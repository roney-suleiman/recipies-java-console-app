package de.RoSul.ui;

import de.RoSul.model.Recipes;
import de.RoSul.setting.AppCommands;
import de.RoSul.setting.AppTexts;
import de.RoSul.ui.InputValidation;

/**
 * Es liest die Eingabe des Benutzers
 */
public class UserInput {

//region Konstanten
//endregion

//region Attribute
//endregion

//region Konstruktor
//endregion

//region Methoden

    public static Recipes getInputRecipe() {


        String recipeName = InputValidation.nameValidation();

        String recipeIngredients = InputValidation.ingrediensValidation();

        String recipeDescription = InputValidation.descriptionValidation();

        return new Recipes(recipeName, recipeIngredients, recipeDescription);
    }

//endregion

}
