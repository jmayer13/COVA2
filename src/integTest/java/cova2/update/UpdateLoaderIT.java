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
package cova2.update;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * Ibtegration Test to UpdateLoader
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class UpdateLoaderIT {

    private UpdateLoader updateLoader;
    private final String VERSION = "1.0.0.0";

    /**
     * Initializate class tested
     */
    @Before
    public void initialize() {
        updateLoader = new UpdateLoader(VERSION);
    }//end of the method initialize

    /**
     * Test run updater
     *
     * @throws IOException
     */
    @Test
    public void testRunUpdater() throws IOException {
        updateLoader.runUpdater();
    }//end of the method testRunUpdater 

}//end of the class UpdateLoaderIT
