package capstoneproject.sp.model;


public class NutritionalFacts {
    private String nutriServingSize;
    private String nutriCalories;
    private String nutriCaloriesFromFat;
    private String nutriFat;
    private String nutriCarbohydrates;
    private String nutriDietaryFiber;
    private String nutriProtein;
    private String nutriVitaminA;
    private String nutriVitaminC;
    private String nutriCalcium;
    private String nutriIron;

    public NutritionalFacts() {
    }

    public NutritionalFacts(String nutriServingSize, String nutriCalories, String nutriCaloriesFromFat, String nutriFat, String nutriCarbohydrates, String nutriDietaryFiber, String nutriProtein, String nutriVitaminA, String nutriVitaminC, String nutriCalcium, String nutriIron) {
        this.nutriServingSize = nutriServingSize;
        this.nutriCalories = nutriCalories;
        this.nutriCaloriesFromFat = nutriCaloriesFromFat;
        this.nutriFat = nutriFat;
        this.nutriCarbohydrates = nutriCarbohydrates;
        this.nutriDietaryFiber = nutriDietaryFiber;
        this.nutriProtein = nutriProtein;
        this.nutriVitaminA = nutriVitaminA;
        this.nutriVitaminC = nutriVitaminC;
        this.nutriCalcium = nutriCalcium;
        this.nutriIron = nutriIron;
    }

    public String getNutriServingSize() {
        return nutriServingSize;
    }

    public String getNutriCalories() {
        return nutriCalories;
    }

    public String getNutriCaloriesFromFat() {
        return nutriCaloriesFromFat;
    }

    public String getNutriFat() {
        return nutriFat;
    }

    public String getNutriCarbohydrates() {
        return nutriCarbohydrates;
    }

    public String getNutriDietaryFiber() {
        return nutriDietaryFiber;
    }

    public String getNutriProtein() {
        return nutriProtein;
    }

    public String getNutriVitaminA() {
        return nutriVitaminA;
    }

    public String getNutriVitaminC() {
        return nutriVitaminC;
    }

    public String getNutriCalcium() {
        return nutriCalcium;
    }

    public String getNutriIron() {
        return nutriIron;
    }
}
