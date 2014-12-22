package com.simensohol.simpleapp.core.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Simen Søhol
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "simpleApp_DB";
    private String createTable;

    public DatabaseHelper(Context context, String createTable) {
        super(context, DATABASE_NAME, null, 1);
        setCreateTable(createTable);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getCreateTable());
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    //Setter & Getter
    private void setCreateTable(String createTable) {
        this.createTable = createTable;
    }

    private String getCreateTable() {
        return createTable;
    }
}
