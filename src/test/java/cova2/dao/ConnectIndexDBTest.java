package cova2.dao;

import java.sql.SQLException;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Test to ConnectIndexDB, that creates a connection with the Index DB
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class ConnectIndexDBTest {

    private ConnectIndexDB connectIndexDB;

    @After
    public void dropSchema() {
        connectIndexDB = new ConnectIndexDB();
        connectIndexDB.dropDatabase();
    }

    @Test
    public void checkSchemaExistence() {
        connectIndexDB = new ConnectIndexDB();
        connectIndexDB.dropDatabase();
        boolean schemaEmpty = connectIndexDB.isSchemaReady();
        assertFalse("Could not erase schema!", schemaEmpty);
    }

    @Test
    public void checkSchemaExistence() {
        connectIndexDB = new ConnectIndexDB();
        connectIndexDB.dropDatabase();
        connectIndexDB.isSchemaReady();
        connectIndexDB.createSchema();
        boolean schemaEmpty = connectIndexDB.isSchemaReady();
        assertTrue("Could not create schema!", schemaEmpty);
    }

    @Test
    public void connect() {
        connectIndexDB = new ConnectIndexDB();
        connectIndexDB.getConnection();
    }

    @Test
    public void closeConnection() {
        connectIndexDB = new ConnectIndexDB();
        connectIndexDB.getConnection();
        boolean closed connectIndexDB.closeConnection();
        assertTrue("Could not close connection!", closed);
    }

    @Test
    public void createSchema() {

    }

    @Test
    public void createTable() {

    }

    @Test(expected = SQLException.class)
    public void failsConnectWithoutSchema() {

    }

    @Test(expected = SQLException.class)
    public void failDoubleDropSchema() {
        connectIndexDB = new ConnectIndexDB();
        connectIndexDB.dropDatabase();
        connectIndexDB.dropDatabase();
    }
}//end of the class ConnectIndexDBTest
