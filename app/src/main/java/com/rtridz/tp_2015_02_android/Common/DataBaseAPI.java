package com.rtridz.tp_2015_02_android.Common;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

public class DataBaseAPI extends SQLiteOpenHelper {

    public DataBaseAPI(Context context) {
        super(context, DataBaseSchemas.DATABASE_NAME, null, DataBaseSchemas.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataBaseSchemas.SQL_CREATE_TABLE_LANGS);
        db.execSQL(DataBaseSchemas.SQL_CREATE_TABLE_ROUTES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DataBaseSchemas.SQL_DELETE_LANGS);
        db.execSQL(DataBaseSchemas.SQL_DELETE_ROUTES);
        onCreate(db);
    }

    public static HashMap getLangs(Context context) {
        DataBaseAPI dbAPI = new DataBaseAPI(context);
        SQLiteDatabase db = dbAPI.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                DataBaseSchemas.Langs.COLUMN_NAME_ABBREV,
                DataBaseSchemas.Langs.COLUMN_NAME_LANG
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = DataBaseSchemas.Langs.COLUMN_NAME_LANG + " ASC";

        Cursor c = db.query(DataBaseSchemas.Langs.TABLE_NAME,  projection,
                null, null, null, null, sortOrder
        );
        HashMap<String, String> values = new HashMap<>();
        if (c.moveToFirst()) {
            boolean isNotEnd = true;
            while (isNotEnd) {
                values.put(c.getString(0), c.getString(1));
                isNotEnd = c.moveToNext();
            }
        }
        return values;
    }

    public static HashMap getRoutes(Context context) {
        DataBaseAPI dbAPI = new DataBaseAPI(context);
        SQLiteDatabase db = dbAPI.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                DataBaseSchemas.Routes.COLUMN_NAME_FROM,
                DataBaseSchemas.Routes.COLUMN_NAME_TO
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = DataBaseSchemas.Routes.COLUMN_NAME_FROM + " ASC";

        Cursor c = db.query(DataBaseSchemas.Routes.TABLE_NAME,  projection,
                null, null, null, null, sortOrder
        );
        HashMap<String, String> values = new HashMap<>();
        if (c.moveToFirst()) {
            boolean isNotEnd = true;
            while (isNotEnd) {
                values.put(c.getString(0), c.getString(1));
                isNotEnd = c.moveToNext();
            }
        }
        return values;
    }
}
