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
    public Index addIndex(Index index) throws SQLException {
        PreparedStatement registerStatement = _connection.prepareStatement("INSERT INTO index (main_title, code_anime) VALUES(?,?);");
        registerStatement.setString(1, index.getMainTitleAnime());
        registerStatement.setInt(2, index.getCodeAnime());
        int lines = registerStatement.executeUpdate();
        if (lines > 0) {
            index.setCodeIndex(rescueCodeIndex(index));
            return index;
        } else {
            return null;
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

    /**
     * Get code recommended to new anime registered (last one +1)
     *
     * @return <code>Integer</code> code anime recommended
     * @throws SQLException
     */
    public int getRecommendedCodeAnime() throws SQLException {
        int recomendedCodeAnime = 1;
        PreparedStatement maxStatement = _connection.prepareStatement("SELECT MAX(code_anime) FROM index;");
        ResultSet resultSet = maxStatement.executeQuery();
        while (resultSet.next()) {
            recomendedCodeAnime = resultSet.getInt(1);
            recomendedCodeAnime++;
        }
        return recomendedCodeAnime;
    }//end of the method getRecommendedCodeAnime

    /**
     * Rescue code Index from index thought the search by name
     *
     * @param index
     * @return
     * @throws SQLException
     */
    protected int rescueCodeIndex(Index index) throws SQLException {
        PreparedStatement rescueStatement = _connection.prepareStatement("SELECT code_index FROM index WHERE main_title=(?) AND  code_anime = (?) ;");
        rescueStatement.setString(1, index.getMainTitleAnime());
        rescueStatement.setInt(2, index.getCodeAnime());
        ResultSet resultSet = rescueStatement.executeQuery();
        while (resultSet.next()) {
            return resultSet.getInt(1);

        }
        return -1;
    }//end the method rescueCodeIndex

}//end of class IndexDAO 
