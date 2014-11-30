package cova2.model.index;

import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Test for cova2.model.Index
 *
 * @RunWith(JUnit4.class)
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class IndexTest {

    //class tested
    private cova2.model.index.Index index;

    /**
     * Clean objects
     */
    @After
    public void cleaner() {
        index = null;
    }//end of the method cleaner 

    /**
     * Test the iniciation of a non-empty Index Object
     */
    @Test
    public void testCreateFullIndex() {
        int codeIndex = 1;
        String mainTitleAnime = "Test";
        int codeAnime = 1;
        index = new Index(codeIndex, mainTitleAnime, codeAnime);
        assertNotNull("Could not create a " + Index.class + " Object!", index);
    }//end of the method testCreateFullIndex

    /**
     * Test the iniciation of a non-empty Index Object
     */
    @Test
    public void testCreateIndex() {
        int codeIndex = 1;
        String mainTitleAnime = "Test";
        int codeAnime = 1;
        index = new Index(codeIndex, mainTitleAnime, codeAnime);
        assertNotNull("Could not create a " + Index.class + " Object!", index);
    }//end of the method testCreateIndex

    /**
     * Fail Test check if illegal arguments pass
     */
    @Test(expected = IllegalArgumentException.class)
    public void failCreateFullIndex() {
        //the code should be positive 
        int codeIndex = -1;
        //the title shoud have been informed
        String mainTitleAnime = null;
        //the code should be positive
        int codeAnime = -1;
        index = new Index(codeIndex, mainTitleAnime, codeAnime);
    }//end of the method failCreateFullIndex

    /**
     * Test the setting of data throught the constructor and check then in the
     * gets
     */
    @Test
    public void testDataThroughtContructor() {
        int codeIndex = 1;
        String mainTitleAnime = "Test";
        int codeAnime = 1;
        index = new Index(codeIndex, mainTitleAnime, codeAnime);
        assertTrue("The codeIndex was not returned or are not the same!", codeIndex == index.getCodeIndex());
        assertTrue("The mainTitleAnime was not returned or are not the same!", mainTitleAnime.equals(index.getMainTitleAnime()));
        assertTrue("The codeAnime was not returned or are not the same!", codeAnime == index.getCodeAnime());
    }//end of the method testDataThroughtContructor

    /**
     * Test the data flow between setters and getters
     */
    @Test
    public void testDataThroughtSetters() {
        testCreateFullIndex();
        int codeIndex = 1;
        String mainTitleAnime = "Test";
        int codeAnime = 1;
        index.setCodeIndex(codeIndex);
        index.setMainTitleAnime(mainTitleAnime);
        index.setCodeAnime(codeAnime);
        assertTrue("The codeIndex was not returned or are not the same!", codeIndex == index.getCodeIndex());
        assertTrue("The mainTitleAnime was not returned or are not the same!", mainTitleAnime.equals(index.getMainTitleAnime()));
        assertTrue("The codeAnime was not returned or are not the same!", codeAnime == index.getCodeAnime());
    }//end of the method testDataThroughtSetters

    /**
     * Fail test if set invalid codeIndex
     *
     */
    @Test(expected = IllegalArgumentException.class)
    public void failInvalidCodeIndexSetted() {
        index = new Index(1, "Test", 1);
        index.setCodeIndex(-1);
        index.setCodeIndex(0);
    }//end of the method failInvalidCodeIndexSetted

    /**
     * Fail test if set invalid mainAnimeTitle
     */
    @Test(expected = IllegalArgumentException.class)
    public void failInvalidMainTitleSetted() {
        index = new Index(1, "Test", 1);
        index.setMainTitleAnime("");
    }//end of the method failInvalidMainTitleSetted

    /**
     * Fail test if set invalid codeAnime
     */
    @Test(expected = IllegalArgumentException.class)
    public void failInvalidCodeAnimeSetted() {
        index = new Index(1, "Test", 1);
        index.setCodeAnime(-1);
    }//end of the method failInvalidCodeAnimeSetted

}//end of the test class IndexTest
