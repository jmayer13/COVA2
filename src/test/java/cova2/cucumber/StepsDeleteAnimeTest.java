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
 * Test for feature delete anime
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class StepsDeleteAnimeTest {

    private MainController mainController;
    private Index indexRegistered;
    private Anime animeRegistered;

    /**
     * Assert "Given I select the anime "Bleach""
     *
     * @param title Bleach
     * @throws Throwable
     */
    @Given("^I select the anime \"(.*?)\"$")
    public void i_select_the_anime(String title) throws Throwable {
        Index index = new Index(title, 46);
        Anime anime = new Anime();
        anime.setCodeAnime(46);
        anime.setCurrentEpisode(0);
        IndexDAO indexDAO = new IndexDAO();
        indexRegistered = indexDAO.addIndex(index);
        AnimeDAO animeDAO = new AnimeDAO();
        animeDAO.createAnime(anime);
        animeRegistered = anime;
        mainController = new MainController();
    }//end of the method i_select_the_anime

    /**
     * Assert "When I click on delete "
     *
     * @throws Throwable
     */
    @When("^I click on delete$")
    public void i_click_on_delete() throws Throwable {
        mainController.deleteAnime(indexRegistered);
    }//end of the method i_click_on_delete

    /**
     * Assert "Then I shouldn't see the anime "Bleach""
     *
     * @param arg1
     * @throws Throwable
     */
    @Then("^I shouldn't see the anime \"(.*?)\"$")
    public void i_shouldn_t_see_the_anime(String arg1) throws Throwable {
        IndexDAO indexDAO = new IndexDAO();
        assertThat(indexDAO.getIndexes().size(), is(0));
    }//end of the method i_shouldn_t_see_the_anime

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

}//end of the class StepsDeleteAnimeTest
