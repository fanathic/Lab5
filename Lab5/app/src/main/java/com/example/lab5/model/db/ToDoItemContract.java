package com.example.lab5.model.db;

import android.provider.BaseColumns;

public final class ToDoItemContract {
    private ToDoItemContract(){}

    public static class ToDoItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_DATE = "date";
    }


}

