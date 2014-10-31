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
import java.awt.Container;

/**
 * Adapter to handle with the lack of precision in swing coordenates, and
 * convert percentages in pixels
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class ComponentHack {

    private double _x;
    private double _y;
    private double _width;
    private double _height;
    //component parsed
    private final Component _component;

    /**
     * Constructor with component
     *
     * @param component managed by adapter
     */
    public ComponentHack(Component component) {
        _component = component;
    }//end of the constructor

    /**
     * Set percentages, the sum of the position and size can't be bigger that
     * 100
     *
     * @param x % of x in relation to parent component
     * @param y % of y in relation to parent component
     * @param widht % of widht in relation to parent component
     * @param height % of height in relation to parent component
     */
    public void setPercentageBounds(double x, double y, double widht, double height) {
        if (x + widht > 100 || y + height > 100) {
            throw (new IllegalArgumentException("The position and size setted exceed the screen size!"));
        }
        _x = x;
        _y = y;
        _width = widht;
        _height = height;
    }//end of the method setPercentageBounds

    /**
     * Update components size and position in relation to the parent component
     *
     * @param proportion <code>widht/height</code> relation
     */
    public void update(double proportion) {
        Container container = _component.getParent();
        double sizeX = container.getWidth();
        double sizeY = container.getHeight();
        if (container.getWidth() < container.getHeight() * proportion) {
            sizeY = sizeX / proportion;
        } else {
            sizeX = sizeY * proportion;

        }
        _component.setBounds((int) (sizeX * _x / 100), (int) (sizeY * _y / 100), (int) (sizeX * _width / 100), (int) (sizeY * _height / 100));
    }//end of the method update

    /**
     * Get the component managed by the adpter
     *
     * @return <code>Component</code> managed
     */
    public Component getComponent() {
        return _component;
    }//end of the method getComponent

}//end of the class ComponentHack 
