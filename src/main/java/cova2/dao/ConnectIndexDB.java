package cova2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Connect to the IndexDB
 *
 * @see cova2.dao.ConnectionFactory
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class ConnectIndexDB {

    //connection
    private Connection _connection;
    //variables to db connections
    private final String DRIVE_CLASS = "org.h2.Driver";
    private final String URL = "jdbc:h2:./data/db/index";
    private final String SAFE_URL = "jdbc:h2:./data/db/index;IFEXISTS=TRUE";
    private final String USER_NAME = "cova2";
    private final String PASSSWORD = "";

    /**
     * Private constructor, starts drive Use it to connect      <code>
     *          Constructor constructors[] = ConnectionDatabase.class.getDeclaredConstructors();
     *          constructors[0].setAccessible(true);
     *          connectionDatabase = (ConnectionDatabase) constructors[0].newInstance();
     * </code>
     *
     * @throws ClassNotFoundException drive not found
     */
    private ConnectIndexDB() throws ClassNotFoundException {
        try {
            Class.forName(DRIVE_CLASS);
        } catch (ClassNotFoundException classNotFoundException) {
            Logger logger = LogManager.getLogger(ConnectIndexDB.class.getName());
            logger.error("Could not found the IndexDB drive.", classNotFoundException);
        }
    }//end of the constructor

    /**
     * Conect with a unsafe URL, if the database is not created, it will create
     * the database
     *
     * @return <code>Connection</code> connection with indexDB
     * @throws SQLException access error or null arguments
     */
    public Connection connect() throws SQLException {
        _connection = DriverManager.getConnection(URL, USER_NAME, PASSSWORD);
        return _connection;
    }//end of the method connect

    /**
     * Get the connection, check the database and create the database if it are
     * not created
     * <b>This is the only method to call to get a connection</b>
     *
     * @return <code>Connection</code> connection with indexDB
     * @throws SQLException access error or null arguments
     * @throws ClassNotFoundException access error or null arguments whe
     * checking the connection
     */
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if (isDatabaseReady()) {
            connect();
            return _connection;
        } else {
            connect();
            createTable();
            if (isDatabaseReady()) {
                return _connection;
            } else {
                Logger logger = LogManager.getLogger(ConnectIndexDB.class.getName());
                logger.error("Could not create database!: SQL script of creation ineffective!");
                throw new SQLException("Could not create database!: SQL script of creation ineffective! ");
            }
        }
    }//end of the method getConnection

    /**
     * Create the tables
     *
     * @return <code>boolean</code> creation was valid
     * @throws SQLException sql script error or connection errors
     */
    public boolean createTable() throws SQLException {
        boolean result;
        String tableSQL = "CREATE TABLE index (code_index INT PRIMARY KEY, main_title VARCHAR(2000), code_anime INT);";
        PreparedStatement tableCreator = _connection.prepareStatement(tableSQL);
        result = tableCreator.execute();
        tableCreator.close();
        return result;
    }//end of the method createTable

    /**
     * Delete the database and the data
     *
     * @return <code>boolean</code> drop was valid
     * @throws SQLException error with sql script
     * @throws NullPointerException is case of connection not created
     */
    public boolean dropDatabase() throws SQLException, NullPointerException {
        if (_connection != null) {
            boolean result;
            String eraseSQL = "DROP ALL OBJECTS DELETE FILES;";
            PreparedStatement databaseEraser = _connection.prepareStatement(eraseSQL);
            result = databaseEraser.execute();
            databaseEraser.close();
            return result;
        } else {
            throw (new NullPointerException("The connection is not active!"));
        }
    }//end of method dropDatabase

    /**
     * Check if the database is ready to be used
     *
     * @return <code>boolean</code> database ready
     * @throws ClassNotFoundException access error or null arguments
     */
    public boolean isDatabaseReady() throws ClassNotFoundException {
        boolean result = true;
        try {
            //an exception is throw if the database do not exist
            _connection = null;
            _connection = DriverManager.getConnection(SAFE_URL, USER_NAME, PASSSWORD);
        } catch (SQLException sqlException) {
            Logger logger = LogManager.getLogger(ConnectIndexDB.class.getName());
            logger.warn("Database is not ready.", sqlException);
            result = false;
        }
        if (_connection == null) {
            result = false;
        }
        return result;
    }//end of method isDatabaseReady

    /**
     * Close of the connection
     *
     * @return <code>boolean</code> could close connection
     * @throws SQLException problem closing connection
     * @throws NullPointerException connection not created
     */
    public boolean closeConnection() throws SQLException, NullPointerException {
        if (_connection != null) {
            _connection.close();
            return _connection.isClosed();
        } else {
            throw (new NullPointerException("The connection is not active!"));
        }
    }//end of method closeConnection

}//end of the class ConnectIndexDB  
