@ui @integration
Feature: Product test functionality

  Scenario: Create a product via UI and verify in API
    Given I login with username "admin@gmail.com" and password "admin"
    When I create a product with name "Square sticker"
    And I generated new authorization token
    Then the product "Square sticker" should be created in the API

  Scenario: Create a product via API and verify in UI
    And I generated new authorization token
    Then I create product "Cartoon sticker" from the API
    Given I login with username "admin@gmail.com" and password "admin"
    When I verify a product with name "Cartoon sticker" is created in UI

  Scenario: Create stickers in bulk via API
    Given I generated new authorization token
    Then I create 10 Bulk products

  Scenario: Update all Bulk stickers via API
    Given I generated new authorization token
    Then I updated bulk sticker with name containing "B-Bulk"

  Scenario: Deactivate stickers from the list
    Given I generated new authorization token
    Then I deactivate stickers
      | Square sticker         |

  Scenario: Delete all products from UI
    Given I login with username "admin@gmail.com" and password "admin"
    When I navigated to Product page
    Then I delete all products

  Scenario: Create 100 Bulk stickers from API with generated name and description
    Given I login with username "admin@gmail.com" and password "admin"
