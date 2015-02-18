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

import java.io.IOException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Integration Test to smoke test
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class SmokeTestsIT {

    /**
     * test if is the simgle instance
     */
    @Test
    public void testIsSingleInstance() {
        SmokeTests smokeTests = new SmokeTests();
        assertTrue(smokeTests.isSingleInstance());
    }//end of the method testIsSingleInstance

    /**
     * Test if the mechanism of lock works
     */
    @Test
    public void testIsNotSingleInstance() {
        SmokeTests smokeTests = new SmokeTests();
        smokeTests.isSingleInstance();
        assertFalse(smokeTests.isSingleInstance());
    }//end of the method testIsNotSingleInstance

    /**
     *
     * @throws IOException
     */
    @Test
    public void testHasFilePermission() throws IOException {
        SmokeTests smokeTests = new SmokeTests();
        assertTrue(smokeTests.hasFilePermission());
    }//end of the method testHasFilePermission

}//end of the class SmokeTestsIT 
