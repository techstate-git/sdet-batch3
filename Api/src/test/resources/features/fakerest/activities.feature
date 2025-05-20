Feature: Test Activities

  Scenario: Get Activities
    Given the user is get all activities

  Scenario: Get Activity by ID
    Given the user is get activity by id = 7

  Scenario: Create a new Activity
    Given the user is created activity with id = 777
    And the title is = "Activity 777"
    And the due date is = 30 days
    And activity is completed = true
    Then the user is creating activity using parameters above