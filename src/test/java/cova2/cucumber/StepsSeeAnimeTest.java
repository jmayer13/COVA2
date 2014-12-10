package cova2.cucumber;

import cova2.controller.MainController;
import cova2.dao.AnimeDAO;
import cova2.dao.IndexDAO;
import cova2.model.anime.Anime;
import cova2.model.index.Index;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.sql.SQLException;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class StepsSeeAnimeTest {

    private MainController mainController;

    @Given("^I have the anime \"(.*?)\" registered, in the episode (\\d+)$")
    public void i_have_the_anime_registered_in_the_episode(String title, int currentEpisode) throws Throwable, SQLException, ClassNotFoundException {
        IndexDAO indexDAO = new IndexDAO();
        Index index = new Index(title, indexDAO.getRecommendedCodeAnime());
        index = indexDAO.addIndex(index);
        Anime anime = new Anime();
        anime.setCodeAnime(index.getCodeAnime());
        anime.setCurrentEpisode(currentEpisode);
        AnimeDAO animeDAO = new AnimeDAO();
        animeDAO.createAnime(anime);
    }

    @When("^I open the main window$")
    public void i_open_the_main_window() throws Throwable {
        mainController = new MainController();
        assertTrue(mainController.isViewOpen());
    }

    @Then("^I should see the anime \"(.*?)\"$")
    public void i_should_see_the_anime(String animeName) throws Throwable {
        Index index = mainController.getIndexRow(0);
        assertThat(index.getMainTitleAnime(), is(animeName));
    }

    @Then("^the current episode (\\d+) that I wached$")
    public void the_current_episode_that_I_wached(int currentEpisode) throws Throwable {
        Anime anime = mainController.getAnimeRow(0);
        assertThat(anime.getCurrentEpisode(), is(currentEpisode));
        cleanData();
    }

    @After
    public void cleanData() throws SQLException, ClassNotFoundException {
        IndexDAO indexDAO = new IndexDAO();
        indexDAO.deleteIndex(mainController.getIndexRow(0));
        AnimeDAO animeDAO = new AnimeDAO();
        animeDAO.deleteAnime(mainController.getIndexRow(0).getCodeAnime());
    }
}
