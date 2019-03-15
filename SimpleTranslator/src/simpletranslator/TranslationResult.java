/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpletranslator;

import java.util.ArrayList;
import java.util.List;




/**
 * The raw html that is fetched after parsing an URL for a given input query, contains data which represents a resulting
 * translation. The obtained data is grouped as multiple pairs of source- and target word entries. The way these words
 * are grouped improves the overall readability of the result data. Therefore, the fetched data is wrapped into a data
 * structure that enables the result to maintain its initial formatting. A Single Translation result contains one or
 * more associated source entries and one or more translation entries
 *
 * @author daanm
 */
public class TranslationResult {

    private List<WordEntry> sources;
    private List<WordEntry> targets;






    /**
     * Creates a new TranslationResults with two empty List objects which store {@link simpletranslator.WordEntry }
     * objects
     */
    public TranslationResult() {
        sources = new ArrayList<>();
        targets = new ArrayList<>();
    }






    /**
     * evaluates if both containers are empty
     *
     * @return true if both sources and targets are empty, false if one or both obtain at least one WordEntry
     */
    public boolean isEmpty() {
        return sources.isEmpty() && targets.isEmpty();
    }






    /**
     * Adds the passed {@link simpletranslator.WordEntry } to the source list, representing the entries in the query
     * language
     *
     * @param entry the WordEntry object that belongs to this results sources
     */
    public void addSource(WordEntry entry) {
        if (!entry.isEmpty()) {
            sources.add(entry);
        }
    }






    /**
     * Adds the passed {@link simpletranslator.WordEntry } to the target list, representing the translated entries
     *
     * @param entry the WordEntry object that belongs to this results targets
     */
    public void addTarget(WordEntry entry) {
        if (!entry.isEmpty()) {
            targets.add(entry);
        }
    }






    /**
     *
     * @param index
     *
     * @return
     */
    public WordEntry getSource(int index) {
        return sources.get(index);
    }






    /**
     *
     * @param index
     *
     * @return
     */
    public WordEntry getTarget(int index) {
        return targets.get(index);
    }






    /**
     *
     * @return
     */
    public List<WordEntry> getSourceList() {
        return sources;
    }






    /**
     *
     * @return
     */
    public List<WordEntry> getTargetList() {
        return targets;
    }






    /**
     * evaluates to {@link java.util.List#size() } to determine the amount of source entries
     *
     * @return the amount of source entries
     */
    public int sourceSize() {
        return sources.size();
    }






    /**
     * evaluates to {@link java.util.List#size() } to determine the amount of target entries
     *
     * @return the amount of target entries
     */
    public int targetSize() {
        return targets.size();
    }






    /**
     * generates a single-line {@link java.lang.String } object of all containing source entries
     *
     * @return a single-line string representation of the source entries, separated by a comma
     */
    public String sourceString() {
        String line = "";
        line = sources.stream().map((entry) -> entry.toString() + ",  ").reduce(line, String::concat);
        return line;
    }






    /**
     * generates a single-line {@link java.lang.String } object of all containing target entries
     *
     * @return a single-line string representation of the target entries, separated by a comma
     */
    public String targetString() {
        String line = "";
        for (WordEntry entry : targets) {
            line += entry.toString() + ",  ";
        }
        return line;
    }

}

