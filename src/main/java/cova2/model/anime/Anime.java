package cova2.model.anime;

/**
 * Anime model MVP version
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class Anime {

    private int _codeAnime;
    private double _currentEpisode;

    /**
     * Set the code of the anime
     *
     * @param codeAnime code of the anime based on MAO
     */
    public void setCodeAnime(int codeAnime) {
        _codeAnime = codeAnime;
    }//end of the method setCodeAnime

    /**
     * Set current episode being watched
     *
     * @param currentEpisode last episode wached
     */
    public void setCurrentEpisode(double currentEpisode) {
        _currentEpisode = currentEpisode;
    }//end of the method setCurrentEpisode

    /**
     * Get code of the anime
     *
     * @return <code>Integer</code> code of the anime
     */
    public int getCodeAnime() {
        return _codeAnime;
    }//end of the method getCodeAnime

    /**
     * Get current episode
     *
     * @return <code>Double</code> current episode
     */
    public double getCurrentEpisode() {
        return _currentEpisode;
    }//end of the method getCurrentEpisode

}//end of the class Anime 
