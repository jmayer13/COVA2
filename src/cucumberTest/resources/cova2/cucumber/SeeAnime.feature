 Feature: See animes
    Anime should not exist in the be displayed with respective episodes
    Animes shoud be alphabetically ordered
  
    Scenario: See anime
       Given I have the anime "One Piece" registered, in the episode 600
       When I open the main window
       Then I should see the anime "One Piece"
       And the current episode 600 that I wached
