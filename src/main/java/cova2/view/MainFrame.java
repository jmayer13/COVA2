package cova2.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import jone.swing.ComponentHack;
import jone.swing.JProportionFrame;

/**
 * Descrição
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
    private JProportionFrame mainFrame;

    public MainFrame() {
        startView();
    }

    private void startView() {
        mainFrame = new JProportionFrame(1.25);
        JMenuBar menuBar = new JMenuBar();
        JTabbedPane tablesPane = new JTabbedPane();

        JButton plusButton = new JButton();
        JButton minusButton = new JButton();
        JButton vizualizeButton = new JButton();
        JButton addButton = new JButton();
        JButton editButton = new JButton();
        JButton deleteButton = new JButton();

        ComponentHack menuBarComponentHack = new ComponentHack(menuBar);
        ComponentHack tablesPaneComponentHack = new ComponentHack(tablesPane);
        ComponentHack plusButtonComponentHack = new ComponentHack(plusButton);
        ComponentHack minusButtonComponentHack = new ComponentHack(minusButton);
        ComponentHack vizualizeButtonComponentHack = new ComponentHack(vizualizeButton);
        ComponentHack addButtonComponentHack = new ComponentHack(addButton);
        ComponentHack editButtonComponentHack = new ComponentHack(editButton);
        ComponentHack deleteButtonComponentHack = new ComponentHack(deleteButton);

        menuBarComponentHack.setPercentageBounds(0, 0, 100, 5);
        tablesPaneComponentHack.setPercentageBounds(5, 10, 60, 80);
        plusButtonComponentHack.setPercentageBounds(70, 20, 5, 5);
        minusButtonComponentHack.setPercentageBounds(80, 20, 5, 5);
        vizualizeButtonComponentHack.setPercentageBounds(70, 30, 15, 5);
        addButtonComponentHack.setPercentageBounds(70, 40, 15, 5);
        editButtonComponentHack.setPercentageBounds(70, 50, 15, 5);
        deleteButtonComponentHack.setPercentageBounds(70, 60, 15, 5);

        mainFrame.add(menuBarComponentHack);
        mainFrame.add(tablesPaneComponentHack);
        mainFrame.add(menuBarComponentHack);
        mainFrame.add(tablesPaneComponentHack);
        mainFrame.add(plusButtonComponentHack);
        mainFrame.add(minusButtonComponentHack);
        mainFrame.add(vizualizeButtonComponentHack);
        mainFrame.add(addButtonComponentHack);
        mainFrame.add(editButtonComponentHack);
        mainFrame.add(deleteButtonComponentHack);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        new MainFrame();
    }

}//fim da classe MainFrame 
