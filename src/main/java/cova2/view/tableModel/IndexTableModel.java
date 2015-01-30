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
package cova2.view.tableModel;

import cova2.model.anime.Anime;
import cova2.model.index.Index;
import cova2.util.LogManager;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * TableModel to MainFrame
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class IndexTableModel extends AbstractTableModel {

    private final int COLUMN_NUMBER = 2;
    private final int COLUMN_TITLE_INDEX = 0;
    private final int COLUMN_CURRENT_EPISODE_INDEX = 1;
    private List<Index> _indexes;
    private List<Anime> _animes;
    private LogManager logManager;

    /**
     * Set data of tableModel
     *
     * @param indexes
     * @param animes
     */
    public IndexTableModel(List<Index> indexes, List<Anime> animes) {
        logManager = new LogManager(IndexTableModel.class.getName());
        _indexes = indexes;
        _animes = animes;
    }//end of the constructor

    /**
     * Get numbers of lines
     *
     * @return <code>Integer</code> count
     */
    @Override
    public int getRowCount() {
        logManager.debug("The number of rows is " + COLUMN_NUMBER);
        return _indexes.size();
    }//end of the method getRowCount

    /**
     * Get numbers of columns
     *
     * @return <code>Integer</code> count
     */
    @Override
    public int getColumnCount() {
        logManager.info("The number of columns is " + COLUMN_NUMBER);
        return COLUMN_NUMBER;
    }//end of the method getColumnCount

    /**
     * Get table cell value
     *
     * @param rowIndex
     * @param columnIndex
     * @return <code>Object</code> value of cell
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        logManager.trace("Get value from cell " + rowIndex + " " + columnIndex);
        if (columnIndex == this.COLUMN_TITLE_INDEX) {
            return _indexes.get(rowIndex).getMainTitleAnime();
        } else {
            return _animes.get(rowIndex).getCurrentEpisode();
        }
    }//end of the method getValueAt

    /**
     * Get Index of anime with index of table
     *
     * @param index
     * @return <code>Index</code>
     */
    public Index getIndex(int index) {
        logManager.debug("Get index from line " + index);
        return _indexes.get(index);
    }//end of the method getIndex

    /**
     * Get anime of respective line
     *
     * @param index line of the table
     * @return <code>Anime</code> anime
     */
    public Anime getAnime(int index) {
        logManager.debug("Get anime from line " + index);
        return _animes.get(index);
    }//end of the method getAnime

    /**
     * Update data
     *
     * @param indexes
     * @param animes
     */
    public void update(List<Index> indexes, List<Anime> animes) {
        logManager.debug("Updating list indexes with " + indexes.size() + " elements, list amimes with " + animes.size());
        if (indexes == null || animes == null) {
            logManager.error("The update of the tablemodel can't be null!");
            throw new NullPointerException("The update of the tablemodel can't be null!");

        }
        _indexes = indexes;
        _animes = animes;
        fireTableDataChanged();
    }//end of the method update

}//end of the class IndexTableModel 
