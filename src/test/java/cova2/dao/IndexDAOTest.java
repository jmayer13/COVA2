package cova2.dao;

import cova2.model.index.Index;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Test to IndexDAO, that control the persistence of IndexDB
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class IndexDAOTest {

    private IndexDAO indexDAO;
    //index generic for tests
    private final Index testIndex = new Index("title", 0);

    /**
     * Clean the test data
     *
     * @throws SQLException if script don't works
     */
    @After
    public void cleanData() throws SQLException {
        indexDAO.deleteIndex(testIndex);
    }//end of the method cleanData

    /**
     * Test registration of Index
     *
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testAddIdex() throws SQLException, ClassNotFoundException {
        indexDAO = new IndexDAO();
        Index index = indexDAO.addIndex(testIndex);
        assertTrue("Problem when registering Index!", index.getCodeIndex() > 0);
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
        indexDAO = new IndexDAO();
        indexDAO.addIndex(new Index("test", indexDAO.getRecommendedCodeAnime()));
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
        indexDAO = new IndexDAO();
        Index testIndex = new Index("test", 1000);
        indexDAO.addIndex(testIndex);
        assertTrue("Code rescued is not invalid!", indexDAO.rescueCodeIndex(testIndex) > 0);

    }//end of the method testRescueCodeIndex

}//end of the class IndexDAOTest
