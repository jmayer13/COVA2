package cova2.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

/**
 * Test to ConnectionFactory
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class ConnectionFactoryTest {

    /**
     * Test the creation of a single insnce of ConnectIndexDB
     */
    @Test
    public void createSingleIntance() {
        ConnectIndexDB connectIndexDB1 = ConnectionFactory.getConnection();
        assertNotNull("Could not get 1º single instance!", connectIndexDB1);
        ConnectIndexDB connectIndexDB2 = ConnectionFactory.getConnection();
        assertNotNull("Could not get 2º single instance!", connectIndexDB2);
        assertSame("The instance is not unique!", connectIndexDB1, connectIndexDB2);
    }//end of the method createSingleIntance

}//end of class ConnectionFactoryTest
