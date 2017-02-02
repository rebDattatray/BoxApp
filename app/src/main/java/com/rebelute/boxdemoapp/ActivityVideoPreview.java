package com.rebelute.boxdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Rebelute User on 8/8/2016.
 */
public class ActivityVideoPreview extends AppCompatActivity {

    ImageView saveVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview_vedio);
        saveVideo = (ImageView)findViewById(R.id.saveVideo);
        saveVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityVideoPreview.this,ActivitySelectStorage.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_drawer, R.anim.slide_no);
            }
        });

    }


}
