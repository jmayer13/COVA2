package cova2.util;

import java.util.MissingResourceException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Test InternationalizationCentral
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class InternationalizationCentralTest {

    //class tested
    private InternationalizationCentral internationalizationCentral;

    /**
     * Initiate class to test
     */
    @Before
    public void iniciaze() {
        internationalizationCentral = new InternationalizationCentral();
    }//end of the method iniciate

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

}//end of the test class InternationalizationCentralTest
