 Feature: See animes
    Animes should be displayed with respective episodes
    Animes shoud be alphabetically ordered
  
    Scenario: See anime
       Given I have 1 anime registered
       When I open the main window
       Then I should see my animes
       And the current episodies that I am watching
