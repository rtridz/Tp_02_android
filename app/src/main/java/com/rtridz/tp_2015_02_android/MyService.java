package com.rtridz.tp_2015_02_android;
import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends IntentService {

    final String LOG_TAG = "myLogs";

    public MyService() {
        super("name");
    }

    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");
    }


    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 1; i<=5; i++) {
            Log.d(LOG_TAG, "i = " + i);
        }
    }
}