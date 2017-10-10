package capstoneproject.sp.model;


public class Recipe {

    String recipeID;
    String recipeName;
    String recipeCoreIngredient;
    String recipeCoreIngredientPart;

    public Recipe() {
        //used when reading values (importante daw kahit blank sabi ni youtube sensei)
    }

    //rightclick -> generate ->  constructor (eto daw magiinitialize nung ewan labo nung russian speaker)
    public Recipe(String recipeID, String recipeName, String recipeCoreIngredient, String recipeCoreIngredientPart) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.recipeCoreIngredient = recipeCoreIngredient;
        this.recipeCoreIngredientPart = recipeCoreIngredientPart;
    }

    //rightclick -> generate -> getters (used daw while reading the values)

    public String getRecipeID() {
        return recipeID;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeCoreIngredient() {
        return recipeCoreIngredient;
    }

    public String getRecipeCoreIngredientPart() {
        return recipeCoreIngredientPart;
    }
}


//used with mainActivity