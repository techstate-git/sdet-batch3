Feature: Test saucedemo.com

  Scenario Outline: Test Login functionality
    Given I am on the login page
    When I enter "<usernames>"
    And I enter password
    Then I successfully logged in

    Examples:
      | usernames               |
      | standard_user           |
      | locked_out_user         |
      | problem_user            |
      | performance_glitch_user |
      | error_user              |
      | visual_user             |


    # option + command + L -> Refactor your code