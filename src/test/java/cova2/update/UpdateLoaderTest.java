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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test uploader
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class UpdateLoaderTest {

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
     * Test if version is higher
     */
    @Test
    public void testIsVersionHigher() {
        assertTrue(updateLoader.isVersionHigher("1.0.1.0"));
    }//end of the method testIsVersionHigher

    /**
     * Test if version is lower
     */
    @Test
    public void testNotIsVersionHigher() {
        assertFalse(updateLoader.isVersionHigher("0.9.0.0"));
    }//end of the method testNotIsVersionHigher

    /**
     * Test if slip version works
     */
    @Test
    public void testSplitVersion() {
        int[] testVersion = updateLoader.splitVersion("1.1.1.1");
        assertTrue("Do not split the version correctly!", testVersion[0] == 1
                && testVersion[1] == 1 && testVersion[2] == 1 && testVersion[3] == 1);
    }//end of the method testSplitVersion

}//end of the class UpdateLoaderTest
