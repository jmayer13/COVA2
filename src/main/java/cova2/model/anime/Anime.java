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
