package cova2.dao;

import cova2.util.LogManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

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
    private LogManager logManager;

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
            logManager = new LogManager(ConnectIndexDB.class.getName());
            Class.forName(DRIVE_CLASS);
        } catch (ClassNotFoundException ex) {
            logManager.error("Could not found the IndexDB drive.", ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

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
        logManager.debug("Creating conection...");
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
            logManager.debug("The schema is ready");
            connect();
            return _connection;
        } else {
            logManager.debug("The schema is not ready");
            connect();
            logManager.debug("Creating schema");
            createTable();
            if (isDatabaseReady()) {
                return _connection;
            } else {
                logManager.error("Could not create database!: SQL script of creation ineffective!");
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
        logManager.trace("Creating table");
        String tableSQL = "CREATE TABLE index (code_index INT AUTO_INCREMENT PRIMARY KEY, main_title VARCHAR(2000), code_anime INT);";
        PreparedStatement tableCreator = _connection.prepareStatement(tableSQL);
        result = tableCreator.execute();
        tableCreator.close();
        logManager.trace("TABLE OK: " + result);
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
            logManager.debug("Erasing database...");
            boolean result;
            String eraseSQL = "DROP ALL OBJECTS DELETE FILES;";
            PreparedStatement databaseEraser = _connection.prepareStatement(eraseSQL);
            result = databaseEraser.execute();
            databaseEraser.close();
            return result;
        } else {
            logManager.error("The connection is not active!");
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
            logManager.trace("Verifying if connection is ready");
            //an exception is throw if the database do not exist
            _connection = null;
            _connection = DriverManager.getConnection(SAFE_URL, USER_NAME, PASSSWORD);
        } catch (SQLException sqlException) {
            logManager.warn("Database is not ready", sqlException);
            result = false;
        }
        if (_connection == null) {
            result = false;
        }
        logManager.debug("Database is ready: " + result);
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
        logManager.trace("Closing connection ");
        if (_connection != null) {
            _connection.close();
            return _connection.isClosed();
        } else {
            logManager.debug("Closing ... The connection is not active!");
            throw (new NullPointerException("The connection is not active!"));
        }
    }//end of method closeConnection

}//end of the class ConnectIndexDB  
