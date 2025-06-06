package com.cgstikers.api.clients;

import com.cgstikers.api.models.Token;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TokenRequest {
    static String baseURL = "http://35.175.142.166:8080/cg-stickers/api/auth/signin";

    public static Response generateToken(Token token) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(token)
                .post(baseURL)
                .thenReturn();
    }
}
