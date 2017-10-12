package capstoneproject.sp.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import capstoneproject.sp.R;
import capstoneproject.sp.utils.NetworkUtils;

public class Interlude extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_interlude);

        //if(NetworkUtils.isNetworkConnected(this) {}
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(Interlude.this, MainActivity.class));//this will run after 1 second of visit
                    finish();
                }
            }, 1000);
        }

    }

