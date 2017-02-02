package com.rebelute.boxdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Rebelute User on 8/8/2016.
 */
public class ActivitySelectStorage extends AppCompatActivity {

    ImageView saveVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_storage);
        ImageView recored = (ImageView) findViewById(R.id.recored);
        ImageView upload = (ImageView) findViewById(R.id.upload);

        recored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivitySelectStorage.this,ActivityCameraOpen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_drawer, R.anim.slide_no);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivitySelectStorage.this,VideoList.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_drawer, R.anim.slide_no);
            }
        });

    }


}
