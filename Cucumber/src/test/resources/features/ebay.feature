Feature: Ebay.com testing

  Scenario: Search for a product
    Given I am on the ebay homepage
    When I search for "smartphone"
    Then I should see search results containing "smartphone"

  Scenario: Filter search results by price range
    Given I am on the ebay homepage
    When I search for "laptop"
    And I apply a price filter from "300" to "500"
    Then I should see search results within the price range

  Scenario: Filter search by condition of the product
    Given I am on the ebay homepage
    When I search for "laptop"
    And I apply filter by condition New and Item location US Only
    Then I should see all products is "Brand New" and "from United States"











