package capstoneproject.sp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import capstoneproject.sp.R;
import capstoneproject.sp.keys.BundleKey;
import capstoneproject.sp.model.NutritionalFacts;

public class NutritionalFactsActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvServe;
    TextView tvCal;
    TextView tvCalFromFat;
    TextView tvFat;
    TextView tvCarb;
    TextView tvDietaryFiber;
    TextView tvProtein;
    TextView tvVitA;
    TextView tvVitC;
    TextView tvCalcium;
    TextView tvIron;

    Button btnBack;

    private NutritionalFacts facts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_nutritional_facts);
        initViews();
        btnBack.setOnClickListener(this);


        //eto ung galing sa kabilang activity ipapasa mo
        facts = getIntent().getParcelableExtra(BundleKey.NUTRIFACTS);

        if (facts != null) {
            setNutritionalFacts(facts);
        }
    }

    private void initViews() {
        tvServe = (TextView) findViewById(R.id.serveValue);
        tvCal = (TextView) findViewById(R.id.calValue);
        tvCalFromFat = (TextView) findViewById(R.id.calFromFatValue);
        tvFat = (TextView) findViewById(R.id.fatValue);
        tvCarb = (TextView) findViewById(R.id.carbValue);
        tvDietaryFiber = (TextView) findViewById(R.id.dietaryFiberValue);
        tvProtein = (TextView) findViewById(R.id.proteinValue);
        tvVitA = (TextView) findViewById(R.id.vitAValue);
        tvVitC = (TextView) findViewById(R.id.vitCValue);
        tvCalcium = (TextView) findViewById(R.id.calciumValue);
        tvIron = (TextView) findViewById(R.id.ironValue);
        btnBack = (Button) findViewById(R.id.back);
    }

    private void setNutritionalFacts(NutritionalFacts facts) {
        tvServe.setText(facts.getServingSize());
        tvCal.setText(facts.getCalories());
        tvCalFromFat.setText(facts.getCaloriesFromFat());
        tvFat.setText(facts.getFat());
        tvCarb.setText(facts.getCarbohydrates());
        tvDietaryFiber.setText(facts.getDietaryFiber());
        tvProtein.setText(facts.getProtein());
        tvVitA.setText(facts.getVitaminA());
        tvVitC.setText(facts.getVitaminC());
        tvIron.setText(facts.getIron());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
        }
    }
}


