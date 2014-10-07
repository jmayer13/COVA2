package cova2.dao;

import cova2.model.index.Index;
import java.util.List;
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
     * Test registration of Index
     */
    @Test
    public void testAddIdex() {
        indexDAO = new IndexDAO();
        boolean result = indexDAO.addIndex(testIndex);
        assertTrue("Problem when registering Index!", result);
    }//end of method testAddIdex

    /**
     * Test the getting of Indexes and if Index sent is the same received
     */
    @Test
    public void testGetIndexes() {
        testAddIdex();
        List<Index> indexes = indexDAO.getIndexes();
        assertNotNull("Coult not get Indexes!", indexes);
        assertEquals("The Index sent is not the same received!", testIndex, indexes.get(0));
    }//end of method testGetIndexes

}//end of the class IndexDAOTest
