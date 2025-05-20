package fakerest.request;

import fakerest.response.ActivitiesResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ActivitiesRequest {
    static String activitiesBaseURL = "https://fakerestapi.azurewebsites.net/api/v1/Activities/";

    public static Response getActivities() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get(activitiesBaseURL)
                .thenReturn();

        return response;
    }

    public static Response getActivities(int id) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get(activitiesBaseURL + id)
                .thenReturn();

        return response;
    }

    public static Response createActivity(ActivitiesResponse activity) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(activity)
                .log().all()
                .when()
                .post(activitiesBaseURL)
                .thenReturn();

        return response;
    }
}













