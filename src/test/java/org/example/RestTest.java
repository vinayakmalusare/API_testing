package org.example;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class RestTest extends config {
    private static final String BASE_URL = "https://reqres.in";
    @Test
    public void testGetUserName() {
        given().when()
                .queryParam("page", "2")
                .get("/api/users/")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("data[1].id", equalTo(8));
    }

    @Test
    public void testDelete(){
    given()
            .when()
                    .delete("/api/users/2")
            .then()
            .statusCode(204);
}

    @Test
    public void testUpdatePartial(){
        given()
                .when()
                .body("{\n" +
                        "    \"name\": \"Vinayak\",\n" +
                        "    \"job\": \"QA\"\n" +
                        "}").contentType(ContentType.JSON)

                .patch( "/api/users/2")
                .then()
                .statusCode(200)
                .body("name", equalTo("Vinayak"))
                .body("job", equalTo("QA"))
                .body("updatedAt",notNullValue());

       }

    @Test
    public void testPostUser(){
    given()
            .when()
            .body("{\n" +
            "    \"name\": \"Vinayak\",\n" +
                    "    \"job\": \"QA\"\n" +
                    "}").contentType(ContentType.JSON)

                    .post("/api/users/2")
            .then()

            .statusCode(201)
            .body("name", equalTo("Vinayak"))
            .body("job", equalTo("QA"))
            .body("id", notNullValue())
                .body("createdAt",notNullValue());
    }
// to read json File
    @Test
    public void jsonUser() {
            String responseBody = given ().when ()
                    .queryParam ("page", "2")
                    .get ( "/api/users/")
                    .getBody().asString();
            System.out.print(responseBody);
        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        JSONObject dataObject = jsonArray.getJSONObject(0);
        String firstName = dataObject.get("first_name").toString();
        System.out.println(firstName);
        }

        @Test

    public void newBranchTest(){

        }
    }
