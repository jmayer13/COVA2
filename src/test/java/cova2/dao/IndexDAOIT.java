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
package cova2.dao;

import cova2.model.index.Index;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
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
     * Test recue the code for the index thought of title and code anime
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void testRescueCodeIndex() throws SQLException, ClassNotFoundException {
        testIndex = new Index("test", 1000);
        indexDAO.addIndex(testIndex);
        assertTrue("Code rescued is not invalid!", indexDAO.rescueCodeIndex(testIndex) == 1);
    }//end of the method testRescueCodeIndex

    /**
     * Test recomended anime code
     */
    @Test
    public void testGetRecomendedCodeAnime() throws SQLException, ClassNotFoundException {
        testIndex.setCodeAnime(1);
        testIndex = indexDAO.addIndex(testIndex);
        assertTrue("Recommended anime code generated is invalid!", indexDAO.getRecommendedCodeAnime() == 2);
    }//end of the method testGetRecomendedAnimeCode

    /**
     * Test recomended anime code with empty database
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

    }

    @Test
    public void testInsertIndex() throws SQLException {
        testIndex.setCodeIndex(1);
        indexDAO.addIndex(testIndex);
        assertTrue("The index was not registered!", indexDAO.getIndexes().size() == 1);
    }

    @Test
    public void testSelectIndexes() throws SQLException {
        testIndex.setCodeIndex(1);
        indexDAO.addIndex(testIndex);
        assertTrue("Could not read index from database!", indexDAO.getIndexes().get(0).getCodeIndex() == 1);

    }

    @Test
    public void testUpdateIndex() throws SQLException {
        testIndex.setCodeIndex(1);
        indexDAO.addIndex(testIndex);
        testIndex.setCodeAnime(2);
        indexDAO.updateIndex(testIndex);
        assertTrue("Could not update index from database!", indexDAO.getIndexes().get(0).getCodeAnime() == 2);

    }

    @Test
    public void testDeleteIndex() throws SQLException {
        testIndex.setCodeIndex(1);
        indexDAO.addIndex(testIndex);
        indexDAO.deleteIndex(testIndex);
        assertTrue("Could not delete index from database!", indexDAO.getIndexes().size() == 0);

    }

    @After
    public void eraseDatabase() throws SQLException {
        indexDAO.closeConnection();
        File dir = new File("data" + File.separator + "db");
        for (File file : dir.listFiles()) {
            file.delete();
        }
        dir.delete();
    }

}
