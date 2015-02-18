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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class SmokeTestsIT {

    @Test
    public void testIsSingleInstance() {
        SmokeTests smokeTests = new SmokeTests();
        assertTrue(smokeTests.isSingleInstance());
    }

    @Test
    public void testIsNotSingleInstance() {
        SmokeTests smokeTests = new SmokeTests();
        smokeTests.isSingleInstance();
        assertFalse(smokeTests.isSingleInstance());
    }

    @Test
    public void testHasFilePermission() {
        SmokeTests smokeTests = new SmokeTests();
        assertTrue(smokeTests.hasFilePermission());

    }

}//fim da classe SmokeTestsIT 
