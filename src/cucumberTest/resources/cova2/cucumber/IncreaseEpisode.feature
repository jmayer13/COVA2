Feature: Increase Episode
    Anime should exist in the system
  
    Scenario: Increase Episode
       Given I select the anime "Bleach" with current episode 1
       When I click on + 
       Then I should see the anime one episode bigger
