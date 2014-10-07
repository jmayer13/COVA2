package cova2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Descrição
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class ConnectIndexDB {

    private Connection _connection;
    private final String DRIVE_CLASS = "org.h2.Driver";
    private final String URL = "jdbc:h2:./data/db/index";
    private final String SAFE_URL = "jdbc:h2:./data/db/index;IFEXISTS=TRUE";
    private final String USER_NAME = "cova2";
    private final String PASSSWORD = "";

    private ConnectIndexDB() throws ClassNotFoundException, SQLException {
        try {
            Class.forName(DRIVE_CLASS);
        } catch (ClassNotFoundException classNotFoundException) {
            Logger logger = LogManager.getLogger(ConnectIndexDB.class.getName());
            logger.error("Could not found the IndexDB drive.", classNotFoundException);
        }
    }

    public Connection connect() throws SQLException, ClassNotFoundException {
        _connection = DriverManager.getConnection(URL, USER_NAME, PASSSWORD);
        return _connection;
    }

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
    }

    public boolean createTable() throws SQLException {
        boolean result;
        String tableSQL = "CREATE TABLE index (code_index INT PRIMARY KEY, main_title VARCHAR(2000), code_anime INT)";
        PreparedStatement tableCreator = _connection.prepareStatement(tableSQL);
        result = tableCreator.execute();
        tableCreator.close();
        return result;
    }

    public boolean dropDatabase() throws SQLException, ClassNotFoundException {
        if (_connection != null) {
            boolean result;
            String eraseSQL = "DROP ALL OBJECTS";
            PreparedStatement databaseEraser = _connection.prepareStatement(eraseSQL);
            result = databaseEraser.execute();
            databaseEraser.close();
            return result;
        } else {
            throw (new NullPointerException("The connection is not active!"));
        }
    }

    public boolean isDatabaseReady() throws ClassNotFoundException {
        boolean result = true;
        try {
            //an exception is throw if the database do not exist
            _connection = null;
            _connection = DriverManager.getConnection(SAFE_URL, USER_NAME, PASSSWORD);
        } catch (SQLException exception) {
            exception.printStackTrace();
            Logger logger = LogManager.getLogger(ConnectIndexDB.class.getName());
            logger.warn("Database is not ready.", exception);
            result = false;
        }
        if (_connection == null) {
            result = false;
        }
        return result;
    }

    public boolean closeConnection() throws SQLException {
        if (_connection != null) {
            _connection.close();
            return _connection.isClosed();
        } else {
            throw (new NullPointerException("The connection is not active!"));
        }
    }

}//enf of the class ConnectIndexDB  
