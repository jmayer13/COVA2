Feature: Add animes
    Anime should not exist in the system
  
    Scenario: Add anime
       Given I start to watch the anime "Bleach"
       When I click on add
       And I insert the title and current episode
       And I click ok
       Then I should see the anime "Bleach" on the list
