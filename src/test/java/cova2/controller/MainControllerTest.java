package cova2.controller;

import cova2.dao.AnimeDAO;
import cova2.dao.IndexDAO;
import cova2.model.anime.Anime;
import cova2.model.index.Index;
import cova2.view.tableModel.IndexTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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
        mainController = new MainController();
    }//end of test method initiate

    /**
     * Test start of interface
     */
    @Test
    public void testStartInterface() {
        assertTrue("The interface was not initialized!", mainController.isViewOpen());
    }//end of the test method testStartInterface

    /**
     * Test if get the indexes from database
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void testLoadData() throws SQLException, ClassNotFoundException {
        List<Index> indexes = mainController.loadIndexes();
        assertFalse("No index found!", indexes.isEmpty());
    }//end of the method testLoadData

    /**
     * Testa o carregamento de animes
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void testLoadAnimes() throws SQLException, ClassNotFoundException {
        List<Index> indexes = mainController.loadIndexes();
        List<Anime> animes = mainController.loadAnimes(indexes);
        assertFalse("No anime found!", animes.isEmpty());
    }//end of the method testLoadAnimes

    /**
     * Test table acess and data setting
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void testTable() throws SQLException, ClassNotFoundException {
        List<Index> indexes = mainController.loadIndexes();
        List<Anime> animes = mainController.loadAnimes(indexes);
        IndexTableModel tableModel = new IndexTableModel(indexes, animes);
        mainController.setTableModel(tableModel);
        assertNotNull("Could not get index from row!", mainController.getIndexRow(1));
        assertNotNull("Could not get anime from row!", mainController.getAnimeRow(1));

    }//end of the method testTable

    /**
     * Erase data
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @After
    public void eraseData() throws SQLException, ClassNotFoundException {
        IndexDAO indexDAO = new IndexDAO();
        testIndexes.forEach(index -> {
            try {
                indexDAO.deleteIndex(index);
            } catch (SQLException ex) {
                fail("Throws SQLException! " + ex.getMessage());
            }
        }
        );
        AnimeDAO animeDAO = new AnimeDAO();
        testAnimes.forEach(anime -> animeDAO.deleteAnime(anime.getCodeAnime()));
    }//end of the method eraseData
}//end of class MainControllerTest 
