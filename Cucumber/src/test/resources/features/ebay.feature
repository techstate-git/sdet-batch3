Feature: Ebay.com testing
  @ui
  Scenario: Search for a product
    Given I am on the ebay homepage
    When I search for "smartphone"
#    Then I should see search results containing "smartphone"

  Scenario: Filter search results by price range
    Given I am on the ebay homepage
    When I search for "laptop"
    And I apply a price filter from "300" to "500"
    Then I should see search results within the price range

  @ui
  Scenario: Filter search by condition of the product
    Given I am on the ebay homepage
    When I search for "laptop"











