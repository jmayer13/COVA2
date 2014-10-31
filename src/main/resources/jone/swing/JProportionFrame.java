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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Frame that manages componets based on proportion, having a all-screen size
 *
 * @see jone.swing.JBaseFrame
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class JProportionFrame extends JBaseFrame {

    //proportion of screen width/height
    private double _proportion;
    //list of components added to frame in ComponetHack adapeters
    private List<ComponentHack> componentHacks;

    /**
     * Constructor with proportion
     *
     * @param proportion proportion of valid screen
     */
    public JProportionFrame(double proportion) {
        super(JBaseFrame.ScreenSizeMode.ALL_SCREEM_MODE);
        _proportion = proportion;
        componentHacks = new ArrayList();
        initiateInternal();
    }//end of the constructor

    /**
     * Initialize internal settings
     */
    private void initiateInternal() {

        //define adaptation listener
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adapt(e);
            }
        });
    }//end of the method initiateInternal

    /**
     * Adapt the frame to the actual size
     *
     * @param e ComponentEvent
     */
    private void adapt(ComponentEvent e) {
        Dimension dimension = e.getComponent().getSize();
        _widthFrame = dimension.width;
        _heightFrame = dimension.height;
        _widthInternalPanel = _widthFrame - 20;
        _heightInternalPanel = _heightFrame - 30;
        setInternalBounds(_widthInternalPanel, _heightInternalPanel);
        internalScrollPane.setBounds(0, 0, _widthFrame, _heightFrame);
        // internalPanel.setBounds(0, 0, _widthFrame, _heightFrame);
        for (int i = 0; i < componentHacks.size(); i++) {
            componentHacks.get(i).update(_proportion);
        }
    }//end of the method adapt

    /**
     * Warning if try add component with this method
     * <b>Use add(ComponentHack ch)</b>
     *
     * @param component
     * @return <code>Component</code> component
     * @deprecated
     */
    @Override
    @Deprecated
    public Component add(Component component) {
        throw new UnsupportedOperationException(" Use the instances of the class ComponentHack to add Components!");
    }//end of method add

    /**
     * Add a ComponentHack
     *
     * @param componentHack
     * @return <code>ComponentHack</code> component adapter
     */
    public ComponentHack add(ComponentHack componentHack) {
        componentHacks.add(componentHack);
        internalPanel.add(componentHack.getComponent());
        return componentHack;
    }//end of the method ComponentHack
}//end of the class JProportionFrame 
