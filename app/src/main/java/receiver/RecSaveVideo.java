package receiver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rebelute.boxdemoapp.R;

/**
 * Created by Rebelute User on 8/8/2016.
 */
public class RecSaveVideo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rec_save_video);
        ImageView saveVdo=(ImageView) findViewById(R.id.saveVdo);
        saveVdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RecSaveVideo.this,RecDownloadVideo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_drawer, R.anim.slide_no);
            }
        });
    }

}
