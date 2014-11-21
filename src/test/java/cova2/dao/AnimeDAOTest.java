package cova2.dao;

import cova2.model.anime.Anime;
import java.io.File;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
        animeDAO = new AnimeDAO();
        File dir = new File("data" + File.separator + "anime");
        if (!dir.exists()) {
            dir.mkdirs();
        }
    } //end of the test method initiate

    /**
     * Test the creation of animes
     */
    @Test
    public void testCreateAnime() {
        Anime anime = new Anime();
        anime.setCodeAnime(1);
        anime.setCurrentEpisode(0);
        animeDAO.createAnime(anime);
    }//end of the test method testAddAnime

    /**
     * Test if reads the anime created
     */
    @Test
    public void testReadAnime() {
        testCreateAnime();
        Anime anime = animeDAO.readAnime(1);
        assertEquals("the code of the anime get is not the same!", anime.getCodeAnime(), 1);
    }//end of the test method testReadAnime

    /**
     * Test if deletes the anime created
     */
    @Test
    public void testDeleteAnime() {
        testCreateAnime();
        animeDAO.deleteAnime(1);
        assertFalse("Anime was not deleted!",
                (new File("data" + File.separator + "anime" + File.separator + "1.json")).exists());
    }//end of the test method testDeleteAnime

    /**
     * Clean database
     */
    @After
    public void cleanDataBase() {
        animeDAO.deleteAnime(1);
    }//end of the test method cleanDataBase

}//end of the test class AnimeDAOTest
