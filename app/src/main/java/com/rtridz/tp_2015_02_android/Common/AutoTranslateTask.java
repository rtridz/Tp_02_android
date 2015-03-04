package com.rtridz.tp_2015_02_android.Common;

import android.os.AsyncTask;

public class AutoTranslateTask extends AsyncTask<TranslateTaskParams, Void, AutoTranslateResult> {

    @Override
    protected AutoTranslateResult doInBackground(TranslateTaskParams... params) {
        return YandexAPISender.autoTranslate(params[0].getText(), params[0].getToLang());
    }
}