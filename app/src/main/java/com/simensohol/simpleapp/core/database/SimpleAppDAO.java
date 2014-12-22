package com.simensohol.simpleapp.core.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.simensohol.simpleapp.core.entity.ObjectToSave;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Simen Søhol
 */
public class SimpleAppDAO {
    private static final String ID_COLUMN = "id";
    private static final String NAME_COLUMN = "name";
    private static final String TABLE = "simpleapp";
    private static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE + " (" +
            ID_COLUMN + " int, " +
            NAME_COLUMN + " varchar(255));";

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public SimpleAppDAO(Context context) {
        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(context, CREATE_TABLE);
        }
    }

    public List<ObjectToSave> getAll() {
        List<ObjectToSave> objectToSaveList = new ArrayList<ObjectToSave>();
        ObjectToSave objectToSave;
        open(false);

        Cursor cursor = db.query(TABLE, new String[]{ID_COLUMN, NAME_COLUMN}, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
            do {
                objectToSave = new ObjectToSave();

                objectToSave.setId(cursor.getInt(cursor.getColumnIndex(ID_COLUMN)));
                objectToSave.setName(cursor.getString(cursor.getColumnIndex(NAME_COLUMN)));

                objectToSaveList.add(objectToSave);
            } while (cursor.moveToNext());
        }
        close();

        return objectToSaveList;
    }

    public void save(ObjectToSave objectToSave) {
        ContentValues value = new ContentValues();
        value.put(ID_COLUMN, objectToSave.getId());
        value.put(NAME_COLUMN, objectToSave.getName());

        open(false);
        db.insert(TABLE, null, value);
        close();
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
