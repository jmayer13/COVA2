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

import java.io.File

/**
 * Delete json files
 */
class DeleteAnime {
  /**
   * title - code of anime
   */
  def delete(title: String){ 
    val file = new File( "data"+File.separator+"anime"+File.separator+title+".json");
    file.delete
  }//end of the method delete
}//end of the class DeleteAnime
