package org.apipractice.tests.integration;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.apipractice.base.BaseTest;
import org.apipractice.endpoints.APIconstants;
import org.apipractice.modules.PayloadManager;
import org.apipractice.pojos.Booking;
import org.apipractice.pojos.BookingResponse;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestE2E_01  extends BaseTest {

    //  Test E2E Scenario 1

    //  1. Create a Booking -> bookingID

    // 2. Create Token -> token

    // 3. Verify that the Create Booking is working - GET Request to bookingID

    // 4. Update the booking ( bookingID, Token) - Need to get the token, bookingID from above request

    // 5. Delete the Booking - Need to get the token, bookingID from above request


    // Create A Booking, Create a Token
    // Verify that Get booking -
    // Update the Booking
    // Delete the Booking

    @Test(groups = "qa", priority = 1)
    @Owner("Pavithra")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking(ITestContext itestcontext){
        //preparation
        requestSpecification.basePath(APIconstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when()
                .body(payloadManager.createpayloadBookingasString())
                .post();
        //making of request
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        BookingResponse bookingResponse = PayloadManager.bookingresponsejava(response.asString());
        // validation
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Jim");
        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
     // to get value of booking id we can't use return
        // so use ITestContext interface

        itestcontext.setAttribute("bookingid",bookingResponse.getBookingid());


    }

    @Test(groups = "qa", priority = 2)
    @Owner("Pavithra")
    @Description("TC#INT1 - Step 2. Verify that the Booking By ID")
    public void testVerifyBookingId(ITestContext itestcontext){
        Integer bookingid = (Integer) itestcontext.getAttribute("bookingid");


        // GET Request - to verify that the firstname after creation is James
        String basePathGET = APIconstants.CREATE_UPDATE_BOOKING_URL+"/" + bookingid;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);
        response = RestAssured
                .given(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("Praveen");


    }

    @Test(groups = "qa", priority = 3)
    @Owner("Pavithra")
    @Description("TC#INT1 - Step 3. Verify Updated Booking by ID")
    public void testUpdateBookingByID(ITestContext itestcontext){
        Integer bookingid = (Integer) itestcontext.getAttribute("bookingid");
        String token = getToken();
        itestcontext.setAttribute("token",token);

        String basePathPUTPATCH = APIconstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathPUTPATCH);

        requestSpecification.basePath(basePathPUTPATCH);

        response = RestAssured
                .given(requestSpecification).cookie("token", token)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();


        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo("Lucky");
        assertThat(booking.getLastname()).isEqualTo("Dutta");

    }

    @Test(groups = "qa", priority = 4)
    @Owner("Pavithra")
    @Description("TC#INT1 - Step 4. Delete the Booking by ID")
    public void testDeleteBookingById(ITestContext itestcontext){

            String token = (String)itestcontext.getAttribute("token");
            Integer bookingid = (Integer) itestcontext.getAttribute("bookingid");

            String basePathDELETE = APIconstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;

            requestSpecification.basePath(basePathDELETE).cookie("token", token);
            validatableResponse = RestAssured.given().spec(requestSpecification)
                    .when().delete().then().log().all();
            validatableResponse.statusCode(201);


        }


    }
