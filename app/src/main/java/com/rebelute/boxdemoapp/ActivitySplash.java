package com.rebelute.boxdemoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;
import mergevideo.ComposerJoinActivity;
import watermarkk.ComposerVideoEffectActivity;

/**
 * Created by Rebelute User on 8/8/2016.
 */
public class ActivitySplash extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "A9D0YuTYtcnT2HxQJ10vXi26L";
    private static final String TWITTER_SECRET = "j41Ie8u4Wfa7m50WjzLBAE1G56CJ3kbyYrc6WsUU220cPFTlYT";


    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.pre_login_activity);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

                Intent mainIntent = new Intent(ActivitySplash.this, ComposerJoinActivity.class);//Activity_Login.class;ComposerVideoEffectActivity;ComposerJoinActivity
                startActivity(mainIntent);
                overridePendingTransition(R.anim.slide_in_drawer, R.anim.slide_no);
                finish();
            }


        }, SPLASH_DISPLAY_LENGTH);
    }


}
