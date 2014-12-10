package cova2.dao;

import cova2.model.index.Index;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test to IndexDAO, that control the persistence of IndexDB
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class IndexDAOTest {

    private IndexDAO indexDAO;
    //index generic for tests
    private Index testIndex = new Index("title", 0);

    /**
     * Create data for test
     */
    @Before
    public void initialize() throws SQLException, ClassNotFoundException {
        indexDAO = new IndexDAO();
        testIndex = new Index("title", 0);
    }//end of the method initialize

    /**
     * Clean the test data
     *
     * @throws SQLException if script don't works
     */
    @After
    public void cleanData() throws SQLException {
        if (testIndex.getCodeIndex() != 0) {
            indexDAO.deleteIndex(testIndex);
        }
        indexDAO.closeConnection();
    }//end of the method cleanData

    /**
     * Test registration of Index
     *
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testAddIdex() throws SQLException, ClassNotFoundException {
        testIndex = indexDAO.addIndex(testIndex);
        assertTrue("Problem when registering Index!", testIndex.getCodeIndex() > 0);
    }//end of method testAddIdex

    /**
     * Test the getting of Indexes
     *
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testGetIndexes() throws SQLException, ClassNotFoundException {
        testAddIdex();
        List<Index> indexes = indexDAO.getIndexes();
        assertNotNull("Coult not get Indexes!", indexes);
    }//end of method testGetIndexes

    /**
     * Test recomended anime code
     */
    @Test
    public void testGetRecomendedCodeAnime() throws SQLException, ClassNotFoundException {
        testIndex.setCodeAnime(indexDAO.getRecommendedCodeAnime());
        testIndex = indexDAO.addIndex(testIndex);
        List<Index> indexes = indexDAO.getIndexes();
        assertTrue("Recommended anime code generated is invalid!", indexes.get(0).getCodeAnime() >= 0);
    }//end of the method testGetRecomendedAnimeCode

    /**
     * Test recomended anime code with empty database
     */
    @Test
    public void testEmptyGetRecomendedCodeAnime() throws SQLException, ClassNotFoundException {
        indexDAO = new IndexDAO();
        assertTrue("Recommended anime code generated with empty database is invalid!", indexDAO.getRecommendedCodeAnime() >= 0);
    }//end of the method testGetRecomendedAnimeCode

    /**
     * Test recue the code for the index thought of title and code anime
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void testRescueCodeIndex() throws SQLException, ClassNotFoundException {
        testIndex = new Index("test", 1000);
        indexDAO.addIndex(testIndex);
        assertTrue("Code rescued is not invalid!", indexDAO.rescueCodeIndex(testIndex) > 0);

    }//end of the method testRescueCodeIndex

    /**
     * Test if index is deleted
     */
    @Test
    public void testDeleteIndex() throws SQLException, ClassNotFoundException {
        testAddIdex();
        assertTrue("Could not delete anime!", indexDAO.deleteIndex(testIndex));
    }//end of the method testDeleteIndex

    /**
     * Fails in use closed connection
     *
     * @throws SQLException
     */
    @Test(expected = SQLException.class)
    public void failUseConnectionAfterClosed() throws SQLException, ClassNotFoundException {
        indexDAO.closeConnection();
        indexDAO.getIndexes();
        indexDAO = new IndexDAO();
    }//end of the method failUseConnectionAfterClosed

    /**
     * Fail to delete Index with codeIndex equals to zero
     *
     * @throws SQLException
     */
    @Test(expected = IllegalArgumentException.class)
    public void failsDeleteZeroCodeIndex() throws SQLException {
        indexDAO.deleteIndex(testIndex);
    }//end of the method failsDeleteZeroCodeIndex
}//end of the class IndexDAOTest
