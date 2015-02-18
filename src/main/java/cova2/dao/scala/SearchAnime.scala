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
import cova2.model.anime.Anime
import org.json4s._
import org.json4s.Formats
import org.json4s.jackson.JsonMethods._

/**
 * Search by anime
 */
class SearchAnime {
  /**
   * Search anime by code
   */
  def searchCodeAnime (codigoAnime : String): Anime ={
    val readJSON = new ReadJSONFile();
    val file = readJSON.read(codigoAnime);
    val json = parse(file.mkString);
    file.close;
    val anime = new Anime;
    implicit val formats = DefaultFormats
    val code_anime =(json \ "code_anime").extract[Int]
    val current_episode =(json \ "current_episode").extract[Int]
    anime.setCodeAnime(code_anime);
    anime.setCurrentEpisode(current_episode);
    anime;
  }//end of the method searchCodeAnime
}//end of the class SearchAnime

