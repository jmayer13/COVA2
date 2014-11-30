package cova2.view.tableModel;

import cova2.model.anime.Anime;
import cova2.model.index.Index;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test IndexTavleModel
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class IndexTableModelTest {

    private IndexTableModel indexTableModel;

    /**
     * Create sets of data and initialize table model
     */
    @Before
    public void initialize() {
        Anime onePieceAnime = new Anime();
        onePieceAnime.setCodeAnime(21);
        onePieceAnime.setCurrentEpisode(600);
        Anime genAnime = new Anime();
        genAnime.setCodeAnime(240);
        genAnime.setCurrentEpisode(2);
        List<Anime> animes = new ArrayList();
        animes.add(onePieceAnime);
        animes.add(genAnime);

        Index onePieceIndex = new Index(1, "One Piece", 21);
        Index genshikenIndex = new Index(2, "Genshiken", 240);
        List<Index> indexes = new ArrayList();
        indexes.add(onePieceIndex);
        indexes.add(genshikenIndex);
        indexTableModel = new IndexTableModel(indexes, animes);

    }//end of the method initialize

    /**
     * Test get back index of the tablemodel
     */
    @Test
    public void testGetIndex() {
        assertTrue("The index is not the same.", indexTableModel.getIndex(0).getCodeIndex() == 1);
    }//end of the method testGetIndex

    /**
     * Test if getValueAt and getIndex match results
     */
    @Test
    public void testMatchIndex() {
        assertTrue("Index don't match!", indexTableModel.getIndex(0).getMainTitleAnime().equals(String.valueOf(indexTableModel.getValueAt(0, 0))));
    }//end of the method testMatchIndex

    /**
     * Test get back anime of the tablemodel
     */
    @Test
    public void testGetAnime() {
        assertTrue("The anime is not the same.", indexTableModel.getAnime(0).getCodeAnime() == 21);
    }//end of the method testGetAnime

    /**
     * Test if getValueAt and getAnime match results
     */
    @Test
    public void testMatchAnime() {
        assertTrue("Anime don't match!", indexTableModel.getAnime(0).getCurrentEpisode()
                == Integer.valueOf(String.valueOf(indexTableModel.getValueAt(0, 1))));
    }//end of the method testMatchAnime

}//end of the method IndexTableModelTest
