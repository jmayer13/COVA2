Feature: Update 
    Scenario: Verify update
       Given I start COVA
       When COVA check for update
       And starts updater.jar if update avaliable and quit
       Then COVA really starts
    Scenario: Update
       Given I start COVA
       When COVA found file updater.zip 
       Then COVA unzip files updater.zip  e backup.zip
       And COVA delete file updater.zip 
       And COVA parse data of backup
    Scenario: Recover fail Update
       Given I start COVA
       When COVA founds the file updateFail.err 
       Then I delete the file updateFail.err and start COVA
