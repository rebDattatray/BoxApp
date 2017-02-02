package com.rebelute.boxdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Rebelute User on 8/8/2016.
 */
public class VideoList extends AppCompatActivity {

    ImageView saveVideo,preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vedio_list);
        saveVideo = (ImageView)findViewById(R.id.saveVideo);
        preview = (ImageView)findViewById(R.id.preview);
        preview.setImageResource(R.drawable.gallery);
        saveVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(VideoList.this,ActivityVideoPreview.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_drawer, R.anim.slide_no);
            }
        });

    }


}
