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
package cova2.controller;

import cova2.dao.AnimeDAO;
import cova2.dao.IndexDAO;
import cova2.model.anime.Anime;
import cova2.model.index.Index;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Class test MainController
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class MainControllerTest {

    private MainController mainController;
    private List<Index> testIndexes;
    private List<Anime> testAnimes;

    /**
     * Initiaze class tested and data
     *
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    @Before
    public void initiaze() throws SQLException, ClassNotFoundException {
        testIndexes = new ArrayList();
        testAnimes = new ArrayList();
        //set some data
        Index onePieceIndex = new Index("One Piece", 21);
        Anime onePieceAnime = new Anime();
        onePieceAnime.setCodeAnime(21);
        onePieceAnime.setCurrentEpisode(600);
        Index genshikenIndex = new Index("Genshiken", 240);
        Anime genAnime = new Anime();
        genAnime.setCodeAnime(240);
        genAnime.setCurrentEpisode(2);

        IndexDAO indexDAO = new IndexDAO();
        testIndexes.add(indexDAO.addIndex(onePieceIndex));
        testIndexes.add(indexDAO.addIndex(genshikenIndex));
        AnimeDAO animeDAO = new AnimeDAO();
        animeDAO.createAnime(onePieceAnime);
        animeDAO.createAnime(genAnime);
        testAnimes.add(onePieceAnime);
        testAnimes.add(genAnime);
        mainController = new MainController() {
            @Override
            public AddAnimeController openAddAnimeController() {
                return null;
            }

            @Override
            public List<Index> loadIndexes() throws SQLException, ClassNotFoundException {
                return testIndexes;
            }

            @Override
            public List<Anime> loadAnimes(List<Index> indexes) {
                return testAnimes;
            }

            @Override
            public void decreaseEpisode(Anime animeRow) {
                for (Anime anime : testAnimes) {
                    if (anime.getCodeAnime() == animeRow.getCodeAnime()) {
                        anime.setCurrentEpisode(anime.getCurrentEpisode() - 1);
                    }
                }
            }

            @Override
            public void increaseEpisode(Anime animeRow) {
                for (Anime anime : testAnimes) {
                    if (anime.getCodeAnime() == animeRow.getCodeAnime()) {
                        anime.setCurrentEpisode(anime.getCurrentEpisode() + 1);
                    }
                }
            }

            @Override
            public void deleteAnime(Index indexRegistered) {
                testIndexes.remove(indexRegistered);
                for (int i = 0; i > testAnimes.size(); i++) {
                    if (testAnimes.get(i).getCodeAnime() == indexRegistered.getCodeIndex()) {
                        testAnimes.remove(i);
                    }
                }
            }

            @Override
            public void closeConnection() {

            }

        };
    }//end of test method initiate

    @Test
    public void testUpdateData() {
        int sizeBefore = testIndexes.size();
        testIndexes.remove(0);
        mainController.updateData();
        assertNull("This element should be null!", mainController.getIndexRow(sizeBefore));
    }

    @Test
    public void testIsViewOpen() {
        assertTrue("View should be open!", mainController.isViewOpen());
    }

    @Test
    public void testCloseView() throws SQLException, ClassNotFoundException {
        mainController.closeView();
        assertFalse("View should not be open!", mainController.isViewOpen());
    }

    @Test
    public void testGetIndexRow() {
        assertTrue("The index get is not the same!", mainController.getIndexRow(0) == testIndexes.get(0));
    }

    @Test
    public void testGetAnimeRow() {
        assertTrue("The anime get is not the same!", mainController.getAnimeRow(0) == testAnimes.get(0));
    }

    /**
     * Test open editAnimeView
     */
    @Test
    public void testOpenEditAnimeView() {
        AddAnimeController addAnimeController = mainController.editAnime(mainController.getIndexRow(0), mainController.getAnimeRow(0));
        assertTrue("Couldn't open edit anime view!", addAnimeController.getView().isOpen() && addAnimeController.getView().isValid());
    }//end of the method testOpenEditAnimeView

    /**
     * Test increase episode
     */
    @Test
    public void testIncreaseEpisode() {
        mainController.increaseEpisode(mainController.getAnimeRow(0));
        assertTrue("Didn't increase the episode!", mainController.getAnimeRow(0).getCurrentEpisode() == 601);
    }//end of the method testIncreaseEpisode

    /**
     * Test decrease episode
     */
    @Test
    public void testDecreaseEpisode() {
        mainController.decreaseEpisode(mainController.getAnimeRow(0));
        assertTrue("Didn't decrease the episode!", mainController.getAnimeRow(0).getCurrentEpisode() == 599);
    }//end of the method testDecreaseEpisode

    /**
     * Test select row and get sellected row
     */
    @Test
    public void testSelectRow() {
        mainController.selectRow(0);
        assertTrue("The row selected is not the same get!", mainController.getIndexRowSelected() == 0);
    }//end of the method testSelectRow

    /**
     * Test delete row
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void testDeleteRow() throws SQLException, ClassNotFoundException {
        mainController.deleteAnime(testIndexes.get(testIndexes.size() - 1));
        assertNull("The anime was not deleted", mainController.getIndexRow(testIndexes.size() - 1));
    }//end of the method testDeleteRow
}//end of class MainControllerTest 
