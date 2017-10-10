package capstoneproject.sp.model;


public class Ingredient {
    private String ingredientID;
    private String ingredientName;
    private String ingredientQuantity;
    private String ingredientMeasurement;

    public Ingredient() {
    }

    public Ingredient(String ingredientID, String ingredientQuantity, String ingredientMeasurement, String ingredientName) {
        this.ingredientID = ingredientID;
        this.ingredientQuantity = ingredientQuantity;
        this.ingredientMeasurement = ingredientMeasurement;
        this.ingredientName = ingredientName;
    }

    public String getIngredientID() {
        return ingredientID;
    }

    public String getIngredientQuantity() {
        return ingredientQuantity;
    }

    public String getIngredientMeasurement() {
        return ingredientMeasurement;
    }

    public String getIngredientName() {
        return ingredientName;
    }
}