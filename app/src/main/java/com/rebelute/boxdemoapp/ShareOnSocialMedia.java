package com.rebelute.boxdemoapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestBatch;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.ShareApi;
import com.facebook.share.Sharer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.facebook.login.LoginManager;

import static com.rebelute.boxdemoapp.R.drawable.facebook;

/**
 * Created by Rebelute User on 1/25/2017.
 */

public class ShareOnSocialMedia extends AppCompatActivity implements View.OnClickListener {
    private static final String LOG_TAG = "ShareVideo";
    private CallbackManager callbackManager;
    LoginManager manager;
    private GraphRequestBatch.Callback callback;
    ShareDialog shareDialog;
    RelativeLayout fblogin, twlogin, gpLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_login_activity);


        FacebookSdk.sdkInitialize(getApplicationContext());

        setIds();


    }

    private void setIds() {
        fblogin = (RelativeLayout) findViewById(R.id.fblogin);
        twlogin = (RelativeLayout) findViewById(R.id.twlogin);
        gpLogin = (RelativeLayout) findViewById(R.id.gpLogin);

        fblogin.setOnClickListener(this);
        twlogin.setOnClickListener(this);
        gpLogin.setOnClickListener(this);
    }


    public void postVideo() {
        /*Uri videoFileUri = Uri.parse("file:///storage/emulated/0/Movies/khat.mp4");
        ShareVideo video = new ShareVideo.Builder()
                .setLocalUrl(videoFileUri)
                .build();
        ShareVideoContent content = new ShareVideoContent.Builder()
                .setVideo(video)
                .setContentTitle("Video shared from my android apps")
                .build();
        ShareApi.share(content, null);*/
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        GraphRequest request = GraphRequest.newPostRequest(accessToken, "me/videos", null, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {

            }
        });
        Bundle params = request.getParameters();
        try {
            byte[] data = readBytes("storage/emulated/0/Movies/khat.mp4");
            params.putByteArray("video.mp4", data);
            params.putString("title", "Video shared from my android apps");
            params.putString("description", " #SomeTag");
            request.setParameters(params);
            request.executeAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void postVideoToWall() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        GraphRequest request = GraphRequest.newPostRequest(accessToken, "/me/videos", null, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
                String a = "";
            }
        });
        Bundle params = request.getParameters();
        try {
            byte[] data = readBytes("storage/emulated/0/Movies/mt.mp4");
            params.putByteArray("video.mp4", data);
            params.putString("title", "Test Video");

            params.putString("description", " #SomeTag");
            request.setParameters(params);
            request.executeAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] readBytes(String dataPath) throws IOException {

        InputStream inputStream = new FileInputStream(dataPath);
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];

        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        return byteBuffer.toByteArray();
    }

    public void post() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        GraphRequest request = GraphRequest.newPostRequest(accessToken, "/me/videos", null, new GraphRequest.Callback() {
            @Override
            public void onCompleted(GraphResponse response) {
                String are;
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(ShareOnSocialMedia.this, "Sahre", Toast.LENGTH_SHORT).show();
                //onFBShareVideoCompleted(response);
            }
        });
        Bundle params = request.getParameters();
        try {
            byte[] data = readBytes("/storage/emulated/0/DCIM/Camera/VID_20170130_174838.mp4");
            params.putByteArray("video.mp4", data);
            String albumName = "testFBUpload";
            params.putString("title", albumName);
            params.putString("description", " #SomeTag");
            request.setParameters(params);
            request.executeAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void postTofacebookWall() {
        callbackManager = CallbackManager.Factory.create();

        List<String> permissionNeeds = Arrays.asList("publish_actions");

        //this loginManager helps you eliminate adding a LoginButton to your UI

        manager = LoginManager.getInstance();
        manager.logOut();
        manager.logInWithPublishPermissions(this, permissionNeeds);

        manager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //sharePhotoToFacebook();
                post();
            }

            @Override
            public void onCancel() {
                System.out.println("onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                System.out.println("onError");
            }
        });
    }

    public void shareOnTwitter() {
        /*Uri uri = Uri
                .parse("storage/emulated/0/Movies/khat.mp4");*/
        File myImageFile = new File("/storage/emulated/0/Movies/khat.mp4");
        Uri myVideoUri = Uri.fromFile(myImageFile);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Test Message to share");
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_STREAM, myVideoUri);
        intent.setType("video/*");
        intent.setPackage("com.twitter.android");
        startActivity(intent);
    }

    public void shareOnGoogle() {
        /*Uri uri = Uri
                .parse("storage/emulated/0/Movies/khat.mp4");*/
        File myImageFile = new File("/storage/emulated/0/Movies/khat.mp4");
        Uri myVideoUri = Uri.fromFile(myImageFile);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Test Message to share");
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_STREAM, myVideoUri);
        intent.setType("video/*");
        intent.setPackage("com.google.android.apps.plus");
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void sharePhotoToFacebook() {
        try {
            Bitmap image = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(image)
                    .setCaption("Give me my codez or I will ... you know, do that thing you don't like!")
                    .build();

            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();

            ShareApi.share(content, null);
        } catch (Exception e) {
            System.out.print("Es");
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fblogin:
                postTofacebookWall();
                break;
            case R.id.twlogin:
                shareOnTwitter();
                break;
            case R.id.gpLogin:
                shareOnGoogle();
                break;
        }
    }
}
