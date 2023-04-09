package de.RoSul.ui;

import de.RoSul.logic.ResourceFileManaging;
import de.RoSul.model.Recipes;
import de.RoSul.setting.AppTexts;
import de.RoSul.test.TestData;
import de.rhistel.logic.ConsoleReader;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementiert die Interaktion zwischen UI (User Interface / Benutzeroberfläche) und dem Benutzer.
 */
public class UiController {

//region Konstanten
//endregion

    //region Attribute
    private List<Recipes> recipesList = new ArrayList<>();
//endregion

    //region Konstruktor
    public UiController() {
//      recipesList = TestData.ListInit();
        recipesList = ResourceFileManaging.getInstance().readRecipeFromFile();
        sortRecipeList();

    }
//endregion

//region Methoden

    public void startApp() {
        printName();
        handling();
    }


    private void printName() {
        System.out.println("""
                =======================
                ===== Recipe Book =====
                =======================
                """);
    }

    private void printMainMenu() {
        System.out.println(AppTexts.CHOSE_MSG);
        System.out.println(AppTexts.FIRST_MENU_CHOICE);
        System.out.println(AppTexts.SECOND_MENU_CHOICE);
        System.out.println(AppTexts.THIRD_MENU_CHOICE);
        System.out.println(AppTexts.FORTH_MENU_CHOICE);
        System.out.println(AppTexts.FIFTH_MENU_CHOICE);
    }

    /**
     * Es verarbeitet Benutzereingaben, für einen rezept hinfügen, ändern, löschen, liste zeigen oder app beenden
     */
    private void handling() {

        boolean isValid = false;
        do {

            printMainMenu();
            int userChoice = ConsoleReader.in.readPositivInt();

            switch (userChoice) {
                case 1 -> show();
                case 2 -> add();
                case 3 -> edit();
                case 4 -> delete();
                case 0 -> isValid = true;
                default -> System.err.println("invalid Index");
            }

        } while (!isValid);

    }

    /**
     * Zeigt alle gespeicherten Rezepten auf der Konsole an
     */
    private void show() {
        sortRecipeList();
        System.out.println();
        System.out.printf(AppTexts.FORMAT_STRING_RECIPE, AppTexts.HEADER_INDEX, AppTexts.HEADER_RECIPE_NAME, AppTexts.HEADER_RECIPE_INGREDIENS, AppTexts.HEADER_RECIPE_DESCRIPTION);
        System.out.println();
        for(int i=0; i<recipesList.size(); i++) {
            Recipes rList = recipesList.get(i);
            System.out.printf(AppTexts.FORMAT_STRING_RECIPE, i, rList.getRecipeName(), rList.getRecipeIngrediens(), rList.getRecipeDescription() + "\n");
        }
    }

    /**
     * Legt eine neue rezept anhand von Nutzereingaben an
     *   und fügt sie der Liste hinzu.
     */
    private void add() {
        recipesList.add(UserInput.getInputRecipe());
        sortAndWrite();
        System.out.println(AppTexts.TEXT_MSG_ADD);
    }

    /**
     * Lässt den Nutzer eine rezept zum Bearbeiten auswählen,
     *  danach werden neue Daten eingelesen und die Rezept abgeändert
     */
    private void edit() {
        show();
        boolean isActive = false;
        while (!isActive) {
            System.out.print("\ninsert the index of recipe you need to Edit");
            int userChoice = ConsoleReader.in.readPositivInt();
            if (userChoice < recipesList.size()) {
                isActive = true;
                recipesList.set(userChoice, UserInput.getInputRecipe());
            } else {
                System.err.println("\ninvalid index !!");
            }
        }
        sortAndWrite();
        System.out.println(AppTexts.TEXT_MSG_EDIT);
    }

    /**
     * Löscht ein Element aus der Liste anhand einer Nutzereingabe für
     * den Index.
     */
    private void delete() {

        show();
        boolean isActive = false;
        while (!isActive) {
            System.out.print("\ninsert the index of recipe you need to Delete");
            int userChoice = ConsoleReader.in.readPositivInt();
            if(userChoice < recipesList.size()) {
                isActive = true;
                recipesList.remove(userChoice);
            }else {
                System.err.println("\ninvalid index !!");
            }
        }
            sortAndWrite();
            System.out.println(AppTexts.TEXT_MSG_DELETE);
    }

    /**
     * Sortiert die RezeptListe zuerst aufsteigend nach die name
     * und zu zweit aufsteigend nach Ingrediens dan nach Description.
     */
    private void sortRecipeList() {
        recipesList.sort((firstRecipe, secondRecipe) -> {
            String firstRecipeName = firstRecipe.getRecipeName();
            String secondRecipeName = secondRecipe.getRecipeName();
            int nameCompare = firstRecipeName.compareTo(secondRecipeName);
            if(nameCompare != 0) {
                return nameCompare;
            }


            String firstRecipeIngrediens = firstRecipe.getRecipeIngrediens();
            String secondRecipeIngrediens = secondRecipe.getRecipeIngrediens();
            int ingrediensCompare = firstRecipeIngrediens.compareTo(secondRecipeIngrediens);
            if(ingrediensCompare != 0) {
                return ingrediensCompare;
            }


            String firstRecipeDescription = firstRecipe.getRecipeDescription();
            String secondRecipeDescription = secondRecipe.getRecipeDescription();
            int descriptionCompare = firstRecipeDescription.compareTo(secondRecipeDescription);

                return descriptionCompare;


        });
    }

    /**
     * schreibt den momentanen Stand der Liste in der Csv-Datei nach dem Sortieren der Liste
     */
    public void sortAndWrite() {
        sortRecipeList();
        ResourceFileManaging.getInstance().writeRecipeListToCsvFile(recipesList);
    }


//endregion

}
