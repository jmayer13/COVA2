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
        mainFrame = new MainFrame();

    }//end of constructor

    /**
     * Check if view is openned
     *
     * @return <code>Boolean</code> is view open?
     */
    public boolean isViewOpen() {
        return mainFrame.isOpen();
    }//end of method isViewOpen

    public List<Index> loadIndexes() throws SQLException, ClassNotFoundException {
        IndexDAO indexDAO = new IndexDAO();
        return indexDAO.getIndexes();
    }

    public List<Anime> loadAnimes(List<Index> indexes) {
        AnimeDAO animeDAO = new AnimeDAO();
        List<Anime> animes = new ArrayList();
        indexes.forEach(index -> {
            Anime anime = animeDAO.readAnime(index.getCodeAnime());
            animes.add(anime);
        });
        return animes;
    }

    public void setTableModel(IndexTableModel tableModel) {
        mainFrame.setTableModel(tableModel);
    }

    public Index getIndexRow(int row) {
        return mainFrame.getIndex(row);
    }

}//end of class MainController 
