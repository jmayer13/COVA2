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
package cova2.view;

import cova2.model.anime.Anime;
import cova2.model.index.Index;
import cova2.util.InternationalizationCentral;
import cova2.util.LogManager;
import cova2.view.tableModel.IndexTableModel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import jone.swing.ComponentHack;
import jone.swing.JProportionFrame;
import jone.util.GoldenRatioCalculator;

/**
 * Main view in a frame
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class MainView {
    /*
     * W3scholl (31/10/2014) "As of today, 99% of your visitors have a screen
     * resolution of 1024x768 pixels or higher." 1024/768 = 1.33333333333
     * 1280x800 = 1.6 1280/1024 = 1.25 1366/768 = 1.77864583333 1920/1080 =
     * 1.77777777778 1440/900 = 1.6 1600/900 = 1.77777777778 78% 1.25 - 1.7789
     */

    //proportion width/height
    private final double PROPORTION = 1.25;
    private JProportionFrame mainFrame;
    private JTable table;
    private IndexTableModel _tableModel;
    private JButton plusButton;
    private JButton minusButton;
    private JButton visualizeButton;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private LogManager logManager;

    /**
     * Initiate frame and components
     */
    public MainView() {
        logManager = new LogManager(MainView.class.getName());
        logManager.trace("Starting view ...");
        startView();
    }//end of the constructor

    /**
     * Start view
     */
    private void startView() {
        logManager.trace("Initializing components");
        InternationalizationCentral iCentral = new InternationalizationCentral();
        mainFrame = new JProportionFrame(PROPORTION);
        JMenuBar menuBar = new JMenuBar();
        JTabbedPane tablesPane = new JTabbedPane();
        table = new JTable();

        plusButton = new JButton("+");
        minusButton = new JButton("-");
        visualizeButton = new JButton(iCentral.getWord("visualize"));
        addButton = new JButton(iCentral.getWord("add"));
        editButton = new JButton(iCentral.getWord("edit"));
        deleteButton = new JButton(iCentral.getWord("delete"));

        logManager.trace("Encapsuling components in ComponentHacks");
        ComponentHack menuBarComponentHack = new ComponentHack(menuBar);
        ComponentHack tablesPaneComponentHack = new ComponentHack(tablesPane);
        ComponentHack plusButtonComponentHack = new ComponentHack(plusButton);
        ComponentHack minusButtonComponentHack = new ComponentHack(minusButton);
        ComponentHack visualizeButtonComponentHack = new ComponentHack(visualizeButton);
        ComponentHack addButtonComponentHack = new ComponentHack(addButton);
        ComponentHack editButtonComponentHack = new ComponentHack(editButton);
        ComponentHack deleteButtonComponentHack = new ComponentHack(deleteButton);

        logManager.trace("Setting bounds");
        int buttonWidth = (int) (GoldenRatioCalculator.getBiggerSize(7, 1 / PROPORTION) * 2);
        menuBarComponentHack.setPercentageBounds(0, 0, 100, 5);
        tablesPaneComponentHack.setPercentageBounds(5, 10, GoldenRatioCalculator.getSmallerSize(80, 1 / PROPORTION), 80);
        plusButtonComponentHack.setPercentageBounds(70, 20, GoldenRatioCalculator.getSmallerSize(buttonWidth) / 2, 5);
        minusButtonComponentHack.setPercentageBounds(70 + (buttonWidth - GoldenRatioCalculator.getSmallerSize(buttonWidth) / 2), 20, GoldenRatioCalculator.getSmallerSize(buttonWidth) / 2, 5);
        visualizeButtonComponentHack.setPercentageBounds(70, 30, buttonWidth, 5);
        addButtonComponentHack.setPercentageBounds(70, 40, buttonWidth, 5);
        editButtonComponentHack.setPercentageBounds(70, 50, buttonWidth, 5);
        deleteButtonComponentHack.setPercentageBounds(70, 60, buttonWidth, 5);

        logManager.trace("Joining components");
        mainFrame.add(menuBarComponentHack);
        mainFrame.add(tablesPaneComponentHack);
        mainFrame.add(menuBarComponentHack);
        mainFrame.add(tablesPaneComponentHack);
        mainFrame.add(plusButtonComponentHack);
        mainFrame.add(minusButtonComponentHack);
        mainFrame.add(visualizeButtonComponentHack);
        mainFrame.add(addButtonComponentHack);
        mainFrame.add(editButtonComponentHack);
        mainFrame.add(deleteButtonComponentHack);

        tablesPane.add(table);
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        logManager.trace("View Started");

    }//end of the method startView

    /**
     * Check if frame is open
     *
     * @return <code>boolean</code> if problem open?
     */
    public boolean isOpen() {
        return mainFrame.isVisible();
    }//end of the method isOpen

    /**
     * Close view
     */
    public void close() {
        logManager.info(MainView.class.getName());
        mainFrame.dispose();
    }//end of the method close

    /**
     * Set tablemodel with data for the table
     *
     * @param tableModel IndexTableModel
     */
    public void setTableModel(IndexTableModel tableModel) {
        _tableModel = tableModel;
        logManager.info("Updating tableModel");
        table.setModel(tableModel);
    }//end of the method setTableModel

    /**
     * Get index from table
     *
     * @param row
     * @return <code>Index</code>
     */
    public Index getIndex(int row) {
        logManager.info("Getting index of row " + row);
        return _tableModel.getIndex(row);
    }//end of the method getIndex

    /**
     * Select row of table
     *
     * @param index
     */
    public void selectRow(int index) {
        table.setRowSelectionInterval(index, index);
    }//end of the method selectRow

    /**
     * Get index of row selected
     *
     * @return <code>int </code> index, if nothing selected returns -1
     */
    public int getRowSelected() {
        return table.getSelectedRow();
    }//end of the method getRowSelected

    /**
     * Get anime from table
     *
     * @param row
     * @return <code>Anime</code>
     */
    public Anime getAnime(int row) {
        logManager.info("Getting anime of row " + row);
        return _tableModel.getAnime(row);
    }//end of the method getAnime

    /**
     * Add action listener to addButton
     *
     * @param actionListener
     */
    public void addAddAnimeActionListener(ActionListener actionListener) {
        logManager.trace("Adding ActionListener to button add");
        addButton.addActionListener(actionListener);
    }//end of the method addAddAnimeActionListener

    /**
     * Edit action listener to Button
     *
     * @param actionListener
     */
    public void editAddAnimeActionListener(ActionListener actionListener) {
        logManager.trace("Adding ActionListener to button edit");
        editButton.addActionListener(actionListener);
    }//end of the method editAddAnimeActionListener

    /**
     * Delete action listener to deleteButton
     *
     * @param actionListener
     */
    public void deleteAddAnimeActionListener(ActionListener actionListener) {
        logManager.trace("Adding ActionListener to button delete");
        deleteButton.addActionListener(actionListener);
    }//end of the method deleteAddAnimeActionListener

    /**
     * Plus action listener to plusButton
     *
     * @param actionListener
     */
    public void plusAddAnimeActionListener(ActionListener actionListener) {
        logManager.trace("Adding ActionListener to button +");
        plusButton.addActionListener(actionListener);
    }//end of the method plusAddAnimeActionListener

    /**
     * Minus action listener to minusButton
     *
     * @param actionListener
     */
    public void minusAddAnimeActionListener(ActionListener actionListener) {
        logManager.trace("Adding ActionListener to button -");
        minusButton.addActionListener(actionListener);
    }//end of the method minusAddAnimeActionListener

    /**
     * Add listener to windows
     *
     * @param windowAdapter
     */
    public void addWindowListener(WindowAdapter windowAdapter) {
        logManager.trace("Adding WindowListener to view");
        mainFrame.addWindowListener(windowAdapter);
    }//end of the method addWindowListener

    //GUI test
    public static void main(String args[]) {
        new MainView();
    }//end of the method main
}//end of class MainView 
