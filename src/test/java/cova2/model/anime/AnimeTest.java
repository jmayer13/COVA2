package cova2.model.anime;

import static org.junit.Assert.assertEquals;
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
        int currentEpisode = anime.getCurrentEpisode();
        assertEquals("The got episode does not correspond with the episode setted!", currentEpisode, 1);
    }//end of the test method testCurrentEpisode

}//fim da classe AnimeTest 
