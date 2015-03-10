package com.rtridz.tp_2015_02_android.common.asyncTasks;

import android.os.AsyncTask;

import com.rtridz.tp_2015_02_android.common.YandexAPISender;

import java.io.UnsupportedEncodingException;

public class AutoTranslateTask extends AsyncTask<TranslateTaskParams, Void, AutoTranslateResult> {

    @Override
    protected AutoTranslateResult doInBackground(TranslateTaskParams... params) {
        try {
            return YandexAPISender.autoTranslate(params[0].getText(), params[0].getToLang());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}