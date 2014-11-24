package cova2.controller;

import cova2.dao.AnimeDAO;
import cova2.dao.IndexDAO;
import cova2.model.anime.Anime;
import cova2.model.index.Index;
import cova2.view.tableModel.IndexTableModel;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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

    /**
     * Initiate class tested
     */
    @Before
    public void initiate() throws SQLException, ClassNotFoundException {
        mainController = new MainController();
        //set some data
        Index onePieceIndex = new Index(1, "One Piece", 21);
        Anime onePieceAnime = new Anime();
        onePieceAnime.setCodeAnime(21);
        onePieceAnime.setCurrentEpisode(600);
        Index genshikenIndex = new Index(2, "Genshiken", 240);
        Anime genAnime = new Anime();
        genAnime.setCodeAnime(240);
        genAnime.setCurrentEpisode(2);

        IndexDAO indexDAO = new IndexDAO();
        indexDAO.addIndex(onePieceIndex);
        indexDAO.addIndex(genshikenIndex);
        AnimeDAO animeDAO = new AnimeDAO();
        animeDAO.createAnime(onePieceAnime);
        animeDAO.createAnime(genAnime);

    }//end of test method initiate

    /**
     * Test start of interface
     */
    @Test
    public void testStartInterface() {
        assertTrue("The interface was not initialized!", mainController.isViewOpen());
    }//end of the test method testStartInterface

    @Test
    public void testLoadData() throws SQLException, ClassNotFoundException {
        List<Index> indexes = mainController.loadIndexes();
        assertFalse("No index found!", indexes.isEmpty());
    }

    @Test
    public void testLoadAnimes() throws SQLException, ClassNotFoundException {
        List<Index> indexes = mainController.loadIndexes();
        List<Anime> animes = mainController.loadAnimes(indexes);
        assertFalse("No anime found!", animes.isEmpty());
    }

    @Test
    public void testTable() throws SQLException, ClassNotFoundException {
        List<Index> indexes = mainController.loadIndexes();
        List<Anime> animes = mainController.loadAnimes(indexes);
        IndexTableModel tableModel = new IndexTableModel(indexes, animes);
        mainController.setTableModel(tableModel);
        assertNotNull("Could not get value from row!", mainController.getIndexRow(1));
    }

    @After
    public void eraseData() throws SQLException, ClassNotFoundException {
        Index onePieceIndex = new Index(1, "One Piece", 21);
        Anime onePieceAnime = new Anime();
        onePieceAnime.setCodeAnime(21);
        onePieceAnime.setCurrentEpisode(600);
        Index genshikenIndex = new Index(2, "Genshiken", 240);
        Anime genAnime = new Anime();
        genAnime.setCodeAnime(240);
        genAnime.setCurrentEpisode(2);

        IndexDAO indexDAO = new IndexDAO();
        indexDAO.deleteIndex(onePieceIndex);
        indexDAO.deleteIndex(genshikenIndex);

        AnimeDAO animeDAO = new AnimeDAO();
        animeDAO.deleteAnime(onePieceIndex.getCodeAnime());
        animeDAO.deleteAnime(genshikenIndex.getCodeAnime());
    }
}//end of class MainControllerTest 
