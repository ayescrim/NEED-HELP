package capstoneproject.sp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import capstoneproject.sp.R;

public class MainMenuActivity extends AppCompatActivity {

    Button btnAllRecipes;
    Button btnCategories;
    Button btnMyProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_menu);


    btnAllRecipes = (Button) findViewById(R.id.btnAllRecipes);
    btnCategories = (Button) findViewById(R.id.btnCategories);
    btnMyProfile = (Button) findViewById(R.id.btnMyProfile);

        btnAllRecipes.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), ShowAllRecipeActivity.class);
            startActivity(intent);
        }
    });

        btnCategories.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), AnimalChoicesActivity.class);
            startActivity(intent);
        }
    });

        btnMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddRecipeActivity.class);
                startActivity(intent);
            }
        });
    }
}
