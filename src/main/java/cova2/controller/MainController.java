package cova2.controller;

import cova2.dao.AnimeDAO;
import cova2.dao.IndexDAO;
import cova2.model.anime.Anime;
import cova2.model.index.Index;
import cova2.view.MainFrame;
import cova2.view.tableModel.IndexTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main controller initiate MainFrameView
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class MainController {

    private MainFrame mainFrame;

    /**
     * Constructor initiate view
     */
    public MainController() {
        startView();
    }//end of constructor

    /**
     * Stars view
     */
    protected void startView() {
        mainFrame = new MainFrame();
        List<Index> indexes = null;
        try {
            indexes = loadIndexes();
        } catch (SQLException | ClassNotFoundException ex) {
            //TO DO log it
            ex.printStackTrace();
            System.exit(1);
        }
        List<Anime> animes = loadAnimes(indexes);
        IndexTableModel tableModel = new IndexTableModel(indexes, animes);
        setTableModel(tableModel);
    }//end of the method startView

    /**
     * Check if view is openned
     *
     * @return <code>Boolean</code> is view open?
     */
    public boolean isViewOpen() {
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
        return mainFrame.getIndex(row);
    }//end of the method getIndexRow

    /**
     * Get index from table
     *
     * @param row
     * @return <code>Anime</code> anime
     */
    public Anime getAnimeRow(int row) {
        return mainFrame.getAnime(row);
    }//end of the method getAnimeRow

}//end of class MainController 
