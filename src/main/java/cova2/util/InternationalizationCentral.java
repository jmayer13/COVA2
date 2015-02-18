/**
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
        start();
    }//end of the constructor

    /**
     * Start default language
     */
    protected void start() {
        currentLocale = new Locale("en", "GL");
        resourceBundle = ResourceBundle.getBundle("lang.language", currentLocale);
    }//end of the method start

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

}//end of the class InternationalizationCentral 
