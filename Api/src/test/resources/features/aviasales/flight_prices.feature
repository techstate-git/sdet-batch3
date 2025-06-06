Feature: Flight Price search API

#  Scenario: Validate successful response for one-way search
#    Given user is searching flight with parameters:
#      | origin       | IST     |
#      | destination  | ORD     |
#      | departure_at | 2025-07 |
#      | return_at    | 2025-08 |
#      | sorting      | price   |
#      | direct       | true    |
#      | currency     | usd     |
#    And response should contain at least one flight
#    And flights should be sorted in ascending order by price
#    And flight origin "IST" and destination "ORD" cities are matched

  Scenario: Currency exchange conversion rate verification
    Given user is searching flight with parameters:
      | origin       | IST     |
      | destination  | ORD     |
      | departure_at | 2025-07 |
      | return_at    | 2025-08 |
      | sorting      | price   |
      | direct       | true    |
      | currency     | usd     |
      | limit        | 1       |
    And user is searching flight with parameters:
      | origin       | IST     |
      | destination  | ORD     |
      | departure_at | 2025-07 |
      | return_at    | 2025-08 |
      | sorting      | price   |
      | direct       | true    |
      | currency     | rub     |
      | limit        | 1       |
    Then the RUB price should be approximately ±15%

  Scenario: Round-trip flights should be cheaper than two one-way flights combined
    Given user is searching round trip flight with new parameters:
      | origin       | IST     |
      | destination  | ORD     |
      | departure_at | 2025-07 |
      | return_at    | 2025-08 |
      | sorting      | price   |
      | direct       | true    |
      | currency     | usd     |
      | limit        | 1       |
    ## Price a 1000
    And user is searching one way outbound flight with new parameters:
      | origin       | IST     |
      | destination  | ORD     |
      | departure_at | 2025-07 |
      | sorting      | price   |
      | direct       | true    |
      | currency     | usd     |
      | limit        | 1       |
    ## Price b 700
    And user is searching one way return flight with new parameters:
      | origin       | ORD     |
      | destination  | IST     |
      | departure_at | 2025-07 |
      | sorting      | price   |
      | direct       | true    |
      | currency     | usd     |
      | limit        | 1       |
    ## Price c 600
    Then round-trip flight price should be less than sum of two one-way prices
    ## a < (b + c)









