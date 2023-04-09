package de.RoSul.model;

import de.RoSul.setting.AppTexts;

/**
 * In dieser Klasse werden Rezepte erstellt, und jedes Rezept enthält den Namen der rezept und die
 * Rezept inhalt, Zubereitung des Rezepts
 */
public class Recipes {

//region Konstanten
//endregion

//region Attribute
    private String recipeName;
    private String recipeIngrediens;
    private String recipeDescription;
//endregion

    /**
     * Konstruktor, Eigenschaften mit eigenen
     *  Standardwerten initialisiert.
     */
//region Konstruktor
    public Recipes() {
        this.recipeName = AppTexts.DEFAULT_RECIPE_NAME;
        this.recipeIngrediens = AppTexts.DEFAULT_RECIPE_INGREDIENS;
        this.recipeDescription = AppTexts.DEFAULT_RECIPE_DESCRIPTION;
    }
    public Recipes(String recipeName, String recipeIngrediens) {
        this.recipeName = recipeName;
        this.recipeIngrediens = recipeIngrediens;
        this.recipeDescription = AppTexts.DEFAULT_RECIPE_DESCRIPTION;
    }
    public Recipes(String recipeName, String recipeIngrediens, String recipeDescription) {
        this.recipeName = recipeName;
        this.recipeIngrediens = recipeIngrediens;
        this.recipeDescription = recipeDescription;
    }

    /**
     * Überladener Konstruktor, welcher ine CSV-Zeile entgegennimmt
     * und über eine passende Methode alle Attribute initialisiert.
     * @param csvLine : {@link String} Csv-Zeile mit Attributen
     */
    public Recipes(String csvLine) {
        getDataModel(csvLine);
    }
//endregion

//region Methoden


    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeIngrediens() {
        return recipeIngrediens;
    }

    public void setRecipeIngrediens(String recipeIngrediens) {
        this.recipeIngrediens = recipeIngrediens;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    @Override
    public String toString() {
        return "Recipes{" +
                "recipeName='" + recipeName + '\'' +
                ", recipeIngrediens='" + recipeIngrediens + '\'' +
                ", recipeDescription='" + recipeDescription + '\'' +
                '}';
    }

    /**
     * es gibt dia alle Attribute als csv zeile zurück
     * @return {@link String} : csv-zeile
     */
    public String getDataAsCsvLine() {
        return recipeName + AppTexts.SEPARATOR + recipeIngrediens + AppTexts.SEPARATOR + recipeDescription + "\n";
    }

    /**
     * Befüllt alle Attribute mittels eines Csv-Strings
     * @param dataLine {@link String} : csv zeile mit alle attribute drin
     */
    public void getDataModel(String dataLine) {
//        Recipes recipes;
        String[] dataArray = dataLine.split(AppTexts.SEPARATOR);
        if(dataArray.length == 3) {
            recipeName = dataArray[0];
            recipeIngrediens = dataArray[1];
            recipeDescription = dataArray[2];
           Recipes recipes = new Recipes(recipeName, recipeIngrediens, recipeDescription);
        }else {
            System.err.println(AppTexts.FIE_ERROR_MSG);
        }

    }

    //endregion

}
