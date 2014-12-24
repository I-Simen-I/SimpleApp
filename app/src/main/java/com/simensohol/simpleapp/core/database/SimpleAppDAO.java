package com.simensohol.simpleapp.core.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.simensohol.simpleapp.core.entity.ObjectToSave;

import java.util.ArrayList;
import java.util.List;

import static com.simensohol.simpleapp.core.database.DatabaseHelper.*;

/**
 * @author Simen Søhol
 */
public class SimpleAppDAO {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private String[] allColumns = {ID_COLUMN, NAME_COLUMN};

    public SimpleAppDAO(Context context) {
        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(context);
        }
    }

    public List<ObjectToSave> getAll() {
        List<ObjectToSave> objectToSaveList = new ArrayList<ObjectToSave>();
        open(false);

        Cursor cursor = db.query(TABLE, allColumns, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
            do {
                objectToSaveList.add(cursorToObject(cursor));
            } while (cursor.moveToNext());
        }
        close();

        return objectToSaveList;
    }

    public ObjectToSave save(ObjectToSave objectToSave) {
        ContentValues values = new ContentValues();

        values.put(NAME_COLUMN, objectToSave.getName());

        open(false);
        long insertId = db.insert(TABLE, null, values);

        Cursor cursor = db.query(TABLE, allColumns, ID_COLUMN + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();

        ObjectToSave savedObject = cursorToObject(cursor);

        cursor.close();
        close();

        return savedObject;
    }

    private ObjectToSave cursorToObject(Cursor cursor) {
        ObjectToSave objectToSave = new ObjectToSave();
        objectToSave.setId(cursor.getInt(cursor.getColumnIndex(ID_COLUMN)));
        objectToSave.setName(cursor.getString(cursor.getColumnIndex(NAME_COLUMN)));

        return objectToSave;
    }

    public int deleteAll() {
        open(false);
        int deleted = db.delete(TABLE, null, null);
        close();
        return deleted;
    }

    private void open(boolean read) throws SQLException {
        if (db == null || !db.isOpen()) {
            if (read) {
                db = dbHelper.getReadableDatabase();
            } else {
                db = dbHelper.getWritableDatabase();
            }
        }
    }

    private void close() {
        db.close();
    }
}
