package receiver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.rebelute.boxdemoapp.Menu;
import com.rebelute.boxdemoapp.R;

public class ActivityStoreLocation extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_storage_location);
        ImageView storage=(ImageView) findViewById(R.id.storage);
        storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityStoreLocation.this,RecSaveVideo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_drawer, R.anim.slide_no);
            }
        });
    }

}
