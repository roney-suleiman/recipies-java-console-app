package de.RoSul.test;

import de.RoSul.model.Recipes;

import java.util.ArrayList;
import java.util.List;

/**
 * Stellt statische Methoden zur Generierung von Testdaten bereit
 */
public class TestData {

//region Konstanten

//endregion

//region Attribute
//endregion

//region Konstruktor
public TestData() {}
//endregion

//region Methoden

    public static List<Recipes> ListInit() {

    List<Recipes> recipesList = new ArrayList<>();

    for(int i = 0 ; i<5 ; i++) {
        Recipes recipe = new Recipes("RecipeName" + i , "RecipeIngrediens" + i , "RecipeDescription" + i);
        recipesList.add(recipe);
    }
    return recipesList;

    }

//endregion

}
