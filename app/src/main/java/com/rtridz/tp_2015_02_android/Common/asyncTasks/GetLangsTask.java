package com.rtridz.tp_2015_02_android.common.asyncTasks;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.rtridz.tp_2015_02_android.common.DataBaseAPI;
import com.rtridz.tp_2015_02_android.common.DataBaseSchemas;
import com.rtridz.tp_2015_02_android.common.LangsInfo;
import com.rtridz.tp_2015_02_android.common.YandexAPISender;

import java.util.Map.Entry;

public class GetLangsTask extends AsyncTask<Context, Void, Boolean> {

    @Override
    protected Boolean doInBackground(Context... params) {
        LangsInfo langsInfo = YandexAPISender.getLangsInfo();
        if (langsInfo != null) {
            //save data to SQLlite
            saveToDB(params[0], langsInfo);
            return true;
        }
        return false;
    }

    private static void saveToDB(Context context, LangsInfo langsInfo) {
        DataBaseAPI dbAPI = new DataBaseAPI(context);
        // Gets the data repository in write mode
        SQLiteDatabase db = dbAPI.getWritableDatabase();

        ContentValues values;
        for (Entry<String, String> pair : langsInfo.getLangs().entrySet()) {
            // Create a new map of values, where column names are the keys
            values = new ContentValues();
            values.put(DataBaseSchemas.Langs.COLUMN_NAME_ABBREV, pair.getKey());
            values.put(DataBaseSchemas.Langs.COLUMN_NAME_LANG, pair.getValue());
            // Insert the new row, returning the primary key value of the new row
            db.insert(DataBaseSchemas.Langs.TABLE_NAME,null,values);
        }
        for (Entry<String, String> pair : langsInfo.getLangPairs().entrySet()) {
            // Create a new map of values, where column names are the keys
            values = new ContentValues();
            values.put(DataBaseSchemas.Routes.COLUMN_NAME_FROM, pair.getKey());
            values.put(DataBaseSchemas.Routes.COLUMN_NAME_TO, pair.getValue());
            // Insert the new row, returning the primary key value of the new row
            db.insert(DataBaseSchemas.Routes.TABLE_NAME,null,values);
        }
    }
}
