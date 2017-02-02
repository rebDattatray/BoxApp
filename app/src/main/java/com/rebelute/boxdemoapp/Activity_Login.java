package com.rebelute.boxdemoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;

public class Activity_Login extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener {

    //Twitter Login Button
    TwitterLoginButton twitterLoginButton;
    private static final String TWITTER_KEY = "A9D0YuTYtcnT2HxQJ10vXi26L";
    private static final String TWITTER_SECRET = "j41Ie8u4Wfa7m50WjzLBAE1G56CJ3kbyYrc6WsUU220cPFTlYT";


    private GoogleApiClient mGoogleApiClient;
    CallbackManager callbackManager;
    RelativeLayout fblogin, twlogin, gpLogin;
    private static final int RC_SIGN_IN = 007;
    Activity mActivity;
    TwitterAuthClient mTwitterAuthClient;
    boolean twitter_clicked = false;
    TwitterSession session;

    ImageView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        mTwitterAuthClient = new TwitterAuthClient();

        setContentView(R.layout.login);

        setIds();
        GoogleLogin();
        twitterCallBack();


        //printHashKey(Activity_Login.this);  //Forgenarating Hashkey for facebook


    }

    private void twitterCallBack() {
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                loginTwitter(result);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });
    }

    public void setIds() {
        mActivity = Activity_Login.this;
        fblogin = (RelativeLayout) findViewById(R.id.fblogin);
        twlogin = (RelativeLayout) findViewById(R.id.twlogin);
        gpLogin = (RelativeLayout) findViewById(R.id.gpLogin);
        twitterLoginButton = (TwitterLoginButton) findViewById(R.id.twitterLogin);

        fblogin.setOnClickListener(this);
        twlogin.setOnClickListener(this);
        gpLogin.setOnClickListener(this);

        login = (ImageView) findViewById(R.id.login);
        login.setOnClickListener(this);
        //Adding callback to the button

    }

    public void GoogleLogin() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Customizing G+ button
        //btnSignIn.setSize(SignInButton.SIZE_STANDARD);
        //btnSignIn.setScopes(gso.getScopeArray());
    }


    public void facebookLogin() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        System.out.println("Success");
                        Log.d("LoginResult:", loginResult.toString());
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        Log.v("LoginActivity", response.toString());

                                        // Application code
                                        try {
                                            String id = object.getString("id");
                                            String name = object.getString("name");
                                            String email = object.getString("email");

                                            Intent facebook = new Intent(mActivity, Menu.class);
                                            startActivity(facebook);
                                            Toast.makeText(mActivity, "Facebook Login", Toast.LENGTH_SHORT).show();
                                        } catch (Exception e) {

                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender,birthday");
                        request.setParameters(parameters);
                        request.executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        Log.d("TAG_ERROR", "On cancel");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d("TAG_ERROR", error.toString());

                    }
                });
    }

    private void GoogleSignInResult(GoogleSignInResult result) {
        Log.d("", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e("", "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();

            Log.e("", "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl);

            Toast.makeText(mActivity, "Google Login", Toast.LENGTH_SHORT).show();


        } else {
            // Signed out, show unauthenticated UI.
            Toast.makeText(mActivity, "Google Login Fail", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            GoogleSignInResult(result);
        } else if (twitter_clicked == true) {
            twitterLoginButton.onActivityResult(requestCode, resultCode, data);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }

    }

    public static void printHashKey(Activity pContext) {
        try {
            PackageInfo info = pContext.getPackageManager().getPackageInfo(
                    "com.giftbox",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fblogin:
                twitter_clicked = false;
                facebookLogin();
                break;
            case R.id.gpLogin:
                twitter_clicked = false;
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
                break;
            case R.id.twlogin:
                twitter_clicked = true;
                if (isTwitterInstalled()) {
                    twitterLoginButton.performClick();
                    //tweetrLogin();
                }
                break;
            case R.id.login:
                Intent intent = new Intent(mActivity, ShareOnSocialMedia.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(mActivity, "Google connection Fail", Toast.LENGTH_SHORT).show();
    }

    public boolean isTwitterInstalled() {
        boolean twitterInstalled = false;
        try {

            PackageInfo packageInfo = getPackageManager().getPackageInfo("com.twitter.android", 0);
            String getPackageName = packageInfo.toString();
            if (getPackageName.contains("com.twitter.android")) {
                twitterInstalled = true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "Please install Twitter App!", Toast.LENGTH_LONG).show();

        }
        return twitterInstalled;
    }

    public void loginTwitter(Result<TwitterSession> result) {

        //Creating a twitter session with result's data
        TwitterSession session = result.data;

        //Getting the username from session
        final String username = session.getUserName();
        Call<User> userResult = Twitter.getApiClient(session).getAccountService().verifyCredentials(true, false);
        userResult.enqueue(new Callback<User>() {

            @Override
            public void failure(TwitterException e) {

            }

            @Override
            public void success(Result<User> userResult) {

                User user = userResult.data;
                String twitterImage = user.profileImageUrl;

                try {
                    Log.e("imageurl", "" + user.profileImageUrl);
                    Log.e("name", "" + user.name);
                    Log.e("email", "" + user.email);
                    Log.e("des", "" + user.description);
                    Log.e("followers ", "" + String.valueOf(user.followersCount));
                    Log.e("createdAt", "" + user.createdAt);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        });

    }
}
