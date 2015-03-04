package com.rtridz.tp_2015_02_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final String LOG_TAG = TranslateActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button translate = (Button)findViewById(R.id.button_trans);
        translate.setOnClickListener(this);
        Button autoTranslate = (Button)findViewById(R.id.button_auto_trans);
        autoTranslate.setOnClickListener(this);

//        GetLangsTask task = new GetLangsTask();
//        task.execute(getApplicationContext());
//        try {
//            Log.d(LOG_TAG, "Download langs: " + task.get()); // blocking for waiting task
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//        getApplicationContext();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_trans :
                startActivity(new Intent(this, TranslateActivity.class));
                break;
            case R.id.button_auto_trans :
                startActivity(new Intent(this, AutoTranslateActivity.class));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.action_auto_trans) :
                startActivity(new Intent(this, AutoTranslateActivity.class));
                return true;
            case (R.id.action_trans) :
                startActivity(new Intent(this, TranslateActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        Log.d(LOG_TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(LOG_TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(LOG_TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(LOG_TAG, "onDestroy");
        super.onDestroy();
    }
}
