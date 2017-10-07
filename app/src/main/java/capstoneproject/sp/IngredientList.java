package capstoneproject.sp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;



public class IngredientList extends ArrayAdapter<Ingredient> {

    private Activity context;
    //list storing all the recipes of a user
    List<Ingredient> ingredientList;

    //initializes all the values
    public IngredientList(Activity context, List<Ingredient> ingredientList){
        super(context, R.layout.ingredient_layout,ingredientList);
        this.context = context;
        this.ingredientList = ingredientList;
    }
    //rightclick -> generate -> overide method -> getview... to overide ansabe? di ko nagets hirap ng russian mag engrish

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.ingredient_layout, null, true);
        TextView textViewQuantity = (TextView)listViewItem.findViewById(R.id.textViewQuantity);
        TextView textViewMeasurement = (TextView)listViewItem.findViewById(R.id.textViewMeasurement);
        TextView textViewIngredient = (TextView)listViewItem.findViewById(R.id.textViewIngredient);

        Ingredient ingredient = ingredientList.get(position);

        textViewQuantity.setText(ingredient.getIngredientQuantity());
        textViewMeasurement.setText(ingredient.getIngredientMeasurement());
        textViewIngredient.setText(ingredient.getIngredientName());

        return listViewItem;
    }
}
