package capstoneproject.sp.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import capstoneproject.sp.R;
import capstoneproject.sp.model.Recipe;


public class RecipeListAdapter extends ArrayAdapter<Recipe> {

    private Activity context;
    //list storing all the recipes of a user
    List<Recipe> recipeList;

    //initializes all the values
    public RecipeListAdapter(Activity context, List<Recipe> recipeList) {
        super(context, R.layout.list_layout, recipeList);
        this.context = context;
        this.recipeList = recipeList;
    }
    //rightclick -> generate -> overide method -> getview... to overide ansabe? di ko nagets hirap ng russian mag engrish

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewDescription = (TextView) listViewItem.findViewById(R.id.textViewDescription);

        Recipe recipe = recipeList.get(position);

        textViewName.setText(recipe.getRecipeName());
        textViewDescription.setText(recipe.getRecipeDescription());

        return listViewItem;
    }
}

//created for list_layout