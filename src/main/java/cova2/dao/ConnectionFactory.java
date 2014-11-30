package cova2.dao;

import java.lang.reflect.Constructor;

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
                } catch (Exception exception) {
                    exception.printStackTrace();
                    System.exit(1);
                }
            }
        }
        return _connectionDatabase;
    }//wnf of method getConnection
}//end of class ConnectionFactory 
