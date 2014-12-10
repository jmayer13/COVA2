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
