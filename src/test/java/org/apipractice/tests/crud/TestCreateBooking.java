package org.apipractice.tests.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.apipractice.base.BaseTest;
import org.apipractice.endpoints.APIconstants;
import org.apipractice.modules.PayloadManager;
import org.apipractice.pojos.BookingResponse;
import org.testng.Assert;
import org.testng.annotations.Test;



public class TestCreateBooking extends BaseTest{

    // Create A Booking, Create a Token
    // Verify that Get booking -
    // Update the Booking
    // Delete the Booking

    @Test(groups = "reg", priority = 1)

    @TmsLink("https://bugz.atlassian.net/browse/TS-1")

    @Owner("Pavithra")
    @Description("TC#INT1 - Step 1. Verify that the Booking can be Created")
    public void testCreateBooking() {
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

    }
}