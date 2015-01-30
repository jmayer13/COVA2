Feature: Delete animes
    Anime Bleach should exist in the system
  
    Scenario: Delete anime
       Given I select the anime "Bleach"
       When I click on delete 
       Then I shouldn't see the anime "Bleach"
