package capstoneproject.sp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
import capstoneproject.sp.model.Ingredient;

public class AddIngredientActivity extends AppCompatActivity {

    TextView recipeName;
    EditText quantity;
    Spinner measurement;
    Spinner ingredient;
    Button addIngredient;

    ListView showIngredientList;

    //create database reference
    DatabaseReference databaseIngredients;

    List<Ingredient> ingredientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredient);

        recipeName = (TextView) findViewById(R.id.recipeName);
        quantity = (EditText) findViewById(R.id.quantity);
        measurement = (Spinner) findViewById(R.id.measurement);
        //edit spinner mamaya, preset na ingredients na nsa firebase dpat andito pag nalagyan na
        ingredient = (Spinner) findViewById(R.id.ingredient);

        addIngredient = (Button) findViewById(R.id.addIngredient);
        showIngredientList = (ListView) findViewById(R.id.showIngredientList);

        Intent intent = getIntent();

        ingredientList = new ArrayList<>();
        //takes the unique name and id of the recipe into account when adding
        String id = intent.getStringExtra(MainActivity.RECIPE_ID);
        String name = intent.getStringExtra(MainActivity.RECIPE_NAME);

        recipeName.setText(name);

        //create id and get ingredients from firebase with same created recipe ID
        databaseIngredients = FirebaseDatabase.getInstance().getReference("recipeIngredients").child(id);

        //when add ingredient is clicked
        addIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveIngredient();
            }
        });
/*
        showIngredientList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Ingredient ingredient = ingredientList.get(i);

                deleteThis(ingredient.getIngredientID(), ingredient.getIngredientName());
                return false;
            }
        });
        */
    }

    @Override
    protected void onStart() {
        super.onStart();

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
                showIngredientList.setAdapter(ingredientListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void saveIngredient() {
        String ingredientQuantity = quantity.getText().toString();
        String ingredientMeasurement = measurement.getSelectedItem().toString();
        String ingredientName = ingredient.getSelectedItem().toString();
        if (!TextUtils.isEmpty(ingredientQuantity)) {
            String id = databaseIngredients.push().getKey();

            Ingredient ingredient = new Ingredient(id, ingredientQuantity, ingredientMeasurement, ingredientName);

            databaseIngredients.child(id).setValue(ingredient);
        } else {
            Toast.makeText(this, "Input Fields Required", Toast.LENGTH_LONG).show();
        }
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
        tempServingSize = servingSize + (quantity * newMeasure);

        //calories
        tempCalories = (tempCalories + ((quantity * measurement) * (NutritionalFacts.getNutriCalories() / 100)));

        //calories from fat
        tempCaloriesFromFat = (tempCaloriesFromFat + ((quantity * measurement) * (NutritionalFacts.getNutriCaloriesFromFat() / 100)));
x`
        //total fat
        tempFat = (tempFat + ((quantity * measurement) * (NutritionalFacts.getNutriFat() / 100)));

        //total carbohydrate
        tempCarbohydrates = (tempCarbohydrates + ((quantity * measurement) * (NutritionalFacts.getNutriCarbohydrates() / 100)));

        //dietary fiber
        tempDietaryFiber = (tempDietaryFiber + ((quantity * measurement) * (NutritionalFacts.getNutriDietaryFiber() / 100 )));

        //protein
        tempProtein = (tempProtein + ((quantity * measurement) * (NutritionalFacts.getNutriProtein() / 100)));

        //vitaminA
        tempVitaminA = (tempVitaminA + ((quantity * measurement) * (NutritionalFacts.getNutriVitaminA() / 100)));

        //vitaminC
        tempVitC = (tempVitaminC + ((quantity * measurement) * (NutritionalFacts.getNutriVitaminC() / 100)));

        //iron
        tempIron = (tempIron + ((quantity * measurement) * (NutritionalFacts.getNutriIron() / 100)));

        //calcium
        tempCalcium = (tempCalcium + ((quantity * measurement) * (NutritionalFacts.getNutriCalcium() / 100)));
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
