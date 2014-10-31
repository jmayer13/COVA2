package cova2.dao;

import cova2.model.index.Index;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
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
    private final Index testIndex = new Index(1, "title", 0);

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
        boolean result = indexDAO.addIndex(testIndex);
        assertTrue("Problem when registering Index!", result);
    }//end of method testAddIdex

    /**
     * Test the getting of Indexes and if Index sent is the same received
     *
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testGetIndexes() throws SQLException, ClassNotFoundException {
        testAddIdex();
        List<Index> indexes = indexDAO.getIndexes();
        assertNotNull("Coult not get Indexes!", indexes);
        assertEquals("The Index sent is not the same received!", testIndex, indexes.get(0));
    }//end of method testGetIndexes

}//end of the class IndexDAOTest
