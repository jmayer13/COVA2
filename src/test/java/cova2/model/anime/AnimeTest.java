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
package cova2.model.anime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class Anime
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class AnimeTest {

    //instance tested
    private Anime anime;

    /**
     * Initialize the class
     */
    @Before
    public void initiaze() {
        anime = new Anime();
    }//end of the test method initiate

    /**
     * Test anime set and get
     */
    @Test
    public void testCodeAnime() {
        anime.setCodeAnime(1);
        int codeAnime = anime.getCodeAnime();
        assertEquals("The got code does not correspond with the code setted!", codeAnime, 1);
    }//end of the test method testCodeAnime

    /**
     * Test current episode set and get
     */
    @Test
    public void testCurrentEpisode() {
        anime.setCurrentEpisode(1);
        double currentEpisode = anime.getCurrentEpisode();
        assertTrue("The got episode does not correspond with the episode setted!", currentEpisode == 1);
    }//end of the test method testCurrentEpisode

}//end of the AnimeTest 
