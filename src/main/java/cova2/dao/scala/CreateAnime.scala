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
import org.json4s._
import org.json4s.jackson.JsonMethods._
import cova2.model.anime.Anime
import org.json4s.JsonDSL._

/**
 * Create anime
 */
class CreateAnime {
  /**
   * Create json for anime
   */
  def createAnime (anime : Anime)={
    val json = 
      ("code_anime"->anime.getCodeAnime)~
    ("current_episode"->anime.getCurrentEpisode);
    val writeJSON = new  WriteJSONFile
    writeJSON.write(pretty(render(json)), anime.getCodeAnime.toString );
  }//end of the method createAnime
}//end of the class CreateAnime
