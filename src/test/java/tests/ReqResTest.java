package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.RestUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ReqResTest {

    @Test
    public void getSingleUserTest() {
        Response res = RestUtils.get("/users/2");

        System.out.println("Response: " + res.asString());

        Assert.assertEquals(res.getStatusCode(), 200);

        }
    @Test
    public void checkUserEmailAndFirstName() {
        given()
                .baseUri("https://reqres.in")
                .basePath("/api/users/2")
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"));
    }

}
