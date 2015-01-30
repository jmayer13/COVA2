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
