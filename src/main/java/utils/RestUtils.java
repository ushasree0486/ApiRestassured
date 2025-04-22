package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/*
public class RestUtils {
    public static String baseURI = "https://opensource-demo.orangehrmlive.com/api";

    public static RequestSpecification getRequest() {
        return RestAssured.given()
                .baseUri(baseURI)
                .header("Content-Type", "application/json");
    }

    public static Response post(String endpoint, String payload) {
        return getRequest().body(payload).post(endpoint);
    }

    public static Response get(String endpoint, String token) {
        return getRequest().header("Authorization", "Bearer " + token).get(endpoint);
    }
}
*/
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestUtils {
    public static Response post(String endpoint, String payload) {
        return RestAssured.given()
                .baseUri("https://reqres.in/")
                .basePath("api")
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post(endpoint);
    }

    public static Response get(String endpoint) {
        return RestAssured.given()
                .baseUri("https://reqres.in/")
                .basePath("api")
                .header("Content-Type", "application/json")
                .when()
                .get(endpoint);
    }
}
