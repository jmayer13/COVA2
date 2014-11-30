package cova2.view;

import cova2.model.anime.Anime;
import cova2.model.index.Index;
import cova2.view.tableModel.IndexTableModel;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test view MainFrame
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class MainFrameTest {

    private MainFrame mainFrame;

    /**
     * Initialize class tested
     */
    @Before
    public void initialize() {
        mainFrame = new MainFrame();
    }//end of the test method initiate

    /**
     * Test if the check for frame open works
     */
    @Test
    public void testFrameOpen() {
        assertTrue("The frame should be opened!", mainFrame.isOpen());
    }//end of the test method testFrameOpen

    /**
     * Test if when close interface the check is false
     */
    @Test
    public void testFrameClose() {
        mainFrame.close();
        assertFalse("The frame should be closed!", mainFrame.isOpen());
    }//end of the test method testFrameClose

    /**
     * Set table model with data and tests the getindex
     */
    @Test
    public void testSetTableModel() {
        Anime onePieceAnime = new Anime();
        onePieceAnime.setCodeAnime(21);
        onePieceAnime.setCurrentEpisode(600);
        List<Anime> animes = new ArrayList();
        animes.add(onePieceAnime);
        Index onePieceIndex = new Index(1, "One Piece", 21);
        List<Index> indexes = new ArrayList();
        indexes.add(onePieceIndex);
        IndexTableModel indexTableModel = new IndexTableModel(indexes, animes);

        mainFrame.setTableModel(indexTableModel);
        assertTrue("Table Model setted don't works!", mainFrame.getIndex(0).getCodeIndex() == onePieceIndex.getCodeIndex());
        assertTrue("Table Model setted don't works!", mainFrame.getAnime(0).getCodeAnime() == onePieceIndex.getCodeAnime());
    }//end of the method testSetTableModel

}//end of the test class MainFrameTest
