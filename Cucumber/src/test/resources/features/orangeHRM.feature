Feature: Orange HRM

  Scenario: Login Functionality
    Given I am on orangeHRM homePage
    And I enter login and password
    Then I verify successfully logged in
    When I navigate to "My Info" page
    Then I change the profile picture


