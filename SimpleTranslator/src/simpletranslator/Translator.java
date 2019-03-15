/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpletranslator;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




/**
 *
 * @author daanm
 */
public class Translator {

    public static final String BASE_URL = "https://www.vertalen.nu/vertaal?vertaal=";
    public static final String END_URL = "&van=en&naar=nl";
    public static final String END_URL2 = "&van=nl&naar=en";






    /**
     * Handles the {@link java.net.URL} construction and retries to reverse the translation direction when an exception
     * is thrown
     *
     * @param query the input that must be translated
     *
     * @return de parsed document
     */
    public static Document createTranslation(String query) {
        query = query.replaceAll("\\s", "+");
        try {
            URL url = new URL(BASE_URL + query + END_URL);
            Document doc = Jsoup.parse(url, 5000);
            return doc;
        } catch (Exception e) {
            try {
                URL url = new URL(BASE_URL + query + END_URL2);
                Document doc = Jsoup.parse(url, 5000);
                return doc;
            } catch (Exception ex) {
                System.out.println("CreateTranslationError for query = " + query);
            }
        }
        return null;
    }






    /**
     * Uses {@link simpletranslator.Translator#createTranslation(java.lang.String) } to obtain a
     * {@link org.jsoup.nodes.Document} object and iterates over its elements to extract the relevant translation data
     *
     * @param query the input that must be translated
     *
     * @return a list of {@link simpletranslator.TranslationResult } objects that serve as container for a set of
     *         associated words
     */
    public static List<TranslationResult> translate(String query) {
        List<TranslationResult> list = new ArrayList<>();
        Document doc = createTranslation(query);
        Elements results = doc.getElementsByClass("result-item-row");
        for (Element result : results) {
            TranslationResult translationResult = new TranslationResult();
            Elements sources = result.getElementsByClass("result-item-source");
            Elements targets = result.getElementsByClass("result-item-target");
            for (Element source : sources) {
                Elements containers = source.getElementsByClass("lemma-container");
                for (Element container : containers) {
                    String word = container.getElementsByClass("wordentry").text();
                    String meta = container.getElementsByClass("meta-info").text();
                    String info = container.getElementsByClass("additional").text();
                    if (word != null && !word.trim().isEmpty()) {
                        WordEntry entry = new WordEntry(word, meta, info);
                        translationResult.addSource(entry);
                    }
                }
            }
            for (Element target : targets) {
                Elements containers = target.getElementsByClass("lemma-container");
                for (Element container : containers) {
                    String word = container.getElementsByClass("wordentry").text();
                    String meta = container.getElementsByClass("meta-info").text();
                    String info = container.getElementsByClass("additional").text();
                    if (word != null && !word.trim().isEmpty()) {
                        WordEntry entry = new WordEntry(word, meta, info);
                        translationResult.addTarget(entry);
                    }
                }
            }
            list.add(translationResult);
        }
        return list;
    }

}

