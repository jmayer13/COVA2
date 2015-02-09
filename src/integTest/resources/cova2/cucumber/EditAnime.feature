Feature: Edit animes
    Anime Bleach should exist in the system
  
    Scenario: Edit anime
       Given I have the anime "Bleach"
       When I click on edit
       And I increase the current episode in 1 
       Then I should see the anime "Bleach" on the list with my current episode
