package capstoneproject.sp.utils;

import capstoneproject.sp.model.NutritionalFacts;

/**
 * Created by REGO on 10/10/2017.
 */

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
}
