package com.rtridz.tp_2015_02_android.common;

import android.provider.BaseColumns;

public final class DataBaseSchemas {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Translater.db";

    public DataBaseSchemas() {}

    public static final String SQL_CREATE_TABLE_LANGS =
            "CREATE TABLE " + Langs.TABLE_NAME + " (" +
                    Langs._ID + " INTEGER PRIMARY KEY," +
                    Langs.COLUMN_NAME_ABBREV + " TEXT," +
                    Langs.COLUMN_NAME_LANG + " TEXT )";

    public static final String SQL_DELETE_LANGS =
            "DROP TABLE IF EXISTS " + Langs.TABLE_NAME;

    public static abstract class Langs implements BaseColumns {
        public static final String TABLE_NAME = "langs";
        public static final String COLUMN_NAME_ABBREV = "abbreviation";
        public static final String COLUMN_NAME_LANG = "lang";
    }

    public static final String SQL_CREATE_TABLE_ROUTES =
            "CREATE TABLE " + Routes.TABLE_NAME + " (" +
                    Routes._ID + " INTEGER PRIMARY KEY," +
                    Routes.COLUMN_NAME_FROM + " TEXT," +
                    Routes.COLUMN_NAME_TO + " TEXT )";

    public static final String SQL_DELETE_ROUTES =
            "DROP TABLE IF EXISTS " + Routes.TABLE_NAME;

    public static abstract class Routes implements BaseColumns {
        public static final String TABLE_NAME = "routes";
        public static final String COLUMN_NAME_FROM = "fromLang";
        public static final String COLUMN_NAME_TO = "toLang";
    }
}
