package com.example.lab5.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ToDoItemDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ToDo.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ToDoItemContract.ToDoItemEntry.TABLE_NAME + " (" +
                    ToDoItemContract.ToDoItemEntry._ID + " INTEGER PRIMARY KEY," +
                    ToDoItemContract.ToDoItemEntry.COLUMN_NAME_NAME + " TEXT," +
                    ToDoItemContract.ToDoItemEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    ToDoItemContract.ToDoItemEntry.COLUMN_NAME_DATE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ToDoItemContract.ToDoItemEntry.TABLE_NAME;

    public ToDoItemDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
