package org.apipractice.tests.crud;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.apipractice.base.BaseTest;
import org.apipractice.endpoints.APIconstants;
import org.apipractice.modules.PayloadManager;
import org.apipractice.pojos.BookingResponse;
import org.apipractice.pojos.TokenResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.requestSpecification;

public class TestCreateToken extends BaseTest {

    private String username;
    private String password;
     @Test(groups = "reg" , priority = 1)
    @TmsLink("https://bugz.atlassian.net/browse/TS-1")

    @Owner("Pavithra")
    @Description("TC#1 - Create Token and Verify")
    public void TestToken() {
        //preparation
        requestSpecification.basePath(APIconstants.AUTH_URL);
        response = RestAssured.given(requestSpecification).when()
                .body(payloadManager.Auth_payload())
                .post();
        //making of request
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        String token = PayloadManager.getTokenJson(response.asString());
        assertActions.verifyStringKeyNotNull(token);

    }
}
