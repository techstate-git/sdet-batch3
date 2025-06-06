package fakerest.request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthorsRequest {
    static String authorsBasURL = "https://fakerestapi.azurewebsites.net/api/v1/Authors/";
    static String bookId ="/authors/books/";

    public static Response getAuthors() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get(authorsBasURL)
                .thenReturn();

        return response;
    }

    public static Response getAuthors(int id) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get(authorsBasURL + bookId + id)
                .thenReturn();

        return response;
    }
}














