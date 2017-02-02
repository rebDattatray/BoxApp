package com.rebelute.boxdemoapp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import receiver.ReceverAuto1;

/**
 * Created by Rebelute User on 1/23/2017.
 */

public class Menu extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_screen);
        ImageView send = (ImageView) findViewById(R.id.send);
        ImageView receive = (ImageView) findViewById(R.id.receive);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu.this,SenderAuto1.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_drawer, R.anim.slide_no);
            }
        });
        receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu.this, ReceverAuto1.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_drawer, R.anim.slide_no);
            }
        });

        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.Video.VideoColumns.DATA };
        Cursor c = getContentResolver().query(uri, projection, null, null, null);
        int vidsCount = 0;
        String path="";
        if (c != null) {
            vidsCount = c.getCount();
            while (c.moveToNext()) {
                Log.e("VIDEO", c.getString(0));
                path = c.getString(0);
                break;
            }
            c.close();
        }

        /*ContentValues content = new ContentValues(4);
        content.put(MediaStore.Video.VideoColumns.DATE_ADDED,
                System.currentTimeMillis() / 1000);
        content.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4");
        content.put(MediaStore.Video.Media.DATA, path);
        ContentResolver resolver = getBaseContext().getContentResolver();
        Uri urii = resolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, content);

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("video*//**//*");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Title");
        sharingIntent.putExtra(android.content.Intent.EXTRA_STREAM,urii);
        sharingIntent.setPackage("com.facebook.katana");
        startActivity(sharingIntent);*/
    }
}
