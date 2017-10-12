package capstoneproject.sp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import capstoneproject.sp.R;
import capstoneproject.sp.adapter.RecipeListAdapter;
import capstoneproject.sp.model.Recipe;

public class ShowAllRecipeActivity extends AppCompatActivity {


    DatabaseReference databaseRecipes;

    ListView listViewAllRecipes;
    private List<Recipe> allRecipeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_show_all_recipe);

        databaseRecipes = FirebaseDatabase.getInstance().getReference("recipes");
        listViewAllRecipes = (ListView) findViewById(R.id.lvAllRecipe);

        allRecipeList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attached event value listener
        databaseRecipes.addValueEventListener(new ValueEventListener() {
            @Override

            //executes everytime something changes in the database
            public void onDataChange(DataSnapshot dataSnapshot) {
                //clear previous entry from old execution
                allRecipeList.clear();
                //to get all the values in firebase
                for (DataSnapshot recipeSnapshot : dataSnapshot.getChildren()) {
                    Recipe recipe = recipeSnapshot.getValue(Recipe.class);

                    allRecipeList.add(recipe);
                }

                RecipeListAdapter adapter = new RecipeListAdapter(ShowAllRecipeActivity.this,allRecipeList);
                listViewAllRecipes.setAdapter(adapter);
            }

            //executes when there is an error
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
