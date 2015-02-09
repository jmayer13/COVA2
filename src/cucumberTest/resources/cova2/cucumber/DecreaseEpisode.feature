Feature: Decrease Episode
    Anime should exist in the system
  
    Scenario: Decrease Episode
       Given I get the anime "Bleach" with current episode 2
       When I click on - 
       Then I should see the anime one episode lower