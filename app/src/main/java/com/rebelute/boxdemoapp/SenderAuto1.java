package com.rebelute.boxdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Rebelute User on 8/8/2016.
 */
public class SenderAuto1 extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;
    ImageView splash;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sender_screen);
        title = (TextView) findViewById(R.id.title);
        title.setText("QR Scanner");
        splash = (ImageView)findViewById(R.id.splash);
        splash.setImageResource(R.drawable.qrscan);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

                Intent mainIntent = new Intent(SenderAuto1.this, SenderAuto2.class);
                startActivity(mainIntent);
                overridePendingTransition(R.anim.slide_in_drawer, R.anim.slide_no);
                finish();
            }


        }, SPLASH_DISPLAY_LENGTH);
    }


}
