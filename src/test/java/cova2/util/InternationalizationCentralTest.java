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
package cova2.util;

import org.junit.Before;

/**
 * Test InternationalizationCentral
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class InternationalizationCentralTest {

    //class tested
    private InternationalizationCentral internationalizationCentral;

    /**
     * Initiate class to test
     */
    @Before
    public void initiaze() {
        internationalizationCentral = new InternationalizationCentral() {
            @Override
            public String getWord(String key) {
                return "";
            }
        };
    }//end of the method initiate 

}//end of the test class InternationalizationCentralTest
