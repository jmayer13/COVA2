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
import cova2.dao.IndexDAO;
import cova2.exception.UnavailableDataException;
import cova2.model.index.Index;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test to feature Add Anime
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class StepsAddAnimeTest {

    private String titleAnime;
    private MainController mainController;
    private AddAnimeController addAnimeController;
    private Index indexRegisterd;

    /**
     * Inicialize controller
     */
    @Before
    public void initialize() {
        mainController = new MainController();
    }//end of the method initialize

    /**
     * Assert "Given I start to watch the anime "Bleach""
     *
     * @param title
     * @throws Throwable
     */
    @Given("^I start to watch the anime \"(.*?)\"$")
    public void i_start_to_watch_the_anime(String title) throws Throwable {
        titleAnime = title;
    }//end of the method i_start_to_watch_the_anime

    /**
     * Assert "When I click on add"
     *
     * @throws Throwable
     */
    @When("^I click on add$")
    public void i_click_on_add() throws Throwable {
        addAnimeController = mainController.openAddAnimeController();
    }//end of the method i_click_on_add

    /**
     * Assert "And I insert the title and current episode"
     *
     * @throws Throwable
     */
    @When("^I insert the title and current episode$")
    public void i_insert_the_title_and_current_episode() throws Throwable {
        addAnimeController.getView().setTitleAnime(titleAnime);
        addAnimeController.getView().setCurrentEpisode(1);
    }//end of the method i_insert_the_title_and_current_episode

    /**
     * Assert "And I click ok"
     */
    @When("^I click ok$")
    public void i_click_ok() {
        addAnimeController.okAction();
    }//end of the method i_click_ok

    /**
     * Assert "Then I should see the anime "Bleach" on the list"
     *
     * @param arg1
     * @throws Throwable
     */
    @Then("^I should see the anime \"(.*?)\" on the list$")
    public void i_should_see_the_anime_on_the_list(String arg1) throws Throwable {
        int sizeIndexes = mainController.loadIndexes().size();
        Index index = null;
        for (int i = 0; i < sizeIndexes; i++) {
            if (mainController.getIndexRow(i).getMainTitleAnime().equals(titleAnime)) {
                index = mainController.getIndexRow(i);
                indexRegisterd = index;
            }
        }
        assertThat(index.getMainTitleAnime(), is(titleAnime));
    }//end of the method i_should_see_the_anime_on_the_list

    /**
     * Clean data of test
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @After
    public void cleanData() throws SQLException, ClassNotFoundException, UnavailableDataException {
        IndexDAO indexDAO = new IndexDAO();
        List<Index> indexes = indexDAO.getIndexes();
        for (Index index : indexes) {
            indexDAO.eraseIndex(index);
        }
        indexDAO.closeConnection();
        File dirDB = new File("data" + File.separator + "db");
        for (File file : dirDB.listFiles()) {
            file.delete();
        }
        dirDB.delete();
        File dirJSON = new File("data" + File.separator + "anime");
        if (dirJSON.exists()) {
            for (File file : dirJSON.listFiles()) {
                file.delete();
            }
        }
        dirJSON.delete();
    }//end of the method cleanData
}//end of the class StepsAddAnimeTest
