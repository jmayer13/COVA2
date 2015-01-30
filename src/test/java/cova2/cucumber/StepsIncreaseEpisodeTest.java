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
package cova2.cucumber;

import cova2.controller.MainController;
import cova2.dao.AnimeDAO;
import cova2.dao.IndexDAO;
import cova2.model.anime.Anime;
import cova2.model.index.Index;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.File;
import java.sql.SQLException;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test for feature Increase episode
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class StepsIncreaseEpisodeTest {

    private MainController mainController;
    private Index indexRegistered;
    private Anime animeRegistered;
    private double episodeIncresed;

    /**
     * Assert "Given I select the anime "Bleach" with current episode 1"
     *
     * @param title Bleach
     * @param episode 1
     * @throws Throwable
     */
    @Given("^I select the anime \"(.*?)\" with current episode (\\d+)$")
    public void i_select_the_anime_with_current_episode(String title, double episode) throws Throwable {
        Index index = new Index(title, 46);
        Anime anime = new Anime();
        anime.setCodeAnime(46);
        anime.setCurrentEpisode(episode);
        IndexDAO indexDAO = new IndexDAO();
        indexRegistered = indexDAO.addIndex(index);
        AnimeDAO animeDAO = new AnimeDAO();
        animeDAO.createAnime(anime);
        episodeIncresed = episode + 1;
        animeRegistered = anime;
        mainController = new MainController();
    }//end of the method i_select_the_anime_with_current_episode

    /**
     * Assert "When I click on + "
     *
     * @throws Throwable
     */
    @When("^I click on \\+$")
    public void i_click_on() throws Throwable {
        mainController.increaseEpisode(animeRegistered);
    }//end of the method i_click_on

    /**
     * Assert "Then I should see the anime one episode bigger"
     *
     * @throws Throwable
     */
    @Then("^I should see the anime one episode bigger$")
    public void i_should_see_the_anime_one_episode_bigger() throws Throwable {
        assertThat(mainController.getAnimeRow(0).getCurrentEpisode(), is(episodeIncresed));
    }//end of the method i_should_see_the_anime_one_episode_bigger

    /**
     * Clean data of test
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @After
    public void cleanData() throws SQLException, ClassNotFoundException {
        IndexDAO indexDAO = new IndexDAO();
        indexDAO.closeConnection();
        File dirDB = new File("data" + File.separator + "db");
        for (File file : dirDB.listFiles()) {
            file.delete();
        }
        dirDB.delete();
        File dirJSON = new File("data" + File.separator + "anime");
        for (File file : dirJSON.listFiles()) {
            file.delete();
        }
        dirJSON.delete();
    }//end of the method cleanData
}//end of the class StepsIncreaseEpisodeTest
