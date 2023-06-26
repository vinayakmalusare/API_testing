package org.example;

import data.CreateBookingData;
import org.testng.annotations.Test;

import static data.CreateBookingDataBuilder.getBookingData;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class RestfulBookerTest {
private int bookingid;
    @Test
    public void testCreateBooking(){

        CreateBookingData bookingData = getBookingData();
        bookingid = given()
                .when()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .log()
                .all()
                .body(bookingData)
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("booking.firstname", equalTo(bookingData.getFirstname())
                ,"booking.lastname",equalTo(bookingData.getLastname()))
                .extract().path("bookingid");
                System.out.print(bookingid);
    }

    @Test
    public void getBooking(){

        System.out.print(bookingid);
        given()
                .when()
                .log()
                .all()
                .get("https://restful-booker.herokuapp.com/booking/"+bookingid)
                .then()
                .log()
                .all();


    }
}
