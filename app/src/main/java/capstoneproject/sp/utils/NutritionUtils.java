package capstoneproject.sp.utils;

import capstoneproject.sp.model.NutritionalFacts;

public class NutritionUtils {


    private static NutritionalFacts getNutritionFacts() {
        NutritionalFacts nutritionalFacts = new NutritionalFacts();

        return nutritionalFacts;
    }

    private static  double getComputedValue(double quantity, double measurement, double nutriBaseVal) {
        double val = 0;
        val = (((quantity * measurement) * (nutriBaseVal / 100)));
        return val;
    }

    private static double getServingSize() {
        double val = 0;
        //TODO get serving size formul
        return val;
    }

    private static double convertValuesToGrams(String measure) {
        if (measure == "pinch"){
            return 0.2;
        } else if (measure == "tsp") {
            return 4.0;
        } else if (measure == "tbsp") {
            return 14.0;
        } else if (measure == "gram") {
            return 100.0;
        } else if (measure == "kilo") {
            return 1000.0;
        } else {
            return 0;
        }
    }
}
