package capstoneproject.sp;



public class Recipe {

    String recipeID;
    String recipeName;
    String recipeDescription;

    public Recipe(){
        //used when reading values (importante daw kahit blank sabi ni youtube sensei)
    }

    //rightclick -> generate ->  constructor (eto daw magiinitialize nung ewan labo nung russian speaker)
    public Recipe(String recipeID, String recipeName, String recipeDescription) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
    }

    //rightclick -> generate -> getters (used daw while reading the values)
    public String getRecipeID() {
        return recipeID;
    }
    public String getRecipeName() {
        return recipeName;
    }
    public String getRecipeDescription() {
        return recipeDescription;
    }
}


//used with mainActivity