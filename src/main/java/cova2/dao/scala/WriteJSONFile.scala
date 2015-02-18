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
package cova2.dao.scala
import java.io._

/**
 * Write JSON Files in data/anime folder
 */
class WriteJSONFile {
  /**
   * Write anime's json in json file
   */
  def write(json: String,title: String){
    val dir = new File("data"+File.separator+"anime");
    if(!dir.exists){
      dir.mkdirs
    }
    val file = new File(dir,title+".json");
    val writer = new PrintWriter(file);
    writer write json;
    writer.close();
  }//end of the method write
}//end of the class WriteJSONFile
