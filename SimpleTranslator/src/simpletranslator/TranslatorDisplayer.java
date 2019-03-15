/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpletranslator;




/**
 *
 * @author DaanM
 */
public interface TranslatorDisplayer {

    /**
     * Invoked to show the GUI
     */
    public void showDisplay();






    /**
     * invoked to hide the GUI
     */
    public void hideDisplay();






    /**
     * Invoked to update the view after a given query is fetched, parsed and wrapped as a Translation
     *
     * @param result the translation that is generated for a given input query
     */
    public void displayTranslation(Translation result);

}

