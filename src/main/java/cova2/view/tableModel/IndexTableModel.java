package cova2.view.tableModel;

import cova2.model.anime.Anime;
import cova2.model.index.Index;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Descrição
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

    public IndexTableModel(List<Index> indexes, List<Anime> animes) {
        _indexes = indexes;
        _animes = animes;
    }

    @Override
    public int getRowCount() {
        return _indexes.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NUMBER;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == this.COLUMN_TITLE_INDEX) {
            return _indexes.get(rowIndex).getCodeIndex();
        } else {
            return _animes.get(rowIndex).getCurrentEpisode();
        }
    }

    public Index getIndex(int index) {
        return _indexes.get(index);
    }

    public Anime getAnime(int index) {
        return _animes.get(index);
    }

}//fim da classe IndexTableModel 
