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

import cova2.exception.DataAlreadyRegisteredException;
import cova2.exception.UnavailableDataException;
import cova2.model.index.Index;
import cova2.util.LogManager;
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
    private LogManager logManager;

    /**
     * Constructor get connection with the database
     *
     * @throws SQLException problem when generate DB
     * @throws ClassNotFoundException drive not found
     */
    public IndexDAO() throws SQLException, ClassNotFoundException {
        logManager = new LogManager(IndexDAO.class.getName());
        _connection = connect();
    }//end of the constructor

    /**
     * Connect with the reational database IndexDB
     */
    protected Connection connect() throws SQLException, ClassNotFoundException {
        logManager.trace("Connecting with database ...");
        ConnectIndexDB connectIndexDB = ConnectionFactory.getConnection();
        return connectIndexDB.getConnection();
    }//end of the method connect

    /**
     * Register Index in IndexDB
     *
     * @param index to register
     * @return <code>Boolean</code>
     * @throws SQLException if SQL script failed
     */
    public Index addIndex(Index index) throws SQLException, DataAlreadyRegisteredException {
        logManager.debug("Add Index " + index.getMainTitleAnime() + "with codeAnime " + index.getCodeAnime());
        if (selectIndex(index.getCodeIndex()) != null) {
            throw new DataAlreadyRegisteredException();
        }
        int lines = insertIndex(index);
        logManager.debug("Index Added");
        if (lines > 0) {
            index.setCodeIndex(rescueCodeIndex(index));
            logManager.debug("Code index returned " + index.getCodeIndex());
            return index;
        } else {
            logManager.error("Error adding Index! The query didn't returned lines");
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
        List<Index> indexes = selectIndexes();
        logManager.debug("Getting " + indexes.size() + " indexes from database");
        return indexes;
    }//end of the method getIndexes

    /**
     * Delete the index
     *
     * @param index
     * @return boolean result of operation
     * @throws SQLException
     */
    public boolean eraseIndex(Index index) throws SQLException, UnavailableDataException {
        if (index.getCodeIndex() <= 0) {
            logManager.error("Delleting Index... CodeIndex invalid");
            throw new IllegalArgumentException("Code Index should be biiger that 0!");
        }
        if (selectIndex(index.getCodeIndex()) == null) {
            throw new UnavailableDataException();
        }
        logManager.debug("Index " + index.getCodeIndex() + " deleted");
        int lines = deleteIndex(index);
        return lines > 0;

    }//end of the method deleteIndex

    /**
     * Get code recommended to new anime registered (last one +1)
     *
     * @return <code>Integer</code> code anime recommended
     * @throws SQLException
     */
    public int getRecommendedCodeAnime() throws SQLException {
        logManager.trace("Getting recommended codeAnime");
        int recomendedCodeAnime = 1;
        PreparedStatement maxStatement = _connection.prepareStatement("SELECT MAX(code_anime) FROM index;");
        ResultSet resultSet = maxStatement.executeQuery();
        while (resultSet.next()) {
            recomendedCodeAnime = resultSet.getInt(1);
            recomendedCodeAnime++;
        }
        resultSet.close();
        maxStatement.close();
        logManager.debug("The codeAnime recommended is " + recomendedCodeAnime);
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
        int codeIndex = -1;
        while (resultSet.next()) {
            logManager.debug("The codeIndex of this index is " + resultSet.getInt(1));

            codeIndex = resultSet.getInt(1);
        }

        resultSet.close();
        rescueStatement.close();
        logManager.error("Was not possible get the codeIndex");

        return codeIndex;
    }//end the method rescueCodeIndex

    /**
     * Close connection with database Warning: throws exception if someone tries
     * use it after closed
     *
     * @throws java.sql.SQLException
     */
    public void closeConnection() throws SQLException {
        logManager.debug("Close connection of IndexDAO");
        ConnectIndexDB connectIndexDB = ConnectionFactory.getConnection();
        connectIndexDB.closeConnection();

    }//end of the method closeConnection

    public void editIndex(Index index) throws SQLException, UnavailableDataException {
        if (index.getCodeIndex() <= 0) {
            throw new NullPointerException("The codeIndex is lower than 1");
        }
        logManager.debug("Edit Index " + index.getMainTitleAnime() + "with codeAnime " + index.getCodeAnime());
        if (selectIndex(index.getCodeIndex()) == null) {
            throw new UnavailableDataException();
        }
        updateIndex(index);

        logManager.debug("Index Edited");

    }

    protected int insertIndex(Index index) throws SQLException {
        PreparedStatement registerStatement = _connection.prepareStatement("INSERT INTO index (main_title, code_anime) VALUES(?,?);");
        registerStatement.setString(1, index.getMainTitleAnime());
        registerStatement.setInt(2, index.getCodeAnime());

        int result = registerStatement.executeUpdate();
        registerStatement.close();
        return result;
    }

    protected List<Index> selectIndexes() throws SQLException {
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
        resultSet.close();
        searchStatement.close();
        return indexes;
    }

    protected Index selectIndex(int codeIndex) throws SQLException {
        Index index = null;
        PreparedStatement searchStatement = _connection.prepareStatement("SELECT   main_title, code_anime FROM index WHERE code_index = (?);");
        searchStatement.setInt(1, codeIndex);
        ResultSet resultSet = searchStatement.executeQuery();
        while (resultSet.next()) {
            String titleAnime = resultSet.getString(1);
            int codeAnime = resultSet.getInt(2);
            index = new Index(codeIndex, titleAnime, codeAnime);

        }
        resultSet.close();
        searchStatement.close();
        return index;
    }

    protected void updateIndex(Index index) throws SQLException {
        PreparedStatement editerStatement = _connection.prepareStatement("UPDATE index SET main_title=?, code_anime=? WHERE code_index=?;");
        editerStatement.setString(1, index.getMainTitleAnime());
        editerStatement.setInt(2, index.getCodeAnime());
        editerStatement.setInt(3, index.getCodeIndex());
        editerStatement.executeUpdate();
        editerStatement.close();
    }

    protected int deleteIndex(Index index) throws SQLException {
        PreparedStatement deleteStatement = _connection.prepareStatement("DELETE FROM index WHERE code_index = (?);");
        deleteStatement.setInt(1, index.getCodeIndex());
        int lines = deleteStatement.executeUpdate();
        deleteStatement.close();
        return lines;
    }

}//end of class IndexDAO 
