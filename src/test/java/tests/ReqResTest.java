package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import payloads.PayloadUtils;
import utils.RestUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ReqResTest extends BaseTest {
    @Test
    public void testListUsers() {
        Response res = given()
                .queryParam("page", 2)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().response();

        String email = res.jsonPath().getString("data[0].email");
        Assert.assertTrue(email.contains("@reqres.in"), "Email does not contain @reqres.in");
        Assert.assertEquals(res.getStatusCode(), 200);
    }
    @Test
    public void testCreateUser() {
        String requestBody = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        Response res = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201) // Assert status code
                .extract().response();

        String id = res.jsonPath().getString("id");
        String name = res.jsonPath().getString("name");
        String job = res.jsonPath().getString("job");

        Assert.assertNotNull(id, "User ID should not be null");
        Assert.assertEquals(name, "morpheus");
        Assert.assertEquals(job, "leader");
        Assert.assertEquals(res.getStatusCode(), 201);
    }
    @Test
    public void testSuccessfulLogin() {
        String payload = PayloadUtils.getLoginPayload("eve.holt@reqres.in", "cityslicka");

        Response res = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract().response();

        String token = res.jsonPath().getString("token");
        Assert.assertNotNull(token, "Token should not be null");
        Assert.assertEquals(res.getStatusCode(), 200);
    }

}