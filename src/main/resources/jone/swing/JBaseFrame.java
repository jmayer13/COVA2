/*- 
 *            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *                    Version 2, December 2004
 *
 * Copyright (C) 2004 Sam Hocevar <sam@hocevar.net>
 *
 * Everyone is permitted to copy and distribute verbatim or modified
 * copies of this license document, and changing it is allowed as long
 * as the name is changed.
 *
 *            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 *  0. You just DO WHAT THE FUCK YOU WANT TO.
 */
package jone.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Frame with basic features of JOne
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class JBaseFrame extends JFrame {

    protected int _xFrame = 0;
    protected int _yFrame = 0;
    protected int _widthFrame = 0;
    protected int _heightFrame = 0;
    protected int _widthInternalPanel = -1;
    protected int _heightInternalPanel = -1;
    protected int _widthMaximum = -1;
    protected int _heightMaximum = -1;
    protected int _widthMinimum = -1;
    protected int _heightMinimum = -1;
    //internals componets
    protected List<Component> components;
    protected JPanel internalPanel;
    protected JScrollPane internalScrollPane;

    /**
     * Modes to define screen size
     */
    public enum ScreenSizeMode {

        /**
         * The developer is in charge to define the size
         */
        OWN_SIZE_MODE,
        /**
         * The frame is in fullscream mode
         */
        FULLSCREEM_MODE,
        /**
         * The frame covers all the screen space avaliable
         */
        ALL_SCREEM_MODE
    }//end of the enum ScreenSizeMode

    /**
     * Modes to rezide the frame and the componets
     */
    public enum ResizeMode {

        /**
         * Not do any kind of resize
         */
        NO_RESIZE_MODE,
        /**
         *
         */
        FIXED_RESIZE_MODE, /**
         *
         */
        ADAPTATIVE_RESIZE_MODE
    }//end of the enum ResizeMode

    /**
     * Constructor with ScreenSizeMode
     *
     * @param screenSizeMode
     */
    public JBaseFrame(ScreenSizeMode screenSizeMode) {
        super();
        initiateInternal();
        setScreenSizemode(screenSizeMode);
    }//end of the constructor

    /**
     * Initiate frame setting
     */
    private void initiateInternal() {
        components = new ArrayList();
        setLayout(null);
        setVisible(true);
        internalScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        internalScrollPane.setBounds(0, 0, _widthFrame, _heightFrame);
        this.getRootPane().setContentPane(internalScrollPane);

        internalPanel = new JPanel();
        internalPanel.setLayout(null);
        internalPanel.setBackground(Color.WHITE);
        internalScrollPane.setViewportView(internalPanel);
    }//end of the method initiateInternal

    public void setScreenSizemode(ScreenSizeMode screenSizeMode) {
        if (screenSizeMode == JProportionFrame.ScreenSizeMode.ALL_SCREEM_MODE) {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            _widthFrame = (int) dim.getWidth();
            _heightFrame = (int) dim.getHeight();
            this.setBounds(0, 0, _widthFrame, _heightFrame);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else if (screenSizeMode == JProportionFrame.ScreenSizeMode.FULLSCREEM_MODE) {
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            _heightFrame = (int) dim.getHeight();
            this.setBounds(_xFrame, _yFrame, _widthFrame, _heightFrame);
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            this.setUndecorated(true);
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
        }
    }//end of the method setScreenSizemode

    /**
     * Set bounds of the panel inside of the frame
     *
     * @param widthInternal
     * @param heightInternal
     */
    public void setInternalBounds(int widthInternal, int heightInternal) {
        _widthInternalPanel = widthInternal;
        _heightInternalPanel = heightInternal;
        internalPanel.setBounds(0, 0, widthInternal, heightInternal);
        internalPanel.setPreferredSize(new Dimension(widthInternal, heightInternal));
    }//end of the method setInternalBounds
}//end of the class JBaseFrame 
