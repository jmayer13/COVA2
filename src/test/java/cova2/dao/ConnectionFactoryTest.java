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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;

/**
 * Test to ConnectionFactory
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class ConnectionFactoryTest {

    /**
     * Test the creation of a single insnce of ConnectIndexDB
     */
    @Test
    public void testCreateIntance() {
        ConnectIndexDB connectIndexDB1 = ConnectionFactory.getConnection();
        assertNotNull("Could not get 1º single instance!", connectIndexDB1);
    }//end of the method testCreateIntance

    @Test
    public void testCreateSingleIntance() {
        ConnectIndexDB connectIndexDB1 = ConnectionFactory.getConnection();
        ConnectIndexDB connectIndexDB2 = ConnectionFactory.getConnection();
        assertSame("The instance is not unique!", connectIndexDB1, connectIndexDB2);
    }//end of the method createSingleIntance

}//end of class ConnectionFactoryTest
