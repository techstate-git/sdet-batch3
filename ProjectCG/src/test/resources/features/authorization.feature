@ui
Feature: Login functionality

  Scenario: Successful login with valid credentials
    Given I login with username "admin@gmail.com" and password "admin"
    Then I should see the dashboard page