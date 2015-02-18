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

import cova2.exception.DataAlreadyRegisteredException;
import cova2.exception.UnavailableDataException;
import cova2.model.anime.Anime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test AnimeDAO
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class AnimeDAOTest {

    private AnimeDAO animeDAO;

    /**
     * Initialize class tested
     */
    @Before
    public void initiate() {
        animeDAO = new AnimeDAO() {
            private List<Anime> animes = new ArrayList();

            @Override
            protected void insertAnime(Anime anime) {
                animes.add(anime);
            }

            @Override
            protected Anime selectAnime(int codeAnime) {
                for (Anime anime : animes) {
                    if (anime.getCodeAnime() == codeAnime) {
                        return anime;
                    }
                }
                return null;
            }

            @Override
            protected void updateAnime(Anime anime) {
                animes.add(anime);
            }

            @Override
            protected void deleteAnime(Anime anime) {
                animes.remove(anime);
            }

        };
    } //end of the test method initiate

    /*CREATE 
     -creation works
     -fail anime null
     -fail existing anime 
     /*
     /**
     * Test the creation of animes
     */
    @Test
    public void testCreateAnime() throws DataAlreadyRegisteredException {
        Anime anime = new Anime();
        anime.setCodeAnime(1);
        anime.setCurrentEpisode(42);
        animeDAO.createAnime(anime);
        assertTrue("The anime created don't have the same current episode!", animeDAO.selectAnime(1).getCurrentEpisode() == 42);
        animeDAO.deleteAnime(anime);
    }//end of the test method testAddAnime

    /**
     * Fail to create anime with null anime
     */
    @Test(expected = NullPointerException.class)
    public void failNullCreateAnime() throws DataAlreadyRegisteredException {
        animeDAO.createAnime(null);
    }//end of the method failNullCreateAnime

    /**
     * Fails registering an anime twice
     */
    @Test(expected = DataAlreadyRegisteredException.class)
    public void failExistingCreateAnime() throws DataAlreadyRegisteredException {
        Anime anime = new Anime();
        anime.setCodeAnime(1);
        anime.setCurrentEpisode(42);
        animeDAO.createAnime(anime);
        animeDAO.createAnime(anime);
        animeDAO.deleteAnime(anime);
    }//end of the method DataAlreadyRegisteredException

    /*
     READ
     -read a created anime
     -return null if code don't have an anime
     */
    /**
     * Test if reads the anime created
     */
    @Test
    public void testReadAnime() {
        Anime anime = new Anime();
        anime.setCodeAnime(1);
        anime.setCurrentEpisode(42);
        animeDAO.insertAnime(anime);
        Anime animeReaded = animeDAO.readAnime(1);
        animeDAO.deleteAnime(anime);
        assertEquals("the code of the anime get is not the same!", anime.getCodeAnime(), animeReaded.getCodeAnime());
    }//end of the test method testReadAnime

    /**
     * Fail reading a anime with ivalid code
     */
    @Test(expected = NullPointerException.class)
    public void failNullReadAnime() {
        Anime animeReaded = animeDAO.readAnime(100008);
        animeReaded.getCodeAnime();
    }//end of the test method testReadAnime

    /*ERASE
     -delete an anime
     -fail delete null
     -fails delete twice
     */
    /**
     * Test if erase the anime created
     */
    @Test
    public void testEraseAnime() throws UnavailableDataException {
        Anime anime = new Anime();
        anime.setCodeAnime(1);
        anime.setCurrentEpisode(42);
        animeDAO.insertAnime(anime);
        animeDAO.eraseAnime(anime);
        assertTrue("Anime was not deleted!", animeDAO.selectAnime(1) == null);
    }//end of the test method testEraseAnime

    /**
     * Test if erase the anime created
     */
    @Test(expected = UnavailableDataException.class)
    public void failsEraseInvalidAnime() throws UnavailableDataException {
        Anime anime = new Anime();
        anime.setCodeAnime(1);
        anime.setCurrentEpisode(42);
        animeDAO.insertAnime(anime);
        animeDAO.eraseAnime(anime);
        animeDAO.eraseAnime(anime);
    }//end of the test method failsEraseInvalidAnime

    /**
     * Test if erase the anime created
     */
    @Test(expected = NullPointerException.class)
    public void failsEraseNullAnime() throws UnavailableDataException {
        animeDAO.eraseAnime(null);
    }//end of the test method failsEraseNullAnime

    /*EDIT
     -Edit anime
     -fail null
     -fail invalid
     */
    /**
     * Test edit anime
     */
    @Test
    public void testEditAnime() throws DataAlreadyRegisteredException, UnavailableDataException {
        Anime anime = new Anime();
        anime.setCodeAnime(1);
        anime.setCurrentEpisode(0);
        animeDAO.insertAnime(anime);
        anime.setCurrentEpisode(1);
        animeDAO.editAnime(anime);
        assertTrue("The edition of anime was not effective!", animeDAO.readAnime(anime.getCodeAnime()).getCurrentEpisode() == 1);
    }//end of the method testEditAnime

    /**
     * Test if erase the anime created
     */
    @Test(expected = UnavailableDataException.class)
    public void failsEditInvalidAnime() throws DataAlreadyRegisteredException, UnavailableDataException {
        Anime anime = new Anime();
        anime.setCodeAnime(500);
        anime.setCurrentEpisode(42);
        animeDAO.editAnime(anime);
    }//end of the test method failsEditInvalidAnime

    /**
     * Test if erase the anime created
     */
    @Test(expected = NullPointerException.class)
    public void failsEditNullAnime() throws DataAlreadyRegisteredException, UnavailableDataException {
        animeDAO.editAnime(null);
    }//end of the test method failsEditInvalidAnime

    /* EDIT 
     -increase
     -decrease until -1
     -null 
     */
    /**
     * Test increase episode
     */
    @Test
    public void testIncreaseEpisode() throws DataAlreadyRegisteredException, UnavailableDataException {
        Anime anime = new Anime();
        anime.setCodeAnime(1);
        anime.setCurrentEpisode(0);
        animeDAO.insertAnime(anime);

        animeDAO.increaseEpisode(anime);
        assertTrue("The edition of anime was not effective!", animeDAO.selectAnime(anime.getCodeAnime()).getCurrentEpisode() == 1);
    }//end of the method testIncreaseEpisode

    /**
     * Test devrease episode
     */
    @Test
    public void testDecreaseEpisode() throws DataAlreadyRegisteredException, UnavailableDataException {
        Anime anime = new Anime();
        anime.setCodeAnime(1);
        anime.setCurrentEpisode(0);
        animeDAO.insertAnime(anime);

        animeDAO.decreaseEpisode(anime);
        assertTrue("The edition of anime was not effective!", animeDAO.selectAnime(anime.getCodeAnime()).getCurrentEpisode() == -1);
    }//end of the method testDecreaseEpisode

    /**
     * Test limited decrease episode
     */
    @Test
    public void testLimitDecreaseEpisode() throws DataAlreadyRegisteredException, UnavailableDataException {
        Anime anime = new Anime();
        anime.setCodeAnime(1);
        anime.setCurrentEpisode(0);
        animeDAO.insertAnime(anime);
        animeDAO.decreaseEpisode(anime);
        anime = animeDAO.readAnime(anime.getCodeAnime());
        animeDAO.decreaseEpisode(anime);
        anime = animeDAO.readAnime(anime.getCodeAnime());
        animeDAO.decreaseEpisode(anime);
        anime = animeDAO.readAnime(anime.getCodeAnime());
        animeDAO.decreaseEpisode(anime);
        assertTrue("The edition of anime was not effective!", animeDAO.selectAnime(anime.getCodeAnime()).getCurrentEpisode() == -1);
    }//end of the method testLimitDecreaseEpisode

    /**
     * Fails increase with null
     */
    @Test(expected = NullPointerException.class)
    public void failEmptyIncreaseEpisode() throws DataAlreadyRegisteredException, UnavailableDataException {
        animeDAO.increaseEpisode(null);
    }//end of the method

    /**
     * Fails decrease with null
     */
    @Test(expected = NullPointerException.class)
    public void failEmptyDecreaseEpisode() throws DataAlreadyRegisteredException, UnavailableDataException {
        animeDAO.decreaseEpisode(null);
    }//end of the method failEmptyDecreaseEpisode

    /**
     * Fail increase with invalid episode
     */
    @Test(expected = UnavailableDataException.class)
    public void failInvalidIncreaseEpisode() throws DataAlreadyRegisteredException, UnavailableDataException {
        Anime anime = new Anime();
        anime.setCodeAnime(0);
        animeDAO.increaseEpisode(anime);
    }//end of the method failInvalidIncreaseEpisode

    /**
     * Fail dencrease with invalid episode
     *
     * @throws cova2.exception.DataAlreadyRegisteredException
     */
    @Test(expected = UnavailableDataException.class)
    public void failInvalidDecreaseEpisode() throws DataAlreadyRegisteredException, UnavailableDataException {
        Anime anime = new Anime();
        anime.setCodeAnime(0);
        animeDAO.decreaseEpisode(anime);
    }//end of the method failInvalidDecreaseEpisode

}//end of the test class AnimeDAOTest
