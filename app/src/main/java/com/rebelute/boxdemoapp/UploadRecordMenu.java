package com.rebelute.boxdemoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import receiver.ActivityStoreLocation;

/**
 * Created by Rebelute User on 1/23/2017.
 */

public class UploadRecordMenu extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_record_menu);
        ImageView recored = (ImageView) findViewById(R.id.recored);
        ImageView upload = (ImageView) findViewById(R.id.upload);

        recored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UploadRecordMenu.this,ActivityCameraOpen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_drawer, R.anim.slide_no);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UploadRecordMenu.this,VideoList.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_drawer, R.anim.slide_no);
            }
        });
    }
}
