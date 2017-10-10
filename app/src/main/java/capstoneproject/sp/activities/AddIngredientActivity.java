package capstoneproject.sp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import capstoneproject.sp.adapter.IngredientListAdapter;
import capstoneproject.sp.R;
import capstoneproject.sp.keys.BundleKey;
import capstoneproject.sp.mock.MockNutritionalFacts;
import capstoneproject.sp.model.Ingredient;
import capstoneproject.sp.model.NutritionalFacts;

public class AddIngredientActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvRecipeName;
    private EditText etQuantity;
    private Spinner spnMeasurement;
    private Spinner spnIngredient;
    private Button btnAddIngredient;
    private Button btnNutriFact;

    private ListView lvIngredientList;

    //create database references
    DatabaseReference databaseIngredients;
    DatabaseReference NutriIngredient;

    private List<Ingredient> ingredientList;



    private NutritionalFacts facts;

    private String id;

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        ingredientList = new ArrayList<>();
        Intent intent = getIntent();
        id = intent.getStringExtra(MainActivity.RECIPE_ID);
        name = intent.getStringExtra(MainActivity.RECIPE_NAME);

        setupViews();
/*
        lvIngredientList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Ingredient spnIngredient = ingredientList.get(i);

                deleteThis(spnIngredient.getIngredientID(), spnIngredient.getIngredientName());
                return false;
            }
        });
        */
        databaseIngredients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //madumi na ampota kaya linisin muna
                ingredientList.clear();
                //get the ingredients with the same ID
                for (DataSnapshot ingredientSnapshot : dataSnapshot.getChildren()) {

                    Ingredient ingredient = ingredientSnapshot.getValue(Ingredient.class);
                    ingredientList.add(ingredient);
                }


                IngredientListAdapter ingredientListAdapter = new IngredientListAdapter(AddIngredientActivity.this, ingredientList);
                lvIngredientList.setAdapter(ingredientListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }



    private void setupViews() {
        tvRecipeName = (TextView) findViewById(R.id.tvRecipeName);
        etQuantity = (EditText) findViewById(R.id.etQuantity);
        spnMeasurement = (Spinner) findViewById(R.id.spnMeasurement);
        spnIngredient = (Spinner) findViewById(R.id.spnIngredient);
        btnAddIngredient = (Button) findViewById(R.id.btnAddIngredient);
        lvIngredientList = (ListView) findViewById(R.id.showIngredientList);
        databaseIngredients = FirebaseDatabase.getInstance().getReference("recipeIngredients").child(id);
        NutriIngredient = FirebaseDatabase.getInstance().getReference("NutriIngredient");

        btnNutriFact = (Button) findViewById(R.id.btnNutriFact);
        tvRecipeName.setText(name);
        //Set Listeners
        btnAddIngredient.setOnClickListener(this);
        btnNutriFact.setOnClickListener(this);
    }




    @Override
    protected void onStart() {
        super.onStart();


        NutriIngredient.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<String> NutriIngredientList = new ArrayList<String>();
                for (DataSnapshot NutriIngredientSnapshot : dataSnapshot.getChildren()) {
                    String str = NutriIngredientSnapshot.getKey();


                    NutriIngredientList.add(str);

                    ArrayAdapter<String>  NutriIngredientAdapter = new ArrayAdapter<String>(AddIngredientActivity.this,R.layout.support_simple_spinner_dropdown_item,
                            NutriIngredientList);
                    spnIngredient.setAdapter(NutriIngredientAdapter);


                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void saveIngredient() {
        String ingredientQuantity = etQuantity.getText().toString();
        String ingredientMeasurement = spnMeasurement.getSelectedItem().toString();
        String ingredientName = spnIngredient.getSelectedItem().toString();
        if (!TextUtils.isEmpty(ingredientQuantity)) {
            String id = databaseIngredients.push().getKey();

            Ingredient ingredient = new Ingredient(id, ingredientQuantity, ingredientMeasurement, ingredientName);

            databaseIngredients.child(id).setValue(ingredient);
        } else {
            Toast.makeText(this, "Input Fields Required", Toast.LENGTH_LONG).show();
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNutriFact:
                facts = MockNutritionalFacts.generate(); //Mock nutrifacts for testing
                //start nutritional facts activity passing moc nutrifact
                showNutritionalFacts();
                break;

            case R.id.btnAddIngredient:
                saveIngredient();
                break;
        }
    }

    private void showNutritionalFacts() {
        Intent intent = new Intent(this, NutritionalFactsActivity.class);
        intent.putExtra(BundleKey.NUTRIFACTS, facts);
        startActivity(intent);
    }
    /*
    private void deleteThis(final String ingredientID, String ingredientName){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View deleteDialog = inflater.inflate(R.layout.delete_dialog, null);

        dialogBuilder.setView(deleteDialog);

        final Button deleteButton = (Button) deleteDialog.findViewById(R.id.deleteButton);

        dialogBuilder.setTitle("Proceed to delete "+ingredientName+"?");

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteIngredient(ingredientID);
            }
        });
    }

    private void deleteIngredient(String ingredientID) {
        DatabaseReference drRecIng = FirebaseDatabase.getInstance().getReference("recipeIngredients").child(ingredientID);

        drRecIng.removeValue();

        Toast.makeText(this, "Successfully Deleted",Toast.LENGTH_LONG).show();
    }
*/



    /*
    public void calculateIngredient(){
        //serving size, need to total ingredients grams
        tempServingSize = servingSize + (etQuantity * newMeasure);

        //calories
        tempCalories = (tempCalories + ((etQuantity * spnMeasurement) * (NutritionalFacts.getCalories() / 100)));

        //calories from fat
        tempCaloriesFromFat = (tempCaloriesFromFat + ((etQuantity * spnMeasurement) * (NutritionalFacts.getCaloriesFromFat() / 100)));
x`
        //total fat
        tempFat = (tempFat + ((etQuantity * spnMeasurement) * (NutritionalFacts.getFat() / 100)));

        //total carbohydrate
        tempCarbohydrates = (tempCarbohydrates + ((etQuantity * spnMeasurement) * (NutritionalFacts.getCarbohydrates() / 100)));

        //dietary fiber
        tempDietaryFiber = (tempDietaryFiber + ((etQuantity * spnMeasurement) * (NutritionalFacts.getDietaryFiber() / 100 )));

        //protein
        tempProtein = (tempProtein + ((etQuantity * spnMeasurement) * (NutritionalFacts.getProtein() / 100)));

        //vitaminA
        tempVitaminA = (tempVitaminA + ((etQuantity * spnMeasurement) * (NutritionalFacts.getVitaminA() / 100)));

        //vitaminC
        tempVitC = (tempVitaminC + ((etQuantity * spnMeasurement) * (NutritionalFacts.getVitaminC() / 100)));

        //iron
        tempIron = (tempIron + ((etQuantity * spnMeasurement) * (NutritionalFacts.getIron() / 100)));

        //calcium
        tempCalcium = (tempCalcium + ((etQuantity * spnMeasurement) * (NutritionalFacts.getCalcium() / 100)));
    }


//for the spinner to provide needed value for the computation

    public double computeConversion(){
        if (measure == "pinch"){
            return 0.2;
        } else if (measure == "tsp") {
            return 4.0;
        } else if (measure == "tbsp") {
            return 14.0;
        } else if (measure == "gram") {
            return 100.0;
        } else if (measure == "kilo") {
            return 1000.0;
        }
*/


}
