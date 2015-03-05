package com.rtridz.tp_2015_02_android;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.rtridz.tp_2015_02_android.common.asyncTasks.TranslateTask;
import com.rtridz.tp_2015_02_android.common.asyncTasks.TranslateTaskParams;
import com.rtridz.tp_2015_02_android.fragments.Header;
import com.rtridz.tp_2015_02_android.fragments.HeaderFragment;
import com.rtridz.tp_2015_02_android.fragments.TextFields;
import com.rtridz.tp_2015_02_android.fragments.TextFragment;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TranslateActivity extends Activity implements HeaderFragment.Listener, TextFragment.Listener {
    private static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        // checking langs in sql lite
        // get langs

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HeaderFragment headerFragment = HeaderFragment.newInstance(false);
        fragmentTransaction.add(R.id.header_container, headerFragment);
        TextFragment textFragment = TextFragment.newInstance(false);
        fragmentTransaction.add(R.id.text_container, textFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_trans, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.action_auto_trans) :
                startActivity(new Intent(this, AutoTranslateActivity.class));
                return true;
            case (R.id.action_main) :
                startActivity(new Intent(this, MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickFromLang() {
        // show list of languages
    }

    @Override
    public void onClickToLang() {
        // show list of languages
    }

    @Override
    public void onClickTranslate(String fromLang, String toLang) {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.text_container);
        if (fragment instanceof TextFields) {
            final TextFields textFields = (TextFields)fragment;
            TranslateTask task = new TranslateTask() {
                @Override
                protected void onPostExecute(String translatedText) {
                    super.onPostExecute(translatedText);
                    textFields.setText(translatedText);
                }
            };
            task.execute(new TranslateTaskParams(fromLang, toLang, textFields.getEditText()));
        } else {
            Log.e(LOG_TAG, "Text fragment not implement TextFields");
        }
    }

    @Override
    public void onEnterTranslate(String text) {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.header_container);
        final Fragment txtFragment = getFragmentManager().findFragmentById(R.id.text_container);
        if (fragment instanceof Header && txtFragment instanceof TextFields) {
            Header header = (Header)fragment;
            TranslateTask task = new TranslateTask() {
                @Override
                protected void onPostExecute(String translatedText) {
                    super.onPostExecute(translatedText);
                    ((TextFields)txtFragment).setText(translatedText);
                }
            };
            task.execute(new TranslateTaskParams(header.getFromLangAbbrev(), header.getToLangAbbrev(), text));
        } else {
            Log.e(LOG_TAG, "Text fragment not implement TextFields");
        }
    }

    @Override
    public boolean onDelKeyEvent() {
        // NOF
        return false;
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
