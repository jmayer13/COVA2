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

import cova2.dao.scala.CreateAnime;
import cova2.dao.scala.DeleteAnime;
import cova2.dao.scala.SearchAnime;
import cova2.exception.DataAlreadyRegisteredException;
import cova2.exception.UnavailableDataException;
import cova2.model.anime.Anime;
import cova2.util.LogManager;

/**
 * Anime DAO - connect with Scala classes
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class AnimeDAO {

    private LogManager logManager;

    /**
     * Constructor without paramenters
     */
    public AnimeDAO() {
        logManager = new LogManager(AnimeDAO.class.getName());
    }//end of the constructor

    /**
     * Create anime
     *
     * @param anime anime to be stored
     */
    public void createAnime(Anime anime) throws DataAlreadyRegisteredException {
        logManager.info("Creating anime ...");
        if (anime == null) {
            throw new NullPointerException("The Anime is null!");
        }
        if (selectAnime(anime.getCodeAnime()) != null) {
            throw new DataAlreadyRegisteredException();
        }
        insertAnime(anime);
    }//end of the method createAnime

    /**
     * Read anime file
     *
     * @param codeAnime
     * @return <code>Anime</code> anime readed
     */
    public Anime readAnime(int codeAnime) {
        logManager.info("Reading anime with code " + codeAnime);
        return selectAnime(codeAnime);
    }//end of the method readAnime

    /**
     * Delete anime
     *
     * @param codeAnime code of the anime to be deleted
     */
    public void eraseAnime(Anime anime) throws UnavailableDataException {
        logManager.info("Deleting anime with code " + anime.getCodeAnime());
        if (selectAnime(anime.getCodeAnime()) == null) {
            throw new UnavailableDataException();
        }
        deleteAnime(anime);
    }//end of the method deleteAnime

    public void editAnime(Anime anime) throws DataAlreadyRegisteredException, UnavailableDataException {
        logManager.info("Editing anime ...");
        if (anime == null) {
            throw new NullPointerException("The Anime is null!");
        }
        if (selectAnime(anime.getCodeAnime()) == null) {
            throw new UnavailableDataException();
        }
        updateAnime(anime);
    }

    public void increaseEpisode(Anime anime) throws DataAlreadyRegisteredException, UnavailableDataException {
        double episode = anime.getCurrentEpisode() + 1;
        anime.setCurrentEpisode(episode);
        if (selectAnime(anime.getCodeAnime()) == null) {
            throw new UnavailableDataException();
        }
        editAnime(anime);
    }

    public void decreaseEpisode(Anime anime) throws DataAlreadyRegisteredException, UnavailableDataException {
        double episode = anime.getCurrentEpisode();
        if (episode < 0) {
            episode = -1;
        } else {
            episode--;
        }
        anime.setCurrentEpisode(episode);
        if (selectAnime(anime.getCodeAnime()) == null) {
            throw new UnavailableDataException();
        }
        editAnime(anime);
    }

    protected void insertAnime(Anime anime) {
        CreateAnime createAnime = new CreateAnime();
        createAnime.createAnime(anime);
    }

    protected Anime selectAnime(int codeAnime) {
        try {
            SearchAnime searchAnime = new SearchAnime();
            return searchAnime.searchCodeAnime(String.valueOf(codeAnime));
        } catch (Exception ex) {
            return null;
        }
    }

    protected void updateAnime(Anime anime) throws DataAlreadyRegisteredException {
        DeleteAnime deleteAnime = new DeleteAnime();
        deleteAnime.delete(String.valueOf(anime.getCodeAnime()));
        createAnime(anime);
    }

    protected void deleteAnime(Anime anime) {
        DeleteAnime deleteAnime = new DeleteAnime();
        deleteAnime.delete(String.valueOf(anime.getCodeAnime()));
    }

}//end of the class AnimeDAO 
