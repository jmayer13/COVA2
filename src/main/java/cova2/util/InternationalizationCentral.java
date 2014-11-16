package cova2.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

//TO DO: Connect with preferences system
/**
 * Central to internacionalization
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class InternationalizationCentral {

    private Locale currentLocale;
    private ResourceBundle resourceBundle;

    /**
     * Iniciate with default locale
     */
    public InternationalizationCentral() {
        currentLocale = new Locale("en", "GL");
        resourceBundle = ResourceBundle.getBundle("lang.language", currentLocale);
    }//end of the constructor

    /**
     * Get word or sentence in the defined language
     *
     * @param key
     * @return <code>String</code> word or setence traduced
     */
    public String getWord(String key) {
        String word = null;
        try {
            word = resourceBundle.getString(key);
        } catch (MissingResourceException exception) {
            Locale defaultLocale = new Locale("en", "GL");
            ResourceBundle defaulResourceBundle = ResourceBundle.getBundle("lang.language", defaultLocale);
            word = defaulResourceBundle.getString(key);
        }
        return word;
    }//end of the method getWord

}//end pf the class InternationalizationCentral 
