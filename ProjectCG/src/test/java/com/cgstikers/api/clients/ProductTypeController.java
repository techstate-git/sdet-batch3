package com.cgstikers.api.clients;

import com.cgstikers.api.models.Products;
import com.cgstikers.common.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductTypeController {
    private static final String BASE_URL = "http://35.175.142.166:8080/cg-stickers";

    public static Response getAllProducts(String accessToken) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + ConfigManager.get("access.token"))
                .log().all()
                .when()
                .get(BASE_URL+ "/api/products")
                .thenReturn();
    }

    public static Response createProduct(String accessToken, Products product) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + ConfigManager.get("access.token"))
                .log().all()
                .when()
                .body(product)
                .post(BASE_URL + "api/products")
                .thenReturn();
    }

    public static Response updateProduct(String accessToken, int id, Products products) {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken)
                .log().all()
                .body(products)
                .put(BASE_URL + "/api/products/" + id)
                .thenReturn();
    }
}
