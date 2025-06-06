Feature: test currency rates

  Scenario: Currency codes should be unique
    When I send a GET request to "/supported-currencies"
    Then all currency codes should be distinct

  Scenario: Currency icons must be valid PNG image URLs
    When I send a GET request to "/supported-currencies"
    Then all icon URLs should end with ".png" and start with "https"

  Scenario: Currency name must not be null or empty
    When I send a GET request to "/supported-currencies"
    Then all currencies must have a non-empty currency name

  Scenario: Active currencies must have dynamic availableUntil date
    When I send a GET request to "/supported-currencies"
    Then currencies with status "AVAILABLE" should have availableUntil field is not empty