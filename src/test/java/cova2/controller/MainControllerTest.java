package cova2.controller;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Class test MainController
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class MainControllerTest {

    private MainController mainController;

    /**
     * Initiate class tested
     */
    @Before
    public void initiate() {
        mainController = new MainController();
    }//end of test method initiate

    /**
     * Test start of interface
     */
    @Test
    public void testStartInterface() {
        assertTrue("The interface was not initialized!", mainController.isViewOpen());
    }//end of the test method testStartInterface

}//end of class MainControllerTest 
