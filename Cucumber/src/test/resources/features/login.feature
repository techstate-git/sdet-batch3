Feature: Login functionality test
  Background:
    Given I am on the magento login page
    When I am login with valid credentials

  Scenario: Successfully login
    Then I am logged in as "Beksultan"

  Scenario: My orders is empty verification
    Then I am verify there is no previous orders

