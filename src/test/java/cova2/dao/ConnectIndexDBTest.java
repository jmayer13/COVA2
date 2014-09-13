package cova2.dao;

import java.sql.Connection;
import static org.junit.Assert.assertNotNull;

/**
 * Test to ConnectIndexDB, that creates a connection with the Index DB
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class ConnectIndexDBTest {

    private ConnectIndexDB connectIndexDB;

    public ConnectIndexDBTest() {
    }

    @Test
    /**
     * Test the establishment of the connection
     */
    public void testConnect() {
        connectIndexDB = new ConnectIndexDB();
        Connection connection = connectIndexDB.connect();
        assertNotNull("Was not possible establish a connection with Index DB!", connectIndexDB);
    }//end of the method testConnect
}//end of the class ConnectIndexDBTest
