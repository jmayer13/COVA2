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

import cova2.model.anime.Anime;
import java.io.File;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class AnimeDAOIT {

    private AnimeDAO animeDAO;

    /**
     * Initialize class tested
     */
    @Before
    public void initiate() {
        animeDAO = new AnimeDAO();
    }

    @Test
    public void testInsertAnime() {
        Anime anime = new Anime();
        anime.setCodeAnime(1);
        anime.setCurrentEpisode(1);
        animeDAO.insertAnime(anime);
        assertTrue("The anime was not registered correctly!", (new File("data" + File.separator + "anime" + File.separator + "1.json")).exists());
    }

    @Test
    public void testSelectAnime() {
        Anime anime = new Anime();
        anime.setCodeAnime(1);
        anime.setCurrentEpisode(1);
        animeDAO.insertAnime(anime);
        assertTrue("The anime was not getted correctly!", animeDAO.selectAnime(anime.getCodeAnime()).getCodeAnime() == 1);

    }

    @Test
    public void testUpdateAnime() {
        Anime anime = new Anime();
        anime.setCodeAnime(1);
        anime.setCurrentEpisode(1);
        animeDAO.insertAnime(anime);
        anime.setCurrentEpisode(2);
        animeDAO.updateAnime(anime);
        assertTrue("The anime was not registered correctly!", animeDAO.selectAnime(anime.getCodeAnime()).getCurrentEpisode() == 2);

    }

    @Test
    public void testDeleteAnime() {
        Anime anime = new Anime();
        anime.setCodeAnime(1);
        anime.setCurrentEpisode(1);
        animeDAO.insertAnime(anime);
        animeDAO.eraseAnime(anime);
        assertFalse("Could not delete anime!", (new File("data" + File.separator + "anime" + File.separator + "1.json")).exists());
    }

    @After
    public void eraseDataBase() {
        File dir = new File("data" + File.separator + "anime");
        for (File file : dir.listFiles()) {
            file.delete();
        }
        dir.delete();
    }
}//fim da classe AnimeDAOIT 
