package org.example;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class RestTest {
    private static final String BASE_URL = "https://reqres.in";
    @Test
    public void testGetUserName() {
    given().when()
            .queryParam("page", "2")
                    .get(BASE_URL+"/api/users/")
            .then()
            .log()
            .all()
            .statusCode(200)
            .body("page", equalTo(2))
            .body("data[1].id", equalTo(8))
            .log()
            .all();
}

    @Test
    public void testDelete(){
    given()
            .when()
                    .delete(BASE_URL +"/api/users/2")
            .then()
            .log()
            .all()
            .statusCode(204);
}

    @Test
    public void testUpdatePartial(){
    given()
            .when()
            .log()
            .all()
            .body("{\n" +
            "    \"name\": \"Vinayak\",\n" +
                    "    \"job\": \"QA\"\n" +
                    "}").contentType(ContentType.JSON);
            when()
                    .patch(BASE_URL+ "/api/users/2")
            .then()
            .log()
            .all()
            .statusCode(200)
                    .body("name", equalTo("Vinayak"))
                    .body("job", equalTo("QA"));

       }

    @Test
    public void testPostUser(){
    given()
            .when()
            .body("{\n" +
            "    \"name\": \"Vinayak\",\n" +
                    "    \"job\": \"QA\"\n" +
                    "}").contentType(ContentType.JSON)
            .log()
            .all()
                    .post(BASE_URL +"/api/users/2")
            .then()
            .log()
            .all()
            .statusCode(201)
            .body("name", equalTo("Vinayak"))
            .body("job", equalTo("QA"))
            .body("id", notNullValue())
                .body("createdAt",notNullValue());
    }

    }
