package cova2.dao;

import cova2.model.index.Index;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manage persistence of index
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class IndexDAO {

    private Connection _connection;

    /**
     * Constructor get connection with the database
     *
     * @throws SQLException problem when generate DB
     * @throws ClassNotFoundException drive not found
     */
    public IndexDAO() throws SQLException, ClassNotFoundException {
        ConnectIndexDB connectIndexDB = ConnectionFactory.getConnection();
        _connection = connectIndexDB.getConnection();
    }//end of the constructor

    /**
     * Register Index in IndexDB
     *
     * @param index to register
     * @return <code>Boolean</code>
     * @throws SQLException if SQL script failed
     */
    public boolean addIndex(Index index) throws SQLException {
        PreparedStatement registerStatement = _connection.prepareStatement("INSERT INTO index (code_index, main_title, code_anime) VALUES(?,?,?);");
        registerStatement.setInt(1, index.getCodeIndex());
        registerStatement.setString(2, index.getMainTitleAnime());
        registerStatement.setInt(3, index.getCodeAnime());
        int lines = registerStatement.executeUpdate();
        if (lines > 0) {
            return true;
        } else {
            return false;
        }
    } //end of method addIndex

    /**
     * Get all the Indexes registered
     *
     * @return <code>List</code> Index
     * @throws java.sql.SQLException if SQL script failed
     */
    public List<Index> getIndexes() throws SQLException {
        List<Index> indexes = new ArrayList();
        PreparedStatement searchStatement = _connection.prepareStatement("SELECT code_index, main_title, code_anime FROM index;");
        ResultSet resultSet = searchStatement.executeQuery();
        while (resultSet.next()) {
            int codeIndex = resultSet.getInt(1);
            String titleAnime = resultSet.getString(2);
            int codeAnime = resultSet.getInt(3);
            Index index = new Index(codeIndex, titleAnime, codeAnime);
            indexes.add(index);
        }
        return indexes;
    }//end of the method getIndexes

    /**
     * Delete the index
     *
     * @param index
     * @return
     * @throws SQLException
     */
    public boolean deleteIndex(Index index) throws SQLException {
        PreparedStatement deleteStatement = _connection.prepareStatement("DELETE FROM index WHERE code_index = (?);");
        deleteStatement.setInt(1, index.getCodeIndex());
        int lines = deleteStatement.executeUpdate();
        if (lines > 0) {
            return true;
        } else {
            return false;
        }
    }//end of the method deleteIndex

}//end of class IndexDAO 
