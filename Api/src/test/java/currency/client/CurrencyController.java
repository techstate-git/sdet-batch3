package currency.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CurrencyController {
    public static String BASE_URL = "https://api.currencyfreaks.com/v2.0";

    public static Response getCurrency(String endpoint) {
        return RestAssured
                .given()
                .log().all()
                .when()
                .get(BASE_URL + endpoint)
                .thenReturn();
    }
}
