package aviasales.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

public class GetFlights {
    static String BASE_URL = "https://api.travelpayouts.com/aviasales/v3/prices_for_dates";

    public static Response getFlights(Map<String, String> queryParams) {
        return RestAssured
                .given()
                .queryParams(queryParams)
                .queryParam("token", "5b80061b101731827cd0fec6aa164e79")
                .log().all()
                .when()
                .get(BASE_URL)
                .thenReturn();
    }
}
