package com.rtridz.tp_2015_02_android.common.asyncTasks;

import android.os.AsyncTask;

import com.rtridz.tp_2015_02_android.common.YandexAPISender;

public class AutoTranslateTask extends AsyncTask<TranslateTaskParams, Void, AutoTranslateResult> {

    @Override
    protected AutoTranslateResult doInBackground(TranslateTaskParams... params) {
        return YandexAPISender.autoTranslate(params[0].getText(), params[0].getToLang());
    }
}