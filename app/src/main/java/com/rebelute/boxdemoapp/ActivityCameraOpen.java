package com.rebelute.boxdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Rebelute User on 8/8/2016.
 */
public class ActivityCameraOpen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;
    TextView recoredText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_camera);
        recoredText = (TextView) findViewById(R.id.recoredText);
        recoredText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(ActivityCameraOpen.this, ActivityVideoPreview.class);
                startActivity(mainIntent);
                overridePendingTransition(R.anim.slide_in_drawer, R.anim.slide_no);
            }
        });


    }
}
