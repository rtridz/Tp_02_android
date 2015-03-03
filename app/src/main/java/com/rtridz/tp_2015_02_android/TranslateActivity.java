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
import android.view.View;

import com.rtridz.tp_2015_02_android.Common.TranslateTask;
import com.rtridz.tp_2015_02_android.Common.TranslateTaskParams;
import com.rtridz.tp_2015_02_android.fragments.HeaderFragment;
import com.rtridz.tp_2015_02_android.fragments.TextFields;
import com.rtridz.tp_2015_02_android.fragments.TextFragment;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TranslateActivity extends Activity  implements View.OnClickListener, HeaderFragment.Listener, TextFragment.Listener {
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
        TextFragment textFragment = TextFragment.newInstance();
        fragmentTransaction.add(R.id.text_container, textFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {

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
            TextFields textFields = (TextFields)fragment;
            TranslateTask task = new TranslateTask();
            task.execute(new TranslateTaskParams(fromLang, toLang, textFields.getEditText()));
            try {
                String translatedText = task.get(5, TimeUnit.SECONDS); // blocking for waiting task
                textFields.setText(translatedText);
            } catch (InterruptedException | ExecutionException | CancellationException | TimeoutException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(LOG_TAG, "Text fragment not implement TextFields");
        }
    }

    @Override
    public void onEnterTranslate(String text) {

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
