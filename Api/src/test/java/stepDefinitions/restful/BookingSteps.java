package stepDefinitions.restful;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import restful.response.BookingResponse;

import java.util.Map;

import static restful.request.BookingRequest.*;

public class BookingSteps {
    Response response;
    BookingResponse bookingResponse = new BookingResponse();
    BookingResponse.BookingDates bookingDates = new BookingResponse.BookingDates();

    static Integer createdBookingId;
    static String jsonString;

    @Given("the request body contains the following booking details:")
    public void the_request_body_contains_the_following_booking_details(Map<String, String> bookingDetails) {
        bookingDates.setCheckin(bookingDetails.get("checkin"));
        bookingDates.setCheckout(bookingDetails.get("checkout"));

        bookingResponse.setFirstname(bookingDetails.get("firstname"));
        bookingResponse.setLastname(bookingDetails.get("lastname"));
        bookingResponse.setTotalprice(Integer.parseInt(bookingDetails.get("totalprice")));
        bookingResponse.setDepositpaid(Boolean.parseBoolean(bookingDetails.get("depositpaid")));
        bookingResponse.setBookingdates(bookingDates);
        bookingResponse.setAdditionalneeds(bookingDetails.get("additionalneeds"));
    }
    @When("I send a POST request to create a booking")
    public void i_send_a_post_request_to_create_a_booking() {
        response = createBooking(bookingResponse);
        response.prettyPrint();
    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }
    @Then("the response body should contain a bookingid field")
    public void the_response_body_should_contain_a_bookingid_field() {
        BookingResponse bookingResponse = response.as(BookingResponse.class);
        createdBookingId = bookingResponse.getBookingid();
        Assert.assertNotNull("Booking ID is null", createdBookingId);
        System.out.println("Booking ID from Response " + createdBookingId);
        System.out.println("Firstname from Booking " + bookingResponse.getBooking().getFirstname());
        System.out.println("Chceckin date from Booking " + bookingResponse.getBooking().getBookingdates().getCheckin());
    }
    @Then("the user is get booking details by ID")
    public void the_user_is_get_booking_details_by_id() {
        response = getBookingById(createdBookingId);
        response.prettyPrint();
    }
    @Then("the user is verified firstname {string} and lastname {string}")
    public void the_user_is_verified_firstname_and_lastname(String firstName, String lastName) {
        BookingResponse bookingResponse = response.as(BookingResponse.class);
        Assert.assertEquals(firstName, bookingResponse.getFirstname());
        Assert.assertEquals(lastName, bookingResponse.getLastname());
    }
    @Given("the request body with ObjectMapper contains the following details")
    public void the_request_body_with_object_mapper_contains_the_following_details(Map<String, String> bookingDetails) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode rootNode = objectMapper.createObjectNode();

            //create a root node
            rootNode.put("firstname", bookingDetails.get("firstname"));
            rootNode.put("lastname", bookingDetails.get("lastname"));
            rootNode.put("totalprice", bookingDetails.get("totalprice"));
            rootNode.put("depositpaid", bookingDetails.get("depositpaid"));

            //create a bookingdates object
            ObjectNode bookingDatesNode = objectMapper.createObjectNode();
            bookingDatesNode.put("checkin", bookingDetails.get("checkin"));
            bookingDatesNode.put("checkout", bookingDetails.get("checkout"));

            rootNode.set("bookingdates", bookingDatesNode);

            rootNode.put("additionalneeds", bookingDetails.get("additionalneeds"));

            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("I send a POST request to create a booking with OM")
    public void i_send_a_post_request_to_create_a_booking_with_om() {
        response = createBookingWithOM(jsonString);
        response.prettyPrint();
    }

    @Then("I validate the response")
    public void i_validate_the_response() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            //Get the response as String
            String responseBody = response.getBody().asString();

            //Parse the JSON into a ObjectNode(JsonNode)
            JsonNode rootNode = objectMapper.readTree(responseBody);
            int bookindId = rootNode.get("bookingid").asInt();

            //Extract the booking details node
            JsonNode bookingNode = rootNode.get("booking");
            String firstName = bookingNode.get("firstname").asText();
            String lastname = bookingNode.get("lastname").asText();
            boolean depositpaid = bookingNode.get("depositpaid").asBoolean();

            //Extract bookingdates
            JsonNode bookingDatesNode = bookingNode.get("bookingdates");
            String checkin = bookingDatesNode.get("checkin").asText();
            String checkout = bookingDatesNode.get("checkout").asText();

            System.out.println(bookindId);
            System.out.println(firstName);
            System.out.println(lastname);
            System.out.println(depositpaid);
            System.out.println(checkin);
            System.out.println(checkout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}









