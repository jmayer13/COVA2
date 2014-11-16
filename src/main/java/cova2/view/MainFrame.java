package cova2.view;

import cova2.util.InternationalizationCentral;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import jone.swing.ComponentHack;
import jone.swing.JProportionFrame;
import jone.util.GoldenRatioCalculator;

/**
 * Main view in a frame
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class MainFrame {
    /*
     * W3scholl (31/10/2014) "As of today, 99% of your visitors have a screen
     * resolution of 1024x768 pixels or higher." 1024/768 = 1.33333333333
     * 1280x800 = 1.6 1280/1024 = 1.25 1366/768 = 1.77864583333 1920/1080 =
     * 1.77777777778 1440/900 = 1.6 1600/900 = 1.77777777778 78% 1.25 - 1.7789
     */

    //proportion width/height
    private final double PROPORTION = 1.25;
    private JProportionFrame mainFrame;

    /**
     * Initiate frame and components
     */
    public MainFrame() {
        startView();
        mainFrame.repaint();
    }//end of the constructor

    /**
     * Start view
     */
    private void startView() {
        InternationalizationCentral iCentral = new InternationalizationCentral();
        mainFrame = new JProportionFrame(PROPORTION);
        JMenuBar menuBar = new JMenuBar();
        JTabbedPane tablesPane = new JTabbedPane();

        JButton plusButton = new JButton("+");
        JButton minusButton = new JButton("-");
        JButton visualizeButton = new JButton(iCentral.getWord("visualize"));
        JButton addButton = new JButton(iCentral.getWord("add"));
        JButton editButton = new JButton(iCentral.getWord("edit"));
        JButton deleteButton = new JButton(iCentral.getWord("delete"));

        ComponentHack menuBarComponentHack = new ComponentHack(menuBar);
        ComponentHack tablesPaneComponentHack = new ComponentHack(tablesPane);
        ComponentHack plusButtonComponentHack = new ComponentHack(plusButton);
        ComponentHack minusButtonComponentHack = new ComponentHack(minusButton);
        ComponentHack visualizeButtonComponentHack = new ComponentHack(visualizeButton);
        ComponentHack addButtonComponentHack = new ComponentHack(addButton);
        ComponentHack editButtonComponentHack = new ComponentHack(editButton);
        ComponentHack deleteButtonComponentHack = new ComponentHack(deleteButton);

        int buttonWidth = (int) (GoldenRatioCalculator.getBiggerSize(7, 1 / PROPORTION) * 2);
        menuBarComponentHack.setPercentageBounds(0, 0, 100, 5);
        tablesPaneComponentHack.setPercentageBounds(5, 10, GoldenRatioCalculator.getSmallerSize(80, 1 / PROPORTION), 80);
        plusButtonComponentHack.setPercentageBounds(70, 20, GoldenRatioCalculator.getSmallerSize(buttonWidth) / 2, 5);
        minusButtonComponentHack.setPercentageBounds(70 + (buttonWidth - GoldenRatioCalculator.getSmallerSize(buttonWidth) / 2), 20, GoldenRatioCalculator.getSmallerSize(buttonWidth) / 2, 5);
        visualizeButtonComponentHack.setPercentageBounds(70, 30, buttonWidth, 5);
        addButtonComponentHack.setPercentageBounds(70, 40, buttonWidth, 5);
        editButtonComponentHack.setPercentageBounds(70, 50, buttonWidth, 5);
        deleteButtonComponentHack.setPercentageBounds(70, 60, buttonWidth, 5);

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

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        mainFrame.dispose();
    }//end of the method close

    //GUI test
    public static void main(String args[]) {
        new MainFrame();
    }//end of the method main

}//end of class MainFrame 
