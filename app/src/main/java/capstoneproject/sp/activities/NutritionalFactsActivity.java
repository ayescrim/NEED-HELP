package capstoneproject.sp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import capstoneproject.sp.R;

public class NutritionalFactsActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvServerValue;
    TextView tvCalValue;
    TextView tvCalFromFatvalue;
    TextView tvFatValue;
    TextView tvCarbValue;
    TextView tvDietaryFiberValue;
    TextView tvProteinValue;
    TextView tvVitAValue;
    TextView tvVitCValue;
    TextView tvCalciumValue;
    TextView tvIronValue;

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritional_facts);
        initViews();
        btnBack.setOnClickListener(this);
    }

    private void initViews() {
        tvServerValue = (TextView) findViewById(R.id.serveValue);
        tvCalValue = (TextView) findViewById(R.id.calValue);
        tvCalFromFatvalue = (TextView) findViewById(R.id.calFromFatValue);
        tvFatValue = (TextView) findViewById(R.id.fatValue);
        tvCarbValue = (TextView) findViewById(R.id.carbValue);
        tvDietaryFiberValue = (TextView) findViewById(R.id.dietaryFiberValue);
        tvProteinValue = (TextView) findViewById(R.id.proteinValue);
        tvVitAValue = (TextView) findViewById(R.id.vitAValue);
        tvVitCValue = (TextView) findViewById(R.id.vitCValue);
        tvCalciumValue = (TextView) findViewById(R.id.calciumValue);
        tvIronValue = (TextView) findViewById(R.id.ironValue);
        btnBack = (Button) findViewById(R.id.back);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
        }
    }
}


