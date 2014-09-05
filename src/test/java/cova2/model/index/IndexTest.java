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
        int codeIndex = 0;
        String mainTitleAnime = "Test";
        int codeAnime = 0;
        index = new Index(codeIndex, mainTitleAnime, codeAnime);
        assertNull("Could not create a " + Index.class + " Object!", index);
    }//end of the method createFullIndex

    /**
     * Fail Test check if illegal arguments pass
     *
     * @Test(expected = IllegalArgumentException.class fail in create a Index
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
     * @Test
     */
    public void checkDataThroughtContructor() {

    }

    /**
     * @Test
     */
    public void checkDataThroughtSetters() {

    }

    public void failInvalidCodeIndexSetted() {
    }

    public void failInvalidMainTitleSetted() {
    }

    public void failInvalidCodeAnimeSetted() {
    }

}//end of the test class IndexTest
