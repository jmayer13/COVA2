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

import cova2.util.LogManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JOptionPane;

/**
 * Manage the creatiuon of instences of connection classes
 *
 * @see cova2.dao.ConnectIndexDB
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class ConnectionFactory {

    //volatile = variable's value will be modified by different threads
    private static volatile ConnectIndexDB _connectionDatabase;

    /**
     * Manage the creation intances to ConnectIndexDB
     * <br /><b>thread-safe</b><br />
     *
     * @return <code>ConnectIndexDB</code>
     */
    public static ConnectIndexDB getConnection() {
        if (_connectionDatabase == null) {
            synchronized (ConnectionFactory.class) {
                try {
                    Constructor constructors[] = ConnectIndexDB.class.getDeclaredConstructors();
                    constructors[0].setAccessible(true);
                    _connectionDatabase = (ConnectIndexDB) constructors[0].newInstance();
                } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
                    exception.printStackTrace();
                    LogManager logManager = new LogManager(ConnectIndexDB.class.getName());
                    logManager.error("Was not possible create a connection with relational DB", exception);
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

                }
            }
        }
        return _connectionDatabase;
    }//enf of method getConnection
}//end of class ConnectionFactory 
