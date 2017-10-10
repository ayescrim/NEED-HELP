package capstoneproject.sp.activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import capstoneproject.sp.R;
import capstoneproject.sp.model.Recipe;
import capstoneproject.sp.adapter.RecipeListAdapter;

public class MainActivity extends AppCompatActivity {

    //constants
    public static final String RECIPE_NAME = "tvRecipeName";
    public static final String RECIPE_ID = "recipeID";

    //declaration of widgets
    EditText recName;
    Spinner coreIngredient;
    Spinner coreIngredientPart;
    Button addRec;

    //create database reference object
    DatabaseReference databaseRecipes;
    //defining the listview
    ListView listViewRecipes;
    //needed list to contain values onstart
    List<Recipe> recipeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //gets database references
        databaseRecipes = FirebaseDatabase.getInstance().getReference("recipes");

// setting values of the variables
        recName = (EditText) findViewById(R.id.recName);
        coreIngredient = (Spinner) findViewById(R.id.textViewCoreIngredient);
        coreIngredientPart = (Spinner) findViewById(R.id.textViewCoreIngredientPart);
        addRec = (Button) findViewById(R.id.addRec);

        listViewRecipes = (ListView) findViewById(R.id.listViewRecipes);
        //initializing the recipe list
        recipeList = new ArrayList<>();
//when button is clicked shit happens
        addRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecipe();
            }
        });

        //long press para mag prompt add procedure and delete
        listViewRecipes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Recipe recipe = recipeList.get(i);

                addDeleteThis(recipe.getRecipeID(), recipe.getRecipeName());
                return false;
            }
        });

        //clickable na ang nasa listahan pra maedit :D
        listViewRecipes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Recipe recipe = recipeList.get(i);
                //redirects to the AddIngredientActivity Java
                Intent intent = new Intent(getApplicationContext(), AddIngredientActivity.class);

                //adds the constants
                intent.putExtra(RECIPE_ID, recipe.getRecipeID());
                intent.putExtra(RECIPE_NAME, recipe.getRecipeName());

                startActivity(intent);
            }
        });

        coreIngredient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String key = parent.getItemAtPosition(position).toString();

                switch (key){
                    case "chicken":
                        ArrayAdapter<String> spinnerChicken = new ArrayAdapter<String>(parent.getContext(),R.layout.support_simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.coreingredientChicken));
                        coreIngredientPart.setAdapter(spinnerChicken);
                        break;

                    case "pork":
                        ArrayAdapter<String> spinnerPork = new ArrayAdapter<String>(parent.getContext(),R.layout.support_simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.coreingredientChicken));
                        coreIngredientPart.setAdapter(spinnerPork);
                        break;

                    case "beef":
                        ArrayAdapter<String> spinnerBeef = new ArrayAdapter<String>(parent.getContext(),R.layout.support_simple_spinner_dropdown_item,
                                getResources().getStringArray(R.array.coreingredientChicken));
                        coreIngredientPart.setAdapter(spinnerBeef);
                        break;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    //rightclick -> generate -> overide methods -> onstart..overide on start for the listview to appear
    @Override
    protected void onStart() {
        super.onStart();
        //attached event value listener
        databaseRecipes.addValueEventListener(new ValueEventListener() {
            @Override

            //executes everytime something changes in the database
            public void onDataChange(DataSnapshot dataSnapshot) {
                //clear previous entry from old execution
                recipeList.clear();
                //to get all the values in firebase
                for (DataSnapshot recipeSnapshot : dataSnapshot.getChildren()) {
                    Recipe recipe = recipeSnapshot.getValue(Recipe.class);

                    recipeList.add(recipe);
                }

                RecipeListAdapter adapter = new RecipeListAdapter(MainActivity.this, recipeList);
                listViewRecipes.setAdapter(adapter);
            }

            //executes when there is an error
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //eto ung nirurun pag clinick ung button
    private void addRecipe() {
        String name = recName.getText().toString().trim();
        String coreingredient = coreIngredient.getSelectedItem().toString();
        String coreingredientpart = coreIngredientPart.getSelectedItem().toString();

//checking kung my tanga na di nag input ng kahit anu pinindot lang ung add
        if (!TextUtils.isEmpty(name)) {
            //yes my utak sya kaya gumana... creating unique string inside "recipes" when push and get to get the ID stored as string
            String id = databaseRecipes.push().getKey();
            //create new
            Recipe recipe = new Recipe(id, name, coreingredient, coreingredientpart);
            //store to firebase
            databaseRecipes.child(id).setValue(recipe);

            //notifications
            Toast.makeText(this, "Successfully Added", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Input Field Required", Toast.LENGTH_LONG).show();
        }
    }

    private void addDeleteThis(final String recipeID, String recipeName) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View addDeleteDialog = inflater.inflate(R.layout.proc_del_dialog, null);

        dialogBuilder.setView(addDeleteDialog);

        final Button deleteButton = (Button) addDeleteDialog.findViewById(R.id.deleteButton);
        final Button addProcButton = (Button) addDeleteDialog.findViewById(R.id.addProcButton);

        dialogBuilder.setTitle("Add procedure or delete " + recipeName + "?");

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecipe(recipeID);
            }
        });

        addProcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),AddProcedureActivity.class);
                intent.putExtra("recipeID",recipeID);
                startActivity(intent);
            }
        });
    }

    private void deleteRecipe(String recipeID) {
        DatabaseReference drRecipe = FirebaseDatabase.getInstance().getReference("recipes").child(recipeID);
        DatabaseReference drRecIng = FirebaseDatabase.getInstance().getReference("recipeIngredients").child(recipeID);
        //-NOTICE-deleting of procedures still not added yet

        drRecipe.removeValue();
        drRecIng.removeValue();

        Toast.makeText(this, "Successfully Deleted", Toast.LENGTH_LONG).show();
    }

}
