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
import cova2.util.InternationalizationCentral;
import cova2.util.LogFileStream;
import cova2.util.LogManager;
import cova2.util.Observer;
import cova2.util.Subject;
import cova2.view.MainView;
import cova2.view.tableModel.IndexTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Main controller initiate MainFrameView
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class MainController implements Observer {

    private MainView mainFrame;
    private IndexTableModel tableModel;
    private LogManager logManager;

    /**
     * Constructor initiate view
     */
    public MainController() {
        logManager = new LogManager(MainController.class.getName());
        logManager.info("Starting view ...");
        startView();
        mainFrame.addAddAnimeActionListener(al -> {
            openAddAnimeController();

        });
        mainFrame.editAddAnimeActionListener(al -> {
            int index = getIndexRowSelected();
            if (index >= 0) {
                editAnime(getIndexRow(index), getAnimeRow(index));
            } else {
                InternationalizationCentral inter = new InternationalizationCentral();
                JOptionPane.showMessageDialog(null, inter.getWord("select_row_message"), inter.getWord("attention"), JOptionPane.INFORMATION_MESSAGE);
            }
        });

        mainFrame.plusAddAnimeActionListener(al -> {
            int index = getIndexRowSelected();
            if (index >= 0) {
                increaseEpisode(getAnimeRow(index));
            } else {
                InternationalizationCentral inter = new InternationalizationCentral();
                JOptionPane.showMessageDialog(null, inter.getWord("select_row_message"), inter.getWord("attention"), JOptionPane.INFORMATION_MESSAGE);
            }
        });
        mainFrame.minusAddAnimeActionListener(al -> {
            int index = getIndexRowSelected();
            if (index >= 0) {
                decreaseEpisode(getAnimeRow(index));
            } else {
                InternationalizationCentral inter = new InternationalizationCentral();
                JOptionPane.showMessageDialog(null, inter.getWord("select_row_message"), inter.getWord("attention"), JOptionPane.INFORMATION_MESSAGE);
            }
        });
        mainFrame.deleteAddAnimeActionListener(al -> {
            int index = getIndexRowSelected();
            if (index >= 0) {
                InternationalizationCentral inter = new InternationalizationCentral();
                int aswer = JOptionPane.showConfirmDialog(null, inter.getWord("delete_question") + getAnimeRow(index) + "?", inter.getWord("attention"), JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (aswer == 0) {
                    deleteAnime(getIndexRow(index));
                }
            } else {
                InternationalizationCentral inter = new InternationalizationCentral();
                JOptionPane.showMessageDialog(null, inter.getWord("select_row_message"), inter.getWord("attention"), JOptionPane.INFORMATION_MESSAGE);
            }
        });
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    closeView();
                } catch (SQLException | ClassNotFoundException ex) {
                    logManager.error("Error closing COVA ", ex);
                    ex.printStackTrace();
                }
            }
        });
    }//end of constructor

    /**
     * Stars view
     */
    protected void startView() {
        mainFrame = new MainView();
        logManager.info("Shearching data...");
        List<Index> indexes = null;
        try {
            logManager.info("Shearching indexes...");
            indexes = loadIndexes();
        } catch (SQLException | ClassNotFoundException ex) {
            logManager.error("Error searching indexes:", ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        logManager.info("Shearching animes...");
        List<Anime> animes = loadAnimes(indexes);
        logManager.trace("Table model created");
        tableModel = new IndexTableModel(indexes, animes);
        setTableModel(tableModel);
    }//end of the method startView

    /**
     * Listener to open AddAnimeController
     *
     * @return <code>AddAnimeController</code>
     */
    public AddAnimeController openAddAnimeController() {
        logManager.info("Caling controller to add anime...");
        AddAnimeController addAnimeController = null;
        try {
            addAnimeController = new AddAnimeController();
            addAnimeController.addObserver(this);
        } catch (SQLException | ClassNotFoundException ex) {
            logManager.error("Error instancing controller to add anime", ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return addAnimeController;
    }//end of the method openAddAnimeController

    /**
     * Update data in tableModel
     */
    public void updateData() {
        logManager.info("Updating data...");
        List<Index> indexes = null;
        try {
            indexes = loadIndexes();
        } catch (SQLException | ClassNotFoundException ex) {
            logManager.error("Error updating data", ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

        }
        List<Anime> animes = loadAnimes(indexes);
        tableModel.update(indexes, animes);
    }//end of the method updateData

    /**
     * Check if view is openned
     *
     * @return <code>Boolean</code> is view open?
     */
    public boolean isViewOpen() {
        logManager.trace("View is open: " + mainFrame.isOpen());
        return mainFrame.isOpen();
    }//end of method isViewOpen

    /**
     * Load indexes from database with DAO
     *
     * @return <code>List</code> indexes
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Index> loadIndexes() throws SQLException, ClassNotFoundException {
        IndexDAO indexDAO = new IndexDAO();
        return indexDAO.getIndexes();
    }//end of the method loadIndexes

    //mock it
    /**
     * Load animes from JSON files thought Indexes
     *
     * @param indexes
     * @return <code>List</code> with animes
     */
    public List<Anime> loadAnimes(List<Index> indexes) {
        AnimeDAO animeDAO = new AnimeDAO();
        List<Anime> animes = new ArrayList();
        indexes.forEach(index -> {
            Anime anime = animeDAO.readAnime(index.getCodeAnime());
            animes.add(anime);
        });
        return animes;
    }//end of the method loadAnimes

    //use it
    /**
     * Set table model to table of view
     *
     * @param tableModel #IndexTableModel
     */
    public void setTableModel(IndexTableModel tableModel) {
        mainFrame.setTableModel(tableModel);
    }//end of the method setTableModel

    /**
     * Get index from table
     *
     * @param row
     * @return <code>Index</code> index
     */
    public Index getIndexRow(int row) {
        logManager.info("Getting index from row " + row);
        return mainFrame.getIndex(row);
    }//end of the method getIndexRow

    //mock it
    /**
     * Get index from table
     *
     * @param row
     * @return <code>Anime</code> anime
     */
    public Anime getAnimeRow(int row) {
        logManager.info("Getting anime from row " + row);
        return mainFrame.getAnime(row);
    }//end of the method getAnimeRow

    /**
     * Update data from table
     *
     * @param subject
     * @param object
     */
    @Override
    public void update(Subject subject, Object object) {
        logManager.info("Updating...");
        updateData();
    }//end of the method update

    //TEST IT
    public AddAnimeController editAnime(Index indexRow, Anime animeRow) {
        logManager.info("Caling controller to add anime...");
        AddAnimeController addAnimeController = null;
        try {
            addAnimeController = new AddAnimeController(indexRow, animeRow);
            addAnimeController.addObserver(this);
        } catch (SQLException | ClassNotFoundException ex) {
            logManager.error("Error editing anime!", ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return addAnimeController;
    }

    //mock it
    public void decreaseEpisode(Anime animeRow) {
        logManager.info("Decresing anime current episode...");
        AnimeDAO animeDAO = new AnimeDAO();
        try {
            animeDAO.decreaseEpisode(animeRow);
        } catch (DataAlreadyRegisteredException | UnavailableDataException ex) {
            logManager.error("Error editing anime!", ex);
            ex.printStackTrace();
        }
        updateData();
    }

    //mock it
    public void increaseEpisode(Anime animeRow) {
        logManager.info("Incresing anime current episode...");

        AnimeDAO animeDAO = new AnimeDAO();
        try {
            animeDAO.increaseEpisode(animeRow);
        } catch (DataAlreadyRegisteredException ex) {
            logManager.error("Error editing anime!", ex);
            ex.printStackTrace();
        } catch (UnavailableDataException ex) {
            logManager.error("Error editing anime!", ex);
            ex.printStackTrace();
        }
        updateData();
    }

    //TEST IT
    public void selectRow(int index) {
        mainFrame.selectRow(index);
    }

    public int getIndexRowSelected() {
        return mainFrame.getRowSelected();
    }

    public void deleteAnime(Index indexRegistered) {
        logManager.info("Deleting anime...");
        IndexDAO indexDAO;
        try {
            indexDAO = new IndexDAO();

            indexDAO.eraseIndex(indexRegistered);
            AnimeDAO animeDAO = new AnimeDAO();
            Anime anime = animeDAO.readAnime(indexRegistered.getCodeAnime());
            animeDAO.eraseAnime(anime);
            updateData();
        } catch (SQLException | ClassNotFoundException | UnavailableDataException ex) {
            logManager.error("Error deleting data", ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void closeView() throws SQLException, ClassNotFoundException {
        mainFrame.close();
        closeConnection();

    }

    protected void closeConnection() throws SQLException, ClassNotFoundException {
        (new IndexDAO()).closeConnection();
        LogFileStream logFileStream = LogFileStream.getLogFileStream();
        logFileStream.close();
    }

}//end of class MainController 
