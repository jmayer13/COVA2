/**
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package cova2.dao;

import java.io.File;
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
public class ConnectIndexDBIT {

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
        connectIndexDB = ConnectionFactory.getConnection();
    }//end of the method intenceClass

    /**
     * Delete the database
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @After
    public void cleanData() throws SQLException, ClassNotFoundException {
     //   connectIndexDB.dropDatabase();
        //         connectIndexDB.closeConnection();
        //     connectIndexDB = null;
        File dir = new File("data" + File.separator + "db");
        for (File file : dir.listFiles()) {
            file.delete();
        }
        dir.delete();
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
        connectIndexDB.createTable();

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
}//end of the class ConnectIndexDBIT 
