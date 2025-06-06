package stepDefinitions.aviasales;

import aviasales.models.FlightSearchResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static aviasales.client.GetFlights.getFlights;

public class FlightSteps {
    Response response;
    Response roundTripResponse;
    FlightSearchResponse flightSearchResponse;
    FlightSearchResponse flightSearchResponseUSD;
    FlightSearchResponse flightSearchResponseRUB;

    @Given("user is searching flight with parameters:")
    public void user_is_searching_flight_with_parameters(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> params = dataTable.asMap();
        String currency = params.get("currency");

        response = getFlights(params);
        Assert.assertEquals(200, response.getStatusCode());

        if ("usd".equalsIgnoreCase(currency)) {
            flightSearchResponseUSD = response.as(FlightSearchResponse.class);
            System.out.println("USD price is: " + flightSearchResponseUSD.getData().get(0).getPrice());
        } else if ("rub".equalsIgnoreCase(currency)) {
            flightSearchResponseRUB = response.as(FlightSearchResponse.class);
            System.out.println("RUB price is: " + flightSearchResponseRUB.getData().get(0).getPrice());
        } else {
            throw new IllegalArgumentException("unsupported currency: " + currency);
        }
    }

    @Then("response should contain at least one flight")
    public void response_should_contain_at_least_one_flight() {
        flightSearchResponse = response.as(FlightSearchResponse.class);
        Assert.assertFalse(flightSearchResponse.getData().isEmpty());
        //lambda expression
        flightSearchResponse.getData().forEach(flight ->
                System.out.println("Flight number: " + flight.getFlight_number() + ", Price: " + flight.getPrice())
        );
    }

    @Given("flights should be sorted in ascending order by price")
    public void flights_should_be_sorted_in_ascending_order_by_price() {
        List<Integer> unsortedPrices = new ArrayList<>(); // empty

        for (FlightSearchResponse.FlightData flight : flightSearchResponse.getData()) {
            unsortedPrices.add(flight.getPrice()); // [900, 983, 2000, 1200]
        }

        List<Integer> prices = new ArrayList<>(unsortedPrices); // [900, 983, 2000, 1200]
        Collections.sort(prices); // [900, 983, 1200, 2000] and [900, 983, 2000, 1200] = Fail

        Assert.assertEquals(prices, unsortedPrices);
    }

    @Then("the RUB price should be approximately ±15%")
    public void the_rub_price_should_be_approximately() {
        int usdPrice = flightSearchResponseUSD.getData().get(0).getPrice();
        int rubPrice = flightSearchResponseRUB.getData().get(0).getPrice();

        double exchangeRate = 78.97;
        double tolerance = 0.15;

        double minExpected = usdPrice * exchangeRate * (1 - tolerance); //0.85
        double maxExpected = usdPrice * exchangeRate * (1 + tolerance); //1.15

        System.out.println("USD price: " + usdPrice);
        System.out.println("RUB price: " + rubPrice);
        System.out.println("Expected RUB range: " + minExpected + " - " + maxExpected);

        Assert.assertTrue("RUB price is too low", rubPrice >= minExpected);
        Assert.assertTrue("RUB price is too high", rubPrice <= maxExpected);
    }

    @Given("user is searching round trip flight with new parameters:")
    public void user_is_searching_round_trip_flight_with_new_parameters(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> params = dataTable.asMap();
        roundTripResponse = getFlights(params);
        Assert.assertEquals(200, roundTripResponse.getStatusCode());
    }
    @Given("user is searching one way outbound flight with new parameters:")
    public void user_is_searching_one_way_outbound_flight_with_new_parameters(io.cucumber.datatable.DataTable dataTable) {

    }
    @Given("user is searching one way return flight with new parameters:")
    public void user_is_searching_one_way_return_flight_with_new_parameters(io.cucumber.datatable.DataTable dataTable) {

    }
    @Then("round-trip flight price should be less than sum of two one-way prices")
    public void round_trip_flight_price_should_be_less_than_sum_of_two_one_way_prices() {

    }


}
















