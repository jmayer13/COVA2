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

import cova2.controller.AddAnimeController;
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
 * Test to feature edit anime
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class StepsEditAnimeTest {

    private String titleAnime;
    private MainController mainController;
    private AddAnimeController addAnimeController;
    private Index indexRegisterd;
    private Anime animeRegisterd;
    private double episode;

    /**
     * Assert "Given I have the anime "Bleach""
     *
     * @param title Bleach
     * @throws Throwable
     */
    @Given("^I have the anime \"(.*?)\"$")
    public void i_have_the_anime(String title) throws SQLException, ClassNotFoundException {
        mainController = new MainController();
        Index indexBleach = new Index(title, 43);
        Anime animeBleach = new Anime();
        animeBleach.setCodeAnime(43);
        animeBleach.setCurrentEpisode(1);
        IndexDAO indexDAO = new IndexDAO();
        indexRegisterd = indexDAO.addIndex(indexBleach);
        AnimeDAO animeDAO = new AnimeDAO();
        animeDAO.createAnime(animeBleach);
        animeRegisterd = animeBleach;
    }//end of the method i_have_the_anime

    /**
     * Assert "When I click on edit"
     *
     * @throws Throwable
     */
    @When("^I click on edit$")
    public void i_click_on_edit() throws Throwable {
        addAnimeController = mainController.editAnime(indexRegisterd, animeRegisterd);
    }//end of the method i_click_on_edit

    /**
     * Assert "And I increase the current episode in 1 "
     *
     * @param addIndex
     * @throws Throwable
     */
    @When("^I increase the current episode in (\\d+)$")
    public void i_increase_the_current_episode_in(int addIndex) throws Throwable {
        episode = addAnimeController.getView().getCurrentEpisode() + addIndex;
        addAnimeController.getView().setCurrentEpisode(episode);
        addAnimeController.okAction();
    }//end of the method i_increase_the_current_episode_in

    /**
     * Assert "Then I should see the anime "Bleach" on the list with my current
     * episode"
     *
     * @param arg1
     * @throws Throwable
     */
    @Then("^I should see the anime \"(.*?)\" on the list with my current episode$")
    public void i_should_see_the_anime_on_the_list_with_my_current_episode(String arg1) throws Throwable {
        Index index = mainController.getIndexRow(0);
        Anime anime = mainController.getAnimeRow(0);

        assertThat(anime.getCurrentEpisode(), is(episode));
    }//end of the method i_should_see_the_anime_on_the_list_with_my_current_episode

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

}//end of the class StepsEditAnimeTest 
