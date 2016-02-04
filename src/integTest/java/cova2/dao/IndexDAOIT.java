package cova2.dao;

import cova2.exception.DataAlreadyRegisteredException;
import cova2.model.index.Index;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class IndexDAOIT {

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

    }

    /**
     * Test rescue the code for the index thought of title and code anime
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void testRescueCodeIndex() throws SQLException, ClassNotFoundException, DataAlreadyRegisteredException {
        testIndex = new Index("test", 1000);
        Index index = indexDAO.addIndex(testIndex);
        assertTrue("Code rescued    is not invalid!", indexDAO.rescueCodeIndex(testIndex) == index.getCodeIndex());
    }//end of the method testRescueCodeIndex

    /**
     * Test recommended anime code
     */
    @Test
    public void testGetRecomendedCodeAnime() throws SQLException, ClassNotFoundException, DataAlreadyRegisteredException {
        testIndex.setCodeAnime(1);
        testIndex = indexDAO.addIndex(testIndex);
        assertTrue("Recommended anime code generated " + indexDAO.getRecommendedCodeAnime() + " is invalid!", indexDAO.getRecommendedCodeAnime() == 2);
    }//end of the method testGetRecomendedAnimeCode

    /**
     * Test recommended anime code with empty database
     */
    @Test
    public void testEmptyGetRecomendedCodeAnime() throws SQLException, ClassNotFoundException {
        indexDAO = new IndexDAO();
        assertTrue("Recommended anime code generated with empty database is invalid!", indexDAO.getRecommendedCodeAnime() == 1);
    }//end of the method testGetRecomendedAnimeCode

    @Test
    public void testCloseConnection() throws SQLException, ClassNotFoundException {
        Connection connection = indexDAO.connect();
        indexDAO.closeConnection();
        assertTrue("The connection should be closed when we call closeConnection!", connection.isClosed());
        Connection c = indexDAO.connect();
    }

    @Test
    public void testInsertIndex() throws SQLException, DataAlreadyRegisteredException {

        Index index = indexDAO.addIndex(testIndex);
        assertNotNull("The index was not registered!", indexDAO.selectIndex(index.getCodeIndex()));
    }

    @Test
    public void testSelectIndexes() throws SQLException, DataAlreadyRegisteredException {

        Index index = indexDAO.addIndex(testIndex);
        assertTrue("Could not read index from database!", indexDAO.getIndexes().get(0).getCodeIndex() == index.getCodeIndex());

    }

    @Test
    public void testUpdateIndex() throws SQLException, DataAlreadyRegisteredException {
        testIndex.setCodeIndex(1);
        indexDAO.addIndex(testIndex);
        testIndex.setCodeAnime(2);
        indexDAO.updateIndex(testIndex);
        assertTrue("Could not update index from database!", indexDAO.getIndexes().get(0).getCodeAnime() == 2);

    }

    @Test
    public void testDeleteIndex() throws SQLException, DataAlreadyRegisteredException {
        testIndex.setCodeIndex(1);
        indexDAO.addIndex(testIndex);
        indexDAO.deleteIndex(testIndex);
        assertTrue("Could not delete index from database!", indexDAO.getIndexes().size() == 0);

    }

    @After
    public void eraseDatabase() throws SQLException {
        List<Index> indexes = indexDAO.getIndexes();
        for (Index index : indexes) {
            indexDAO.deleteIndex(index);
        }
        indexDAO.closeConnection();

        File dir = new File("data" + File.separator + "db");
        for (File file : dir.listFiles()) {
            file.delete();
        }
        dir.delete();
    }

}
