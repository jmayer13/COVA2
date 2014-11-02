package cova2.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import jone.swing.ComponentHack;
import jone.swing.JProportionFrame;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class MainFrame {

    /**
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

        ComponentHack menuBarCH = new ComponentHack(menuBar);
        menuBarCH.setPercentageBounds(0, 0, 100, 5);

        mainFrame.add(menuBarCH);

        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    public static void main(String args[]) {
        new MainFrame();
    }

}//fim da classe MainFrame 
