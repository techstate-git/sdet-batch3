@api
Feature: Create a new booking
  As a QA Engineer
  I want to send a POST request to create a booking
  So that I can verify a new booking is successfully created

  Scenario: Successfully create a new booking with valid details
    Given the request body contains the following booking details:
      | firstname       | BK         |
      | lastname        | Sultan     |
      | totalprice      | 111        |
      | depositpaid     | true       |
      | checkin         | 2018-01-01 |
      | checkout        | 2019-01-01 |
      | additionalneeds | Breakfast  |
    When I send a POST request to create a booking
    Then the response status code should be 200
    And the response body should contain a bookingid field
    And the user is get booking details by ID
    And the user is verified firstname "BK" and lastname "Sultan"

  Scenario: Create a new booking with ObjectMapper
    Given the request body with ObjectMapper contains the following details
      | firstname       | BK         |
      | lastname        | Sultan     |
      | totalprice      | 111        |
      | depositpaid     | true       |
      | checkin         | 2018-01-01 |
      | checkout        | 2019-01-01 |
      | additionalneeds | Breakfast  |
    When I send a POST request to create a booking with OM
    Then I validate the response
