package de.RoSul.logic;

import de.RoSul.model.Recipes;
import de.RoSul.setting.AppTexts;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * <h2>Datei handling</h2>
 * <h3>Funktionalität</h3>
 * <p>
 *  Diese Klasse speichert die Daten dieses Programms
 *  in eine selbst generierte CSV-Datei.
 *  Es ist eine Singleton-Klasse, die einmal die Instanz Erstellt in timeline
 *  Diese Art von Klasse wird beispielsweise für den Lese- oder Schreibvorgang auf externe datei file verwendet
 *  Aufbau eines Singletons in 3 Schritten:
 *      1- Private statische Instanz als Klassenattribut
 *      2- Privater Konstruktor
 *      3-Öffentlicher, statischer und synchronisierter Getter für die Instanz.
 *        Der Getter instanziiert die Klasse beim ersten Aufruf und liefert dieses Objekt danach zurück.
 * </p>
 * <h3>Speicherort</h3>
 * <p>
 * Die Datei wird innerhalb des Projektes gespeichert.
 * Pfad dabei ist: src/de/RoSul/resources/recipes.csv
 * </p>
 */
public class ResourceFileManaging {

//endregion

//region Attribute
    private static ResourceFileManaging instance;
//endregion

//region Konstruktor
    private ResourceFileManaging() {}
//endregion

//region Methoden

public static synchronized ResourceFileManaging getInstance() {
        if(instance == null) {
            instance = new ResourceFileManaging();
        }
        return instance;
}

    /**
     * Schreibt(Speichert) Rezepte einer übergebenen Liste als CSV-Strings
     * in einer CSV-Datei.
     * @param recipesToWrite: {@link List<Recipes>}  Liste mit zu speichernden Rezepten
     */
    public void writeRecipeListToCsvFile(List<Recipes> recipesToWrite) {

        File csvFile = new File(AppTexts.FILE_PATH);

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter out = null;

        try {
            fos = new FileOutputStream(csvFile);
            osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            out = new BufferedWriter(osw);

            for (Recipes recipe : recipesToWrite) {
                out.write(recipe.getDataAsCsvLine());
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.flush();
                out.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
}

    /**
     * Liest die Datei Zeile für Zeile aus, generiert aus jeder Zeile ein Recipe-Objekt
     * und fügt es einer Liste hinzu. Diese Liste wird zurückgegeben.
     * @return : {@link List<Recipes>} Liste von Rezepten
     */
    public List<Recipes> readRecipeFromFile() {
        List<Recipes> recipeList = new ArrayList<>();
        File csvFile = new File(AppTexts.FILE_PATH);

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader in = null;

        try {
            if(!csvFile.exists()) {
                csvFile.createNewFile();
            }

            fis = new FileInputStream(csvFile);
            isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            in = new BufferedReader(isr);
            boolean fileEnded = false;
            while (!fileEnded) {
                String csvLine = in.readLine();
                if(csvLine == null) {
                    fileEnded = true;
                } else {
                    Recipes recipe = new Recipes(csvLine);
                    recipeList.add(recipe);
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                    in.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return recipeList;
}

//endregion

}
