package cova2.dao;

import cova2.dao.scala.CreateAnime;
import cova2.dao.scala.DeleteAnime;
import cova2.dao.scala.SearchAnime;
import cova2.model.anime.Anime;

/**
 * Anime DAO - connect with Scala classes
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class AnimeDAO {

    /**
     * Create anime
     *
     * @param anime anime to be stored
     */
    public void createAnime(Anime anime) {
        CreateAnime createAnime = new CreateAnime();
        createAnime.createAnime(anime);
    }//end of the method createAnime

    /**
     * Read anime file
     *
     * @param codeAnime
     * @return <code>Anime</code> anime readed
     */
    public Anime readAnime(int codeAnime) {
        SearchAnime searchAnime = new SearchAnime();
        return searchAnime.searchCodeAnime(String.valueOf(codeAnime));
    }//end of the method readAnime

    /**
     * Delete anime
     *
     * @param codeAnime code of the anime to be deleted
     */
    public void deleteAnime(int codeAnime) {
        DeleteAnime deleteAnime = new DeleteAnime();
        deleteAnime.delete(String.valueOf(codeAnime));
    }//end of the method deleteAnime

}//end of the class AnimeDAO 
