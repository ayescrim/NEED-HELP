package capstoneproject.sp.mock;

import capstoneproject.sp.model.NutritionalFacts;

/**
 * Created by REGO on 10/10/2017.
 */

public class MockNutritionalFacts {
    //mock data generator
    public static final NutritionalFacts generate() {
        NutritionalFacts facts = new NutritionalFacts();
        facts.setCalcium("4");
        facts.setCalories("1000");
        facts.setCaloriesFromFat("2000");
        facts.setFat("2020202");
        facts.setProtein("200");
        facts.setVitaminA("0");
        facts.setDietaryFiber("20");

        return facts;
    }
}
