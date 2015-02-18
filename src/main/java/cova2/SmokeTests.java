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
package cova2;

import cova2.util.LogManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 * Smole test - verify basic requeriments to run COVA
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class SmokeTests {

    private LogManager logManager;
    private PrintWriter printWriter;

    /**
     * Constructor without parameter
     */
    public SmokeTests() {
        logManager = new LogManager(SmokeTests.class.getName());
    }//end of the constructor SmokeTests

    /**
     * Verify if os the only instance openned
     *
     * @return <code>Boolean</code> true -unique instance
     */
    public boolean isSingleInstance() {
        boolean single = false;
        logManager.info("Verifing if this is the only instance!");
        File fileLock = new File("lock");
        if (!fileLock.exists()) {
            single = true;
        } else {
            single = fileLock.delete();
        }
        if (single) {
            try {
                printWriter = new PrintWriter(fileLock);
            } catch (FileNotFoundException ex) {
                logManager.error("Error deleting data", ex);
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }
        return single;
    }//end of the method isSingleInstance

    /**
     * Verify if COVA is allowed to read and edit files
     *
     * @return <code>Boolean</code> answer
     */
    public boolean hasFilePermission() throws IOException {
        File fileTest = new File("test");
        if (!fileTest.exists()) {
            fileTest.createNewFile();
        }
        if (fileTest.canRead() && fileTest.canWrite()) {
            return true;
        } else {
            return false;
        }
    }//end of the method hasFilePermission

}//end of the class SmokeTests 
