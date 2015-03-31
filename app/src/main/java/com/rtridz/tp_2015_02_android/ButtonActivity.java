package com.rtridz.tp_2015_02_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by rtridz on 14.02.15.
 */
public class ButtonActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_button);
        Button returnButton = (Button) findViewById(R.id.button_return);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
// отличие интента от бандла

            public void onClick(View v) {
                Intent buttonIntent = new Intent(ButtonActivity.this, MainActivity.class);
                buttonIntent.putExtra("text", "Text");
                startActivity(buttonIntent);
            }
        });






        Button btn_lesson2 = (Button) findViewById(R.id.btn_lesson2);
        btn_lesson2.setOnClickListener(new View.OnClickListener() {
            @Override
// отличие интента от бандла

            public void onClick(View v) {
                Intent intent_lesson2 = new Intent(ButtonActivity.this, Lesson2.class);
                setResult(Activity.RESULT_OK,intent_lesson2);
//                finish();
//                startActivity(intent_lesson2);
                startActivityForResult(intent_lesson2, 5);


            }
        });
    }



}
