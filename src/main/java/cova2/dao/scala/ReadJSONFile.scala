package cova2.dao.scala
import java.io.File
import scala.io.Source
/**
 * Read JSON file
 */
class ReadJSONFile {
  /**
   * Read json file
   * title - code anime
   */
  def read(title: String) = {
    val folder = "data"+File.separator+"anime"+File.separator;
    Source.fromFile( folder+title+".json") ;
  }//end of the method ReadJSONFile
}//end of the class ReadJSONFile
