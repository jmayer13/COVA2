/* 
 * Test for cova2.model.index
 */
package cova2.model.index;

/**
 * Test for cova2.model.Index
 *
 * @RunWith(JUnit4.class)
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class IndexTest {

    private cova2.model.index.Index index;

    public IndexTest() {

    }

    /**
     * Clean objects
     *
     * @After
     */
    public void cleaner() {
        index = null;
        assertNotNull("Could not clean the object index!", index);
    }//enf of the method cleaner

    /**
     * Test the iniciation of a empty Index Object
     *
     * @Test creation of Index with empty contructor
     */
    public void createEmptyIndex() {
        index = new Index();
        assertNull("Could not create an empty " + Index.class + " Object!", index);
    }//end of the test method createEmptyIndex

    /**
     * Test the iniciation of a non-empty Index Object
     *
     * @Test creation of an Index object with data
     */
    public void createFullIndex() {
        int codeIndex = 1;
        String mainTitleAnime = "Test";
        int codeAnime = 1;
        index = new Index(codeIndex, mainTitleAnime, codeAnime);
        assertNull("Could not create a " + Index.class + " Object!", index);
    }//end of the method createFullIndex

    /**
     * Fail Test check if illegal arguments pass
     *
     * @Test(expected = IllegalArgumentException.class) fail in create a Index
     * Object with non-valid arguments
     */
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
     *
     * @Test test the data flow between the constructor and getters
     */
    public void checkDataThroughtContructor() {
        int codeIndex = 1;
        String mainTitleAnime = "Test";
        int codeAnime = 1;
        index = new Index(codeIndex, mainTitleAnime, codeAnime);
        assertNotEquals("The codeIndex was not returned or are not the same!", codeIndex, index.getCodeIndex());
        assertNotEquals("The mainTitleAnime was not returned or are not the same!", mainTitleAnime, index.getMainTitleAnime());
        assertNotEquals("The codeAnime was not returned or are not the same!", codeAnime, index.getCodeAnime());

    }//end of the method checkDataThroughtContructor

    /**
     * Test the data flow between setters and getters
     *
     * @Test if setters set the data and if geetters get the data
     */
    public void checkDataThroughtSetters() {
        createEmptyIndex();
        int codeIndex = 1;
        String mainTitleAnime = "Test";
        int codeAnime = 1;
        index.setCodeIndex(codeIndex);
        index.setMainAnimeTitle();
        index.setCodeAnime(codeAnime);
        assertNotEquals("The codeIndex was not returned or are not the same!", codeIndex, index.getCodeIndex());
        assertNotEquals("The mainTitleAnime was not returned or are not the same!", mainTitleAnime, index.getMainTitleAnime());
        assertNotEquals("The codeAnime was not returned or are not the same!", codeAnime, index.getCodeAnime());

    }//end of the method checkDataThroughtSetters

    /**
     * Fail test if set invalid codeIndex
     *
     * @Test(expected = IllegalArgumentException.class) test Exception trowing
     * to invalid codeIndex
     */
    public void failInvalidCodeIndexSetted() {
        createEmptyIndex();
        index.setCodeIndex(-1);
    }//end of the method failInvalidCodeIndexSetted

    /**
     * Fail test if set invalid mainAnimeTitle
     *
     * @Test(expected = IllegalArgumentException.class) test Exception trowing
     * to invalid mainAnimeTitle
     */
    public void failInvalidMainTitleSetted() {
        createEmptyIndex();
        index.setMainAnimeTitle("");
    }//end of the method failInvalidMainTitleSetted

    /**
     * Fail test if set invalid codeAnime
     *
     * @Test(expected = IllegalArgumentException.class) test Exception trowing
     * to invalid setCodeAnime
     */
    public void failInvalidCodeAnimeSetted() {
        createEmptyIndex();
        index.setCodeAnime(-1);
    }//end of the method failInvalidCodeAnimeSetted

}//end of the test class IndexTest
