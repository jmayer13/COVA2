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

import cova2.update.UpdateLoader;
import cova2.util.LogManager;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class UpdateCheck {

    private UpdateLoader updateLoader;
    private String currentVersion = "1.0.0.0";
    private LogManager logManager;

    /**
     * Empty constructor
     */
    public UpdateCheck() {
        logManager = new LogManager(UpdateCheck.class.getName());
        updateLoader = new UpdateLoader(currentVersion);
    }//end of the constructor 

    /**
     * Verify updates
     *
     * @return <code>Boolean</code> true - has update
     */
    public boolean hasUpdate() {
        try {
            return updateLoader.isUpdateAvaliable();
        } catch (IOException ex) {
            logManager.error("Error searching update:", ex);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Couldn't update:" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }//end of the method hasUpdate

}//end of the class UpdateCheck 
