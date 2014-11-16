package cova2.controller;

import cova2.view.MainFrame;

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

}//end of class MainController 
