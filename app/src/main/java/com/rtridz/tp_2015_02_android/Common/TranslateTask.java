package com.rtridz.tp_2015_02_android.Common;

import android.os.AsyncTask;

public class TranslateTask extends AsyncTask<TranslateTaskParams, Void, String> {

    @Override
    protected String doInBackground(TranslateTaskParams... params) {
        return YandexAPISender.translate(params[0].getText(), params[0].getFromLang(), params[0].getToLang());
    }
}
