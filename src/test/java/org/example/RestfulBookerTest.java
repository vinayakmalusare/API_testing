package org.example;
import data.CreateBookingData;
import data.PartialUpdate;
import data.TokenData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static data.CreateBookingDataBuilder.getBookingData;
import static data.PartialUpdateDataBuilder.getPartialData;
import static data.TokenBuilder.getToken;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

public class RestfulBookerTest {
    private CreateBookingData bookingData;
private int bookingid;
private String token;
@BeforeClass
    public void setUp(){
    bookingData= getBookingData();
    }
    @Test
    public void testCreateBooking(){

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

    }

    @Test
    public void getBooking(){

        given()
                .when()
                .log()
                .all()
                .get("https://restful-booker.herokuapp.com/booking/"+bookingid)
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("firstname",equalTo(bookingData.getFirstname()))
                .body("lastname",equalTo(bookingData.getLastname()))
                .body("totalprice",equalTo(bookingData.getTotalprice()))
                .body("bookingdates.checkin",equalTo(bookingData.getBookingdates().getCheckin()));

    }

    @Test
    public void testUpdateBooking(){
        CreateBookingData updateBooking=getBookingData();
        given()
                .when()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie","token="+token)
                .log()
                .all()
                .body(updateBooking)
                .put("https://restful-booker.herokuapp.com/booking/"+bookingid)
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("firstname", equalTo(updateBooking.getFirstname())
                        ,"lastname",equalTo(updateBooking.getLastname()));


    }

    @Test
    public void testGenerateToken(){

        TokenData tokendata=getToken();
        token=given()
                .when()
                .log()
                .all()
                .header("Content-Type","application/json")
                .body(tokendata)
                .post("https://restful-booker.herokuapp.com/auth")
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("token",is(notNullValue())).extract().path("token");

    }

    @Test
    public void testPartialUpdate(){
        PartialUpdate partialdata = getPartialData();
        given()
                .when()
                .header("Content-Type","application/json")
                .header("Accept","application/json")
                .header("Cookie","token="+token)
                .log()
                .all()
                .body(partialdata)
                .patch("https://restful-booker.herokuapp.com/booking/"+bookingid)
                .then()
                .log()
                .all()
                .statusCode(200)
                .body("firstname", equalTo(partialdata.getFirstname())
                        ,"lastname",equalTo(partialdata.getLastname()));
    }

    @Test

    public void testDelete(){

        given()
                .when()
                .header("Content-Type","application/json")
                .header("Cookie","token="+token)
                .log()
                .all()
                .delete("https://restful-booker.herokuapp.com/booking/"+bookingid)
                .then()
                .log()
                .all()
                .statusCode(201);
    }

    @Test
    public void testDeletedBooking(){

        given()
                .when()
                .log()
                .all()
                .get("https://restful-booker.herokuapp.com/booking/"+bookingid)
                .then()
                .log()
                .all()
                .statusCode(404);


    }

}
