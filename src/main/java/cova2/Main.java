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

import cova2.controller.MainController;
import cova2.util.LogManager;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Main class
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class Main {

    private LogManager logManager;

    /**
     * Constructor empty
     */
    public Main() {
        logManager = new LogManager(Main.class.getName());
    }//end of the constructor

    /**
     * Main method
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main main = new Main();
        main.startMain();
    }//end of the method main

    /**
     * Do the smoke tests
     *
     * @return <code>Boolean</code> result of the test
     */
    protected boolean doSmokeTests() {
        SmokeTests smokeTests = new SmokeTests();
        try {
            if (!smokeTests.hasFilePermission()) {
                JOptionPane.showMessageDialog(null, "I don't have permission to read/write files!", "WARNING!", JOptionPane.WARNING_MESSAGE);
                System.exit(1);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        if (smokeTests.isSingleInstance()) {
            return true;
        } else {
            return false;
        }
    }//end of the method doSmokeTests

    /**
     * Check if update is avaliable and update
     *
     * @return <code>Boolean</code> false if don't have update
     */
    protected boolean doUpdateCheck() {
        try {
            UpdateCheck updateCheck = new UpdateCheck();
            if (updateCheck.isUpdating()) {
                updateCheck.finishUpdate();
                return doUpdateCheck();
            } else if (updateCheck.checkUpdateFail()) {
                return false;
            } else if (updateCheck.hasUpdate()) {
                return true;
            } else {
                return false;
            }
        } catch (IOException exception) {
            logManager.debug("Error checking update", exception);
            JOptionPane.showMessageDialog(null, "Error checking update!");
            exception.printStackTrace();
            return false;
        }
    }//end of the method doUpdateCheck

    /**
     * Start main
     *
     * @return <code>Boolean</code> success?
     */
    public boolean startMain() {
        logManager.info("Running...");
        logManager.debug("Smoke Testing ...");
        if (doSmokeTests()) {
            logManager.debug("Verifing updates...");
            if (!doUpdateCheck()) {
                logManager.info("Starting...");
                MainController mainController = new MainController();
                return true;
            }
        }
        return false;
    }//end of the method startMain
}//end of the class Main
