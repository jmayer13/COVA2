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

import java.util.MissingResourceException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * integration Test to InternationalizationCentral
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class InternationalizationCentralIT {

    //class tested
    private InternationalizationCentral internationalizationCentral;

    /**
     * Initiate class to test
     */
    @Before
    public void initiaze() {
        internationalizationCentral = new InternationalizationCentral();
    }//end of the method initiate

    /**
     * Test to get the word Add
     */
    @Test
    public void testGetWord() {
        String word = internationalizationCentral.getWord("add");
        assertEquals("The word add is incorrect! '" + word + "' was get instead!", word, "Add");
    }//end of the method testGetWord

    /**
     * Fail in sending a null key
     */
    @Test(expected = NullPointerException.class)
    public void failNullGetWord() {
        internationalizationCentral.getWord(null);
    }//end of the method failNullGetWord

    /**
     * Test if it reports a impossible key
     */
    @Test(expected = MissingResourceException.class)
    public void failInvalidGetWord() {
        internationalizationCentral.getWord("jaca");
    }//end of the method failInvalidGetWord

}//end of the class InternationalizationCentralIT
