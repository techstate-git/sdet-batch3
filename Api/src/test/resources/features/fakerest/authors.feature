@api
Feature: Test authors

  Scenario: Get Authors
    Given the user is get all authors
    Then status code is 200

  Scenario: Get Authors by Book id
    Given the user is get authors by book id 4
    Then status code is 200
    Then print names of all authors