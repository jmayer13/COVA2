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
