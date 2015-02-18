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

import cova2.dao.AnimeDAO;
import cova2.dao.IndexDAO;
import cova2.exception.DataAlreadyRegisteredException;
import cova2.exception.UnavailableDataException;
import cova2.model.anime.Anime;
import cova2.model.index.Index;
import cova2.util.LogManager;
import cova2.util.Observer;
import cova2.util.Subject;
import cova2.view.AddAnimeView;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Add anime controller
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class AddAnimeController implements Subject {

    private AddAnimeView addAnimeView;
    private List<Observer> observers;
    private LogManager logManager;
    private Index _oldIndex = null;
    private Anime _oldAnime = null;

    /**
     * Constructor to add anime Start view and define events
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public AddAnimeController() throws SQLException, ClassNotFoundException {
        initialize();
    }//end of the constructor 

    /**
     * Constructor to edit anime Start view and define events
     *
     * @param index
     * @param anime
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public AddAnimeController(Index index, Anime anime) throws SQLException, ClassNotFoundException {
        initialize();
        setIndexAnime(index, anime);
    }//end of the constructor 

    /**
     * Initialize views and variables
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    protected void initialize() throws SQLException, ClassNotFoundException {
        logManager = new LogManager(AddAnimeController.class.getName());
        observers = new ArrayList();
        logManager.info("Starting view...");
        addAnimeView = new AddAnimeView();
        addAnimeView.addOKActionListener(ae -> {
            okAction();
        });
        addAnimeView.addCancelActionListener(ae -> {
            closeView();
        }
        );
        addAnimeView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeView();
            }
        });
    }//end of the method initialize

    /**
     * Action to OK button
     */
    public void okAction() {
        logManager.debug("Saving anime...");
        if (isValid()) {
            if (_oldIndex == null) {
                try {
                    registerAnime(getIndex(), getAnime(getIndex()));

                } catch (SQLException | ClassNotFoundException | DataAlreadyRegisteredException ex) {
                    logManager.error("Error registering anime and index", ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                try {
                    editAnime(getIndex(), getAnime(getIndex()));
                } catch (SQLException | ClassNotFoundException | UnavailableDataException | DataAlreadyRegisteredException ex) {
                    logManager.error("Error editing anime and index", ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            notifyObservers();
            closeView();
        }
    }//end of the method okAction

    /**
     * Check if form has valid data
     *
     * @return <code>Boolean</code> true if valid
     */
    protected boolean isValid() {
        return addAnimeView.isValid();
    }//end of the method isValid

    /**
     * Register anime with anime data and index
     *
     * @param index
     * @param anime
     * @return <code>Index</code> index registered
     * @throws SQLException
     * @throws java.lang.ClassNotFoundException
     * @throws cova2.exception.DataAlreadyRegisteredException
     */
    public Index registerAnime(Index index, Anime anime) throws SQLException, ClassNotFoundException, DataAlreadyRegisteredException {
        IndexDAO indexDAO = new IndexDAO();
        AnimeDAO animeDAO = new AnimeDAO();
        Index newIndex = indexDAO.addIndex(index);
        animeDAO.createAnime(anime);
        return newIndex;
    }//end of the method registerAnime

    /**
     * Get index from table
     *
     * @return <code>Index</code>
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public Index getIndex() throws SQLException, ClassNotFoundException {
        Index index = null;
        if (isValid()) {
            if (_oldIndex == null) {
                index = new Index(addAnimeView.getTitleAnime(),
                        getRecommendedCodeAnime());
            } else {
                _oldIndex.setMainTitleAnime(addAnimeView.getTitleAnime());
                index = _oldIndex;
            }
        }
        return index;
    }//end of the method getIndex

    /**
     * Get recommended code for anime (biggest code +1)
     *
     * @return <code>Integer</code> code recommended
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int getRecommendedCodeAnime() throws SQLException, ClassNotFoundException {
        IndexDAO indexDAO = new IndexDAO();
        return indexDAO.getRecommendedCodeAnime();
    }//end of the method getRecommendedCodeAnime

    /**
     * Get anime from table
     *
     * @return <code>Anime</code>
     */
    public Anime getAnime(Index index) throws SQLException, ClassNotFoundException {
        Anime anime = null;
        if (isValid()) {
            if (_oldAnime == null) {
                anime = new Anime();
                anime.setCodeAnime(
                        getRecommendedCodeAnime());
                anime.setCurrentEpisode(addAnimeView.getCurrentEpisode());
            } else {
                _oldAnime.setCurrentEpisode(addAnimeView.getCurrentEpisode());
                anime = _oldAnime;
            }
        }
        return anime;

    }//end of the method getAnime

    /**
     * Close view
     */
    public void closeView() {
        logManager.info("Closing view...");
        addAnimeView.close();
        closeConnections();
    }//end of the method closeView

    /**
     * Close connection
     */
    protected void closeConnections() {
        try {
            IndexDAO indexDAO = new IndexDAO();
            indexDAO.closeConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            logManager.error("Error closing connection ", ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//end of the method closeConnections

    /**
     * Add obsertver
     *
     * @param observer
     */
    @Override
    public void addObserver(Observer observer) {
        logManager.info("Adding oberver ");
        observers.add(observer);
    }//end of the method addObserver

    /**
     * Delete observer from list
     *
     * @param observer
     */
    @Override
    public void deleteObserver(Observer observer) {
        logManager.info("Removing oberver ");
        observers.remove(observer);
    }//end of the method deleteObserver

    /**
     * Notify observer
     *
     * @param arg
     */
    @Override
    public void notifyObservers(Object arg) {
        logManager.info("Notifying obervers ");
        observers.stream().forEach((observer) -> {
            observer.update(this, arg);
        });
    }//end of the method notifyObservers

    /**
     * Return view of controller
     *
     * @return <code>AddAnimeView</code> view
     */
    public AddAnimeView getView() {
        return addAnimeView;
    }//end of the method getView

    /**
     * Edit anime
     *
     * @param index
     * @param anime
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws DataAlreadyRegisteredException
     * @throws UnavailableDataException
     */
    protected void editAnime(Index index, Anime anime) throws SQLException, ClassNotFoundException, DataAlreadyRegisteredException, UnavailableDataException {
        IndexDAO indexDAO = new IndexDAO();
        AnimeDAO animeDAO = new AnimeDAO();
        indexDAO.editIndex(index);
        animeDAO.editAnime(anime);
    }//end of the method editAnime

    /**
     * Set data to be edited
     *
     * @param oldIndex
     * @param oldAnime
     */
    protected void setIndexAnime(Index oldIndex, Anime oldAnime) {
        _oldIndex = oldIndex;
        _oldAnime = oldAnime;
        addAnimeView.setTitleAnime(oldIndex.getMainTitleAnime());
        addAnimeView.setCurrentEpisode(oldAnime.getCurrentEpisode());
    }//end of the method setIndexAnime
}//end of the class AddAnimeController 
