package cova2.dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test to ConnectIndexDB, that creates a connection with the Index DB
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class ConnectIndexDBTest {

    //class tested
    private ConnectIndexDB connectIndexDB;

    /**
     * Calls contructor
     *
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    @Before
    public void initialize() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Constructor constructors[] = ConnectIndexDB.class.getDeclaredConstructors();
        constructors[0].setAccessible(true);
        connectIndexDB = (ConnectIndexDB) constructors[0].newInstance();
    }//end of the method intenceClass

    /**
     * Delete the database
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @After
    public void testDropDatabase() throws SQLException, ClassNotFoundException {
        connectIndexDB.getConnection();
        connectIndexDB.dropDatabase();
        connectIndexDB.closeConnection();
        connectIndexDB = null;
    }//end of the method testDropDatabase

    /**
     * Check the creation of the database and their existence
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void testCheckDatabaseExistence() throws SQLException, ClassNotFoundException {
        connectIndexDB.getConnection();
        connectIndexDB.dropDatabase();
        connectIndexDB.getConnection();
        boolean databaseEmpty = connectIndexDB.isDatabaseReady();
        assertTrue("Could not create database!", databaseEmpty);
    }//end of method testCheckDatabaseExistence

    /**
     * Test the unsafe connection
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void testConnect() throws SQLException, ClassNotFoundException {
        Connection connection = connectIndexDB.getConnection();
        assertNotNull("Could not connect!", connection);
    }//end of the method testConnect

    /**
     * Test if close the connection with the database
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void testCloseConnection() throws SQLException, ClassNotFoundException {
        connectIndexDB.getConnection();
        boolean closed = connectIndexDB.closeConnection();
        assertTrue("Could not close connection!", closed);
    }//end of the method closeConnection

    /**
     * Test if the database is created
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void testCreateTable() throws SQLException, ClassNotFoundException {
        if (connectIndexDB.isDatabaseReady()) {
            connectIndexDB.dropDatabase();
        }
        connectIndexDB.connect();
        connectIndexDB.createTable();
        assertTrue("Could not create table!", connectIndexDB.isDatabaseReady());
    }//end of the method testCreateTable

}//end of the class ConnectIndexDBTest 
