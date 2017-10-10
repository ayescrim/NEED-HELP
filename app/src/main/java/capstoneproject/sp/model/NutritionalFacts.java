package capstoneproject.sp.model;


import android.os.Parcel;
import android.os.Parcelable;

public class NutritionalFacts implements Parcelable {
    private String servingSize;
    private String calories;
    private String caloriesFromFat;
    private String fat;
    private String carbohydrates;
    private String dietaryFiber;
    private String protein;
    private String vitaminA;
    private String vitaminC;
    private String calcium;
    private String iron;

    public NutritionalFacts() {
    }

    public NutritionalFacts(String nutriServingSize, String nutriCalories, String nutriCaloriesFromFat, String nutriFat, String nutriCarbohydrates, String nutriDietaryFiber, String nutriProtein, String nutriVitaminA, String nutriVitaminC, String nutriCalcium, String nutriIron) {
        this.servingSize = nutriServingSize;
        this.calories = nutriCalories;
        this.caloriesFromFat = nutriCaloriesFromFat;
        this.fat = nutriFat;
        this.carbohydrates = nutriCarbohydrates;
        this.dietaryFiber = nutriDietaryFiber;
        this.protein = nutriProtein;
        this.vitaminA = nutriVitaminA;
        this.vitaminC = nutriVitaminC;
        this.calcium = nutriCalcium;
        this.iron = nutriIron;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public void setCaloriesFromFat(String caloriesFromFat) {
        this.caloriesFromFat = caloriesFromFat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setDietaryFiber(String dietaryFiber) {
        this.dietaryFiber = dietaryFiber;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public void setVitaminA(String vitaminA) {
        this.vitaminA = vitaminA;
    }

    public void setVitaminC(String vitaminC) {
        this.vitaminC = vitaminC;
    }

    public void setCalcium(String calcium) {
        this.calcium = calcium;
    }

    public void setIron(String iron) {
        this.iron = iron;
    }

    public String getServingSize() {
        return servingSize;
    }

    public String getCalories() {
        return calories;
    }

    public String getCaloriesFromFat() {
        return caloriesFromFat;
    }

    public String getFat() {
        return fat;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public String getDietaryFiber() {
        return dietaryFiber;
    }

    public String getProtein() {
        return protein;
    }

    public String getVitaminA() {
        return vitaminA;
    }

    public String getVitaminC() {
        return vitaminC;
    }

    public String getCalcium() {
        return calcium;
    }

    public String getIron() {
        return iron;
    }

    protected NutritionalFacts(Parcel in) {
        servingSize = in.readString();
        calories = in.readString();
        caloriesFromFat = in.readString();
        fat = in.readString();
        carbohydrates = in.readString();
        dietaryFiber = in.readString();
        protein = in.readString();
        vitaminA = in.readString();
        vitaminC = in.readString();
        calcium = in.readString();
        iron = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(servingSize);
        dest.writeString(calories);
        dest.writeString(caloriesFromFat);
        dest.writeString(fat);
        dest.writeString(carbohydrates);
        dest.writeString(dietaryFiber);
        dest.writeString(protein);
        dest.writeString(vitaminA);
        dest.writeString(vitaminC);
        dest.writeString(calcium);
        dest.writeString(iron);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<NutritionalFacts> CREATOR = new Parcelable.Creator<NutritionalFacts>() {
        @Override
        public NutritionalFacts createFromParcel(Parcel in) {
            return new NutritionalFacts(in);
        }

        @Override
        public NutritionalFacts[] newArray(int size) {
            return new NutritionalFacts[size];
        }
    };
}