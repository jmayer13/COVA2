package cova2.util;

import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Stream to log file
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class LogFileStreamTest {

    private LogFileStream logFileStream;

    /**
     * Initialize class
     */
    @Before
    public void initialize() {
        logFileStream = LogFileStream.getLogFileStream();
    }//end of the method initialize

    /**
     * Test if instances created are the same
     */
    @Test
    public void testUniqueInstance() {
        assertEquals("The instances are not the same object!", logFileStream, LogFileStream.getLogFileStream());
    }//end of the method testUniqueInstance

    /**
     * Test constructor for single instance
     *
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    @Test
    public void testContructor() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor constructors[] = LogFileStream.class.getDeclaredConstructors();
        constructors[0].setAccessible(true);
        LogFileStream newLogFileStream = (LogFileStream) constructors[0].newInstance();
        assertTrue("Test single intace returned null!", newLogFileStream != null);
    }//end of the method testContructor

    @Test
    public void testCreatePrintWriter() {
        assertNotNull("Could not create PrintWriter!", logFileStream.getPrintWriter());
    }

    @Test
    public void testGetPrintWriter() {
        assertNotNull("PrintWriter was not created correctly!", logFileStream.getPrintWriter());

    }

    @Test
    public void testClose() {
        PrintWriter printWriter = logFileStream.getPrintWriter();
        logFileStream.close();
        assertTrue(printWriter.checkError());
    }

}//end of the class LogFileStreamTest 
