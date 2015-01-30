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

import cova2.exception.DataAlreadyRegisteredException;
import cova2.exception.UnavailableDataException;
import cova2.model.index.Index;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
        indexDAO = new IndexDAO() {
            private List<Index> indexes = new ArrayList();
            private int codeIndexCount = 0;

            @Override
            public int rescueCodeIndex(Index index) {
                codeIndexCount++;
                return codeIndexCount;
            }

            @Override
            public int getRecommendedCodeAnime() {
                codeIndexCount++;
                return codeIndexCount;
            }

            @Override
            public void closeConnection() {
            }

            @Override
            protected Connection connect() throws SQLException, ClassNotFoundException {
                return null;
            }

            public int insertIndex(Index index) {
                indexes.add(index);
                return 1;
            }

            @Override
            public List<Index> selectIndexes() {
                return indexes;
            }

            @Override
            public void updateIndex(Index index) {
                for (int i = 0; i < indexes.size(); i++) {
                    if (indexes.get(i).getCodeIndex() == index.getCodeIndex()) {
                        indexes.set(i, index);
                    }
                }
            }

            @Override
            public int deleteIndex(Index index) {
                indexes.remove(index);
                return 1;
            }

        };
        testIndex = new Index("title", 0);
    }//end of the method initialize

    /*CREATE
     -create index
     -fail null
     -fail exist
     */
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
        indexDAO.deleteIndex(testIndex);
    }//end of method testAddIdex

    @Test(expected = NullPointerException.class)
    public void failCreateNullIndex() throws SQLException {
        testIndex = indexDAO.addIndex(null);
    }

    @Test(expected = DataAlreadyRegisteredException.class)
    public void faildCreateExistIndex() throws SQLException {
        testIndex = indexDAO.addIndex(testIndex);
        testIndex = indexDAO.addIndex(testIndex);
        indexDAO.deleteIndex(testIndex);
    }

    /*GET
     -get indexes
     */
    /**
     * Test the getting of Indexes
     *
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @Test
    public void testGetIndexes() throws SQLException, ClassNotFoundException {
        testIndex = indexDAO.addIndex(testIndex);
        List<Index> indexes = indexDAO.getIndexes();
        assertNotNull("Coult not get Indexes!", indexes);
    }//end of method testGetIndexes

    /*DELETE
     -delete index
     -fail null
     -fail inexistent
     -invalid
     */
    /**
     * Test if index is deleted
     */
    @Test
    public void testEraseIndex() throws SQLException, ClassNotFoundException {
        testIndex = indexDAO.addIndex(testIndex);
        assertTrue("Could not delete anime!", indexDAO.eraseIndex(testIndex));
    }//end of the method testDeleteIndex

    /**
     * Fail to delete Index with codeIndex equals to zero
     *
     * @throws SQLException
     */
    @Test(expected = IllegalArgumentException.class)
    public void failsEraseZeroCodeIndex() throws SQLException {
        indexDAO.eraseIndex(testIndex);
    }//end of the method failsDeleteZeroCodeIndex

    @Test(expected = NullPointerException.class)
    public void failEraseNullIndex() throws SQLException {
        indexDAO.eraseIndex(null);
    }

    @Test(expected = UnavailableDataException.class)
    public void failEraseUnregisteredData() throws SQLException {
        testIndex = indexDAO.addIndex(testIndex);
        indexDAO.eraseIndex(testIndex);
        indexDAO.eraseIndex(testIndex);
    }
    /*EDIT
     -edit index
     -null
     -index that don't exist
     */

    /**
     * Test edit index with the index registered
     *
     * @throws SQLException
     */
    @Test
    public void testEditIndex() throws SQLException {
        testIndex = indexDAO.addIndex(testIndex);
        testIndex.setCodeAnime(5);
        indexDAO.editIndex(testIndex);
        assertTrue("The edition was not effective!", indexDAO.getIndexes().get(0).getCodeAnime() == 5);
        indexDAO.deleteIndex(testIndex);
    }//end of the method testEditIndex

    @Test(expected = NullPointerException.class)
    public void failEditNullIndex() throws SQLException {
        indexDAO.editIndex(null);
    }

    @Test(expected = UnavailableDataException.class)
    public void failEditUnexistentIndex() throws SQLException {
        indexDAO.editIndex(new Index(58, "a", 12));
    }

}//end of the class IndexDAOTest
