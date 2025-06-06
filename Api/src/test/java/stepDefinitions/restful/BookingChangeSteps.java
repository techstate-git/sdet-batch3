package stepDefinitions.restful;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import restful.response.AuthorizationResponse;
import restful.response.BookingResponse;

import java.util.List;
import java.util.Map;

import static restful.request.AuthorizationRequest.generateStickerToken;
import static restful.request.AuthorizationRequest.generateToken;
import static restful.request.BookingRequest.*;

public class BookingChangeSteps {
    AuthorizationResponse authorizationResponse = new AuthorizationResponse();
    BookingResponse bookingResponse = new BookingResponse();
    BookingResponse.BookingDates bookingDates = new BookingResponse.BookingDates();
    Response response;

    private String token;
    private int firstBookingID;

    @Given("the user generated one time token")
    public void the_user_generated_one_time_token() {
        authorizationResponse.setUsername("admin");
        authorizationResponse.setPassword("password123");

        response = generateToken(authorizationResponse);
        authorizationResponse = response.as(AuthorizationResponse.class);
        token = authorizationResponse.getToken(); //some already assigned
        System.out.println("TEST: " + token);
    }

    @Given("the user is get first booking from list")
    public void the_user_is_get_first_booking_from_list() {
        response = getAllBookings();
        List<BookingResponse> bookingResponses = response.as(new TypeRef<>() {});

        bookingResponse = bookingResponses.get(0);
        firstBookingID = bookingResponse.getBookingid();

        System.out.println("Response before updating: ");
        getBookingById(firstBookingID).prettyPrint();
    }

    @When("the user is set a new booking details")
    public void the_user_is_set_a_new_booking_details(Map<String, String> bookingDetails) {
        bookingDates.setCheckin(bookingDetails.get("checkin"));
        bookingDates.setCheckout(bookingDetails.get("checkout"));

        bookingResponse.setFirstname(bookingDetails.get("firstname"));
        bookingResponse.setLastname(bookingDetails.get("lastname"));
        bookingResponse.setTotalprice(Integer.parseInt(bookingDetails.get("totalprice")));
        bookingResponse.setDepositpaid(Boolean.parseBoolean(bookingDetails.get("depositpaid")));
        bookingResponse.setBookingdates(bookingDates);
        bookingResponse.setAdditionalneeds(bookingDetails.get("additionalneeds"));
    }

    @Then("the user is updating the existing booking using PUT call")
    public void the_user_is_updating_the_existing_booking_using_put_call() {
        System.out.println("Response after updating: ");
        updateBooking(token, bookingResponse, firstBookingID).prettyPrint();
    }

}
