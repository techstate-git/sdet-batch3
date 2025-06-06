package restful.request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restful.response.BookingResponse;

public class BookingRequest {
    static String baseURL = "https://restful-booker.herokuapp.com/booking/";

    public static Response createBooking(BookingResponse bookingResponse) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(bookingResponse)
                .post(baseURL)
                .thenReturn();

        return response;
    }

    public static Response getBookingById(Integer id) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(baseURL + id)
                .thenReturn();

        return response;
    }

    public static Response getAllBookings() {
        Response response = RestAssured.given()
                .log().all()
                .when()
                .get(baseURL)
                .thenReturn();

        return response;
    }

    public static Response updateBooking(String token, BookingResponse bookingResponse, int bookingID) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                //token={generatedToken}
                .header("Cookie", "token=" + token)
                .log().uri()
                .when()
                .body(bookingResponse)
                .put(baseURL + bookingID)
                .thenReturn();

        return response;
    }

    public static Response createBookingWithOM(String body) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(body)
                .post(baseURL)
                .thenReturn();

        return response;
    }
}








