package cova2.view;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test view MainFrame
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class MainFrameTest {

    private MainFrame mainFrame;

    /**
     * Initiate class tested
     */
    @Before
    public void initiate() {
        mainFrame = new MainFrame();
    }//end of the test method initiate

    /**
     * Test if the check for frame open works
     */
    @Test
    public void testFrameOpen() {
        assertTrue("The frame should be opened!", mainFrame.isOpen());
    }//end of the test method testFrameOpen

    /**
     * Test if when close interface the check is false
     */
    @Test
    public void testFrameClose() {
        mainFrame.close();
        assertFalse("The frame should be closed!", mainFrame.isOpen());
    }//end of the test method testFrameClose

}//end of the test class MainFrameTest
