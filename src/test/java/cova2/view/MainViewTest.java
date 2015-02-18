package cova2.view;

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
 * Test view MainView
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class MainViewTest {

    private MainView mainFrame;
    private List<Index> testIndexes;
    private List<Anime> testAnimes;

    /**
     * Initialize class tested
     */
    @Before
    public void initialize() {
        testIndexes = new ArrayList();
        testAnimes = new ArrayList();
        //set some data
        Index onePieceIndex = new Index("One Piece", 21);
        onePieceIndex.setCodeIndex(1);
        Anime onePieceAnime = new Anime();
        onePieceAnime.setCodeAnime(21);
        onePieceAnime.setCurrentEpisode(600);
        Index genshikenIndex = new Index("Genshiken", 240);
        genshikenIndex.setCodeIndex(2);
        Anime genAnime = new Anime();
        genAnime.setCodeAnime(240);
        genAnime.setCurrentEpisode(2);

        testIndexes.add(onePieceIndex);
        testIndexes.add(genshikenIndex);
        testAnimes.add(onePieceAnime);
        testAnimes.add(genAnime);
        mainFrame = new MainView();
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
     * Test getAnime
     */
    @Test
    public void testGetIndex() {
        mainFrame.setTableModel(new IndexTableModel(testIndexes, testAnimes) {
            @Override
            public Index getIndex(int i) {
                Index index = new Index("a", 45);
                index.setCodeIndex(45);
                return index;
            }
        });
        assertTrue("Get index don't works!", mainFrame.getIndex(0).getCodeIndex() == 45);
    }//end of the method getIndex

    /**
     * Test getAnime
     */
    @Test
    public void testGetAnime() {
        mainFrame.setTableModel(new IndexTableModel(testIndexes, testAnimes) {
            @Override
            public Anime getAnime(int i) {
                Anime anime = new Anime();
                anime.setCodeAnime(45);
                return anime;
            }
        });
        assertTrue("Get anime don't works!", mainFrame.getAnime(0).getCodeAnime() == 45);
    }//end of the method getAnime

}//end of the test class MainViewTest
