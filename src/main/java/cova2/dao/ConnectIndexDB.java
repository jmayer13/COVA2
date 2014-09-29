package cova2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Descrição
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class ConnectIndexDB {

    private Connection _connection;
    private final String DRIVE_CLASS = "org.h2.Driver";
    private final String URL = "jdbc:h2:./data/db/indexDB";
    private final String SAFE_URL = "jdbc:h2:./data/db/index;IFEXISTS=TRUE";
    private final String USER_NAME = "cova2";
    private final String PASSSWORD = "";
    private final String SCHEMA_NAME = "indexDB";

    private ConnectIndexDB() {
        if (isSchemaReady()) {
            _connection = this.connect();
        } else {

        }
    }

    private Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVE_CLASS);
        _connection = DriverManager.getConnection(URL, USER_NAME, PASSSWORD);
        return _connection;
    }

    public boolean createSchema() throws SQLException, ClassNotFoundException {
        boolean result;
        String schemaSQL = "CREATE SCHEMA " + SCHEMA_NAME;
        PreparedStatement schemaStatement = connection.prepareStatement(schemaSQL);
        result = schemaStatement.execute();
        //create table index
        schemaStatement.close();
        createTable();
        connection.close();
        return result;
    }

    public boolean createTable() throws SQLException {
        boolean result;
        String tableSQL = "CREATE TABLE IF NOT EXISTS index (code_index INT PRIMARY KEY, main_title VARCHAR(2000), code_anime INT)";
        PreparedStatement tableCreator = connection.prepareStatement(tableSQL);
        result = tableCreator.execute();
        tableCreator.close();
        return result;
    }

    public boolean dropDatabase() throws SQLException, ClassNotFoundException {
        boolean result;
        Connection connection = connect();
        String eraseSQL = "DROP SCHEMA IF EXISTS " + SCHEMA_NAME;
        PreparedStatement schemaEraser = connection.prepareStatement(eraseSQL);
        result = schemaEraser.execute();
        schemaEraser.close();
        connection.close();
        return result;
    }

    public boolean isSchemaReady() throws ClassNotFoundException {
        boolean result = true;
        Class.forName(DRIVE_CLASS);
        try {
            //an exception is throw if the schema do not exist
            connection = DriverManager.getConnection(SAFE_URL, USER_NAME, PASSSWORD);
        } catch (SQLException ex) {
            result = false;
        }
        return result;
    }

}//enf of the class ConnectIndexDB 
