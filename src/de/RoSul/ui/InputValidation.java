package de.RoSul.ui;

import de.RoSul.setting.AppCommands;
import de.RoSul.setting.AppTexts;
import de.rhistel.logic.ConsoleReader;


public class InputValidation {

//region Konstanten
//endregion

//region Attribute
//endregion

//region Konstruktor

//endregion

//region Methoden

    /**
     * Liest einen name einer rezept über die Konsole ein
     * und prüft ihn auf Gültigkeit. Der Nutzer muss zwischen 3 und 10 zeichen einen name eingeben,
     * bis dieser gültig ist.
     * @return {@link String} : gebt die name zurück
     */
    public static String nameValidation() {
        String recipeName = AppTexts.DEFAULT_RECIPE_NAME;
        boolean isValid = false;
        while(!isValid) {
            System.out.printf("\nEnter a Valid Name for your Recipe, between %s and %s words", AppCommands.MIN_NAME_CHAR_SIZE, AppCommands.MAX_NAME_CHAR_SIZE);
            recipeName = ConsoleReader.in.readMandatoryString();
            if(recipeName.length() >= AppCommands.MIN_NAME_CHAR_SIZE && recipeName.length() <= AppCommands.MAX_NAME_CHAR_SIZE) {
                isValid = true;
            } else {
                System.err.println("invalid Name !! ");
            }
        }
        return recipeName;
    }

    /**
     * Liest einen ingrediens einer rezept über die Konsole ein
     * und prüft ihn auf Gültigkeit. Der Nutzer muss 100 zeichen lange einen ingrediens eingeben,
     * bis dieser gültig ist.
     * @return {@link String} : gebt die ingrediens zurück
     */
    public static String ingrediensValidation() {
        String recipeIngrediens = AppTexts.DEFAULT_RECIPE_NAME;
        boolean isValid = false;
        while(!isValid) {
            System.out.printf("\nEnter a Valid Ingrediens for your Recipe, less than %s words", AppCommands.MAX_INGREDIENS_CHAR_SIZE);
            recipeIngrediens = ConsoleReader.in.readMandatoryString();
            if(recipeIngrediens.length() <= AppCommands.MAX_INGREDIENS_CHAR_SIZE) {
                isValid = true;
            } else {
                System.err.println("invalid Ingrediens !! ");
            }
        }
        return recipeIngrediens;
    }

    /**
     * Liest einen description einer rezept über die Konsole ein
     * und prüft ihn auf Gültigkeit. Der Nutzer muss 200 zeichen lange einen description eingeben,
     * bis dieser gültig ist.
     * @return {@link String} : gebt die description zurück
     */
    public static String descriptionValidation() {
        String recipeDescription = AppTexts.DEFAULT_RECIPE_NAME;
        boolean isValid = false;
        while(!isValid) {
            System.out.printf("\nEnter a Valid Description for your Recipe, less than %s words", AppCommands.MAX_DESCRIPTION_MAX_SIZE);
            recipeDescription = ConsoleReader.in.readMandatoryString();
            if(recipeDescription.length() <= AppCommands.MAX_DESCRIPTION_MAX_SIZE) {
                isValid = true;
            } else {
                System.err.println("invalid Description !! ");
            }
        }
        return recipeDescription;
    }

//endregion

}
