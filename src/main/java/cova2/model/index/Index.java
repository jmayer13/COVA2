package cova2.model.index;

/**
 * Model of the anime's index, used as reference to data and stored in a
 * relational database
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class Index {

    //code index represents the primary key of the relational DB
    private int _codeIndex;
    //main title is the title selected to be showed in the list
    private String _mainTitleAnime;
    //code that represent the anime code of the JSON ad-hoc DB
    private int _codeAnime;

    /**
     * Contructor with data for the database
     *
     * @param codeIndex code index (primary key)
     * @param mainTitleAnime main title (title selected)
     * @param codeAnime code anime (key to JSON ad-hoc DB)
     */
    public Index(int codeIndex, String mainTitleAnime, int codeAnime) {
        setCodeIndex(codeIndex);
        setMainTitleAnime(mainTitleAnime);
        setCodeAnime(codeAnime);
    }//end of the constructor

    /**
     * Contructor with data for the software
     *
     * @param mainTitleAnime main title (title selected)
     * @param codeAnime code anime (key to JSON ad-hoc DB)
     */
    public Index(String mainTitleAnime, int codeAnime) {
        this.setMainTitleAnime(mainTitleAnime);
        this.setCodeAnime(codeAnime);
    }//end of the constructor

    /**
     * Define the code index, the primary key of the index DB
     *
     * @param codeIndex
     * @throws IllegalArgumentException if the code is not bigget that 0
     */
    public void setCodeIndex(int codeIndex) {
        if (codeIndex <= 0) {
            throw (new IllegalArgumentException("The codeIndex must be bigger that 0!"));
        } else {
            _codeIndex = codeIndex;
        }
    }//end of the method setCodeIndex

    /**
     * Define the main title, the title selected to be shown in the interface
     *
     * @param mainTitleAnime
     * @throws IllegalArgumentException if title empty or null
     */
    public void setMainTitleAnime(String mainTitleAnime) {
        if (mainTitleAnime == null || mainTitleAnime.equals("")) {
            throw (new IllegalArgumentException("The mainTitleAnime must not be empty!"));
        } else {
            _mainTitleAnime = mainTitleAnime;
        }
    }//end of the method setMainAnimeTitle

    /**
     * Define the code anime, key to the JSON file
     *
     * @param codeAnime
     * @throws IllegalArgumentException if code is not bigger or equals to 0
     */
    public void setCodeAnime(int codeAnime) {
        if (codeAnime < 0) {
            throw (new IllegalArgumentException("The codeAnime must be bigger that 0!"));
        } else {
            _codeAnime = codeAnime;
        }
    }//end of the method setCodeAnime

    /**
     * Obtain the code Index, the primary key of the Index DB
     *
     * @return <code>Integer</code> codeIndex
     */
    public int getCodeIndex() {
        return _codeIndex;
    }//end of the method getCodeIndex

    /**
     * Obtain the main title, title selected to be shown in the interface
     *
     * @return <code>String</code> mainTitleAnime
     */
    public String getMainTitleAnime() {
        return _mainTitleAnime;
    }//end of the method getMainAnimeTitle

    /**
     * Obtain code anime, code to acess the JSON ad-hoc DB
     *
     * @return <code>Integer</code> codeAnime
     */
    public int getCodeAnime() {
        return _codeAnime;
    }//end of the method getCodeAnime

}//end of the class Index 
