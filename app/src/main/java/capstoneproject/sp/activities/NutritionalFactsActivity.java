package capstoneproject.sp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import capstoneproject.sp.R;

public class NutritionalFactsActivity extends AppCompatActivity implements View.OnClickListener{

    TextView serveValue;
    TextView calValue;
    TextView calFromFatValue;
    TextView fatValue;
    TextView carbValue;
    TextView dietaryFiberValue;
    TextView proteinValue;
    TextView vitAValue;
    TextView vitCValue;
    TextView calciumValue;
    TextView ironValue;

    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritional_facts);

        serveValue = (TextView) findViewById(R.id.serveValue);
        calValue = (TextView) findViewById(R.id.calValue);
        calFromFatValue = (TextView) findViewById(R.id.calFromFatValue);
        fatValue = (TextView) findViewById(R.id.fatValue);
        carbValue = (TextView) findViewById(R.id.carbValue);
        dietaryFiberValue = (TextView) findViewById(R.id.dietaryFiberValue);
        proteinValue = (TextView) findViewById(R.id.proteinValue);
        vitAValue = (TextView) findViewById(R.id.vitAValue);
        vitCValue = (TextView) findViewById(R.id.vitCValue);
        calciumValue = (TextView) findViewById(R.id.calciumValue);
        ironValue = (TextView) findViewById(R.id.ironValue);

        back = (Button) findViewById(R.id.back);

        //returns to the add ingredient screen
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    //command
    private void goBack() {
        //tangina anu lalagay ko dito para mag back tapos ma end task to?
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
        }
    }
}


