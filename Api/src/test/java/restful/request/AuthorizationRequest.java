package restful.request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restful.response.AuthorizationResponse;

public class AuthorizationRequest {
    static String baseURL = "https://restful-booker.herokuapp.com/auth";
    static String stickerBaseURL = "http://35.175.142.166:8080/cg-stickers/api/auth/signin";

    public static Response generateToken(AuthorizationResponse authorization) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(authorization)
                .post(baseURL)
                .thenReturn();
        return response;
    }

    public static Response generateStickerToken(AuthorizationResponse authorization) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(authorization)
                .post(stickerBaseURL)
                .thenReturn();
        return response;
    }
}
