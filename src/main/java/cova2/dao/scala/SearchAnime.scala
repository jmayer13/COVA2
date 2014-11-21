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
  def searchCodeAnime (codigoAnime : String): Anime = {
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
    return anime;
  }//end of the method searchCodeAnime
}//end of the class SearchAnime

