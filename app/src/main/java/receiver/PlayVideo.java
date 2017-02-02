package receiver;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.rebelute.boxdemoapp.R;

/**
 * Created by Rebelute User on 8/8/2016.
 */
public class PlayVideo extends AppCompatActivity {

    ImageView splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_login_activity);
        splash = (ImageView) findViewById(R.id.splash);
        splash.setImageResource(R.drawable.play_vdo);

    }

}
