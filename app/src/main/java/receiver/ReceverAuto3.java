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
public class ReceverAuto3 extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;
    ImageView splash;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sender_screen);
        title = (TextView) findViewById(R.id.title);
        title.setText("Connection");
        splash = (ImageView) findViewById(R.id.splash);
        splash.setImageResource(R.drawable.tick);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

                Intent mainIntent = new Intent(ReceverAuto3.this, ActivityStoreLocation.class);
                startActivity(mainIntent);
                overridePendingTransition(R.anim.slide_in_drawer, R.anim.slide_no);
                finish();
            }


        }, SPLASH_DISPLAY_LENGTH);
    }

}
