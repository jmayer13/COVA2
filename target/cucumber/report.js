$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("cova2/cucumber/SeeAnime.feature");
formatter.feature({
  "line": 1,
  "name": "See animes",
  "description": " Animes should be displayed with respective episodes\r\n Animes shoud be alphabetically ordered",
  "id": "see-animes",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 5,
  "name": "See anime",
  "description": "",
  "id": "see-animes;see-anime",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 6,
  "name": "I have One Piece registered, in the episode 600",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "I open the main window",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "I should see One Piece",
  "keyword": "Then "
});
formatter.step({
  "line": 9,
  "name": "the current episode 600 that I am watching",
  "keyword": "And "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({
  "arguments": [
    {
      "val": "600",
      "offset": 20
    }
  ],
  "location": "StepsSeeAnimeTest.I_should_see_my_animes(int)"
});
formatter.result({
  "status": "skipped"
});
});