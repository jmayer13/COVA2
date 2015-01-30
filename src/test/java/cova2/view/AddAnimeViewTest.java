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
package cova2.view;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test view to add and edit animes AddAnimeView
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class AddAnimeViewTest {

    private AddAnimeView addAnimeView;

    /**
     * Initialize instance of view
     */
    @Before
    public void initialize() {
        addAnimeView = new AddAnimeView();
    }//end of the method initialize

    /**
     * Test if the check for frame open works
     */
    @Test
    public void testFrameOpen() {
        assertTrue("The frame should be opened!", addAnimeView.isOpen());
    }//end of the test method testFrameOpen

    /**
     * Test if when close interface the check is false
     */
    @Test
    public void testFrameClose() {
        addAnimeView.close();
        assertFalse("The frame should be closed!", addAnimeView.isOpen());
    }//end of the test method testFrameClose

    /**
     * Test data validation, in case positive
     */
    @Test
    public void testIsValid() {
        addAnimeView.setTitleAnime("Bleach");
        addAnimeView.setCurrentEpisode(1);
        assertTrue("This form should be valid!", addAnimeView.isValid());
    }//end of the method testIsValid

    /**
     * Test data validation, in case negative
     */
    @Test
    public void testIsNotValid() {
        assertFalse("A empty form should not be valid!", addAnimeView.isValid());
    }//end of the method testIsNotValid

    /**
     * Test set and get of current episode
     */
    @Test
    public void testSetGetCurrentEpisode() {
        addAnimeView.setCurrentEpisode(0);
        assertTrue(addAnimeView.getCurrentEpisode() == 0);
    }//end of the method testSetGetCurrentEpisode

    /**
     * Test set and get of testSetGetTitleAnime
     */
    @Test
    public void testSetGetTitleAnime() {
        addAnimeView.setTitleAnime("test");
        assertTrue(addAnimeView.getTitleAnime().equals("test"));
    }//end of the method testSetGetTitleAnime

}//end of the test class AddAnimeViewTest
