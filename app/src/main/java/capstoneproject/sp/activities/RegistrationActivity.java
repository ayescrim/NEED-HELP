package capstoneproject.sp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import capstoneproject.sp.R;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_registration);
    }
}
