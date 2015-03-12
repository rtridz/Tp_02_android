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

import com.rtridz.tp_2015_02_android.common.asyncTasks.AutoTranslateResult;
import com.rtridz.tp_2015_02_android.common.asyncTasks.AutoTranslateTask;
import com.rtridz.tp_2015_02_android.common.asyncTasks.TranslateTaskParams;
import com.rtridz.tp_2015_02_android.fragments.Header;
import com.rtridz.tp_2015_02_android.fragments.HeaderFragment;
import com.rtridz.tp_2015_02_android.fragments.TextFields;
import com.rtridz.tp_2015_02_android.fragments.TextFragment;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AutoTranslateActivity extends Activity implements HeaderFragment.Listener, TextFragment.Listener {
    private static final String LOG_TAG = AutoTranslateActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        // checking langs in sql lite
        // get langs

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HeaderFragment headerFragment = HeaderFragment.newInstance(this, true);
        fragmentTransaction.add(R.id.header_container, headerFragment);
        TextFragment textFragment = TextFragment.newInstance(true);
        fragmentTransaction.add(R.id.text_container, textFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_autotrans, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.action_trans):
                startActivity(new Intent(this, TranslateActivity.class));
                return true;
            case (R.id.action_main):
                startActivity(new Intent(this, MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickFromLang() {
        //NOF
    }

    @Override
    public void onClickToLang() {
        // show list of languages
    }

    @Override
    public void onClickTranslate(String fromLang, String toLang) {
        final Fragment fragment = getFragmentManager().findFragmentById(R.id.header_container);
        Fragment txtFragment = getFragmentManager().findFragmentById(R.id.text_container);
        if (fragment instanceof Header && txtFragment instanceof TextFields) {
            final TextFields textFields = (TextFields)txtFragment;
            AutoTranslateTask task = new AutoTranslateTask() {
                @Override
                protected void onPostExecute(AutoTranslateResult result) {
                    textFields.setText(result.getTranslatedText());
                    ((Header)fragment).setFromLangAbbrev(result.getFromLangAbbrev());
                }
            };
            task.execute(new TranslateTaskParams(null, toLang, textFields.getEditText()));
        } else {
            Log.e(LOG_TAG, "Text fragment not implement TextFields");
        }
    }

    @Override
    public void onEnterTranslate(String text) {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.header_container);
        final Fragment txtFragment = getFragmentManager().findFragmentById(R.id.text_container);
        if (fragment instanceof Header && txtFragment instanceof TextFields) {
            final Header header = (Header)fragment;
            AutoTranslateTask task = new AutoTranslateTask() {
                @Override
                protected void onPostExecute(AutoTranslateResult result) {
                    ((TextFields)txtFragment).setText(result.getTranslatedText());
                    header.setFromLangAbbrev(result.getFromLangAbbrev());
                }
            };
            task.execute(new TranslateTaskParams(null, header.getToLangAbbrev(), text));
        } else {
            Log.e(LOG_TAG, "Text fragment not implement TextFields");
        }
    }

    @Override
    @Deprecated
    public boolean onDelKeyEvent() {
        Fragment fragment = getFragmentManager().findFragmentById(R.id.header_container);
        if (fragment instanceof Header) {
            ((Header)fragment).setFromLangAbbrev("auto");
            return true;
        } else {
            Log.e(LOG_TAG, "Text fragment not implement TextFields");
            return false;
        }
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