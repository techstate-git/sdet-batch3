Feature: Update a existing booking

  Background: Generate Authorization Token
    Given the user generated one time token

  Scenario: Update the existing Booking ID
    Given the user is get first booking from list
    When the user is set a new booking details
      | firstname       | BK         |
      | lastname        | Sultan     |
      | totalprice      | 450        |
      | depositpaid     | true       |
      | checkin         | 2025-05-26 |
      | checkout        | 2025-06-01 |
      | additionalneeds | Breakfast  |
    Then the user is updating the existing booking using PUT call

  #Scenario: Generate token for Sticker website
  #  Given the user generated sticker one time token