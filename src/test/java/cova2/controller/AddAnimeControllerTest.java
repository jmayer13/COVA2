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

import cova2.model.anime.Anime;
import cova2.model.index.Index;
import cova2.util.Observer;
import cova2.util.Subject;
import cova2.view.AddAnimeView;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test to controller for anime view
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class AddAnimeControllerTest {

    private AddAnimeController addAnimeController;

    //test double to obeserver
    private class ObserverTest implements Observer {

        public boolean notified = false;

        @Override
        public void update(Subject subject, Object object) {
            notified = true;
        }
    }
    private ObserverTest observerTest;
    private List<Anime> testAnimes;
    private List<Index> testIndexes;

    /**
     * Initialize class tested
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Before
    public void initialize() throws SQLException, ClassNotFoundException {
        testAnimes = new ArrayList();
        testIndexes = new ArrayList();

        //test double create a fake DB that stores temp data
        addAnimeController = new AddAnimeController() {

            @Override
            public Index registerAnime(Index index, Anime anime) throws SQLException {
                testAnimes.add(anime);
                testIndexes.add(index);
                return index;
            }

            @Override
            protected void editAnime(Index index, Anime anime) throws SQLException {
                for (int i = 0; i < testAnimes.size(); i++) {
                    if (testAnimes.get(i).getCodeAnime() == anime.getCodeAnime()) {
                        testAnimes.set(i, anime);
                    }
                }
                for (int i = 0; i < testIndexes.size(); i++) {
                    if (testIndexes.get(i).getCodeIndex() == index.getCodeIndex()) {
                        testIndexes.set(i, index);
                    }
                }
            }

            @Override
            public int getRecommendedCodeAnime() throws SQLException, ClassNotFoundException {
                return 1;
            }

            @Override
            protected void closeConnections() {
            }
        };
        observerTest = new ObserverTest();
    }//end of the method initialize

    /**
     * Test add anime
     */
    public void testAddAnime() {
        Index index = new Index("", 1);
        index.setCodeIndex(1);
        Anime anime = new Anime();
        anime.setCodeAnime(1);
        anime.setCurrentEpisode(0);
        AddAnimeView addAnimeView = addAnimeController.getView();
        addAnimeView.setTitleAnime(index.getMainTitleAnime());
        addAnimeView.setCurrentEpisode(anime.getCurrentEpisode());
        addAnimeController.okAction();
        assertTrue("The anime should be registered", testAnimes.size() > 0);
    }//end of the method testAddAnime

    /**
     * Test edit anime
     */
    public void testEditAnime() {
        Index index = new Index("", 1);
        index.setCodeIndex(1);
        testIndexes.add(index);
        Anime anime = new Anime();
        anime.setCodeAnime(1);
        anime.setCurrentEpisode(0);
        testAnimes.add(anime);
        addAnimeController.setIndexAnime(index, anime);
        addAnimeController.okAction();
        assertTrue("The anime should be registered", testAnimes.size() > 0);
    }//end of the method testEditAnime

    /**
     * Close View
     */
    @Test
    public void close() {
        addAnimeController.closeView();
        assertFalse("The view should be closed.", addAnimeController.getView().isOpen());
    }//end of the method close

    //Subject
    /**
     * Test add and notify methods
     */
    @Test
    public void testAddNotifyObserver() {
        addAnimeController.addObserver(observerTest);
        addAnimeController.notifyObservers();
        assertTrue("Observer not added!", observerTest.notified);
    }//end of the method testAddNotifyObserver

    /**
     * Test remove object
     */
    @Test
    public void removeObserver() {
        addAnimeController.addObserver(observerTest);
        addAnimeController.deleteObserver(observerTest);
        addAnimeController.notifyObservers();
        assertFalse("Observer not removed!", observerTest.notified);
    }//end of the method removeObserver

}//end of the class AddAnimeControllerTest 
