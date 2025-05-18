Feature: Test search functionality

  Scenario Outline: Search products
    Given I am on the magento login page
    When I am login with valid credentials
    Then I search product "<product>"
    And I sorted by "price"

    Examples:
      | product |
      | Jacket  |

  Scenario Outline: Search by title
    Given I am on Mans Jackets screen
    Then I verify result contains "<product>"
    And I apply color "black" filter

    Examples:
      | product |
      | Jacket  |


  # 99.00, 84.00, 77.00 and etc.
  # List<Double> unsortedlist =                       [99.00, 84.00, 100.00 ,77.00]
  # List<Double> sorted = Collections.sort(list) =    [100.00, 99.00, 84.00,77.00]