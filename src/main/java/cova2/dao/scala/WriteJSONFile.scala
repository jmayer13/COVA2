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
    val writer = new PrintWriter(new File( "data"+File.separator+"anime",title+".json"));
    writer write json;
    writer.close();
  }//end of the method write
}//end of the class WriteJSONFile
