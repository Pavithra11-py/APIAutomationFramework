package org.apipractice.tests.crud;

import org.apipractice.base.BaseTest;
import org.apipractice.endpoints.APIconstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.apipractice.base.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.requestSpecification;

public class HealthCheckTest extends BaseTest {

        @Test(groups = "reg", priority = 1)
        @TmsLink("https://bugz.atlassian.net/browse/TS-1")
        @Owner("Pavithra")
        @Description("TC#3  - Verify Health")
        public void testGETHealthCheck() {
            requestSpecification.basePath(APIconstants.PING_URL);
            response = RestAssured.given(requestSpecification)
                    .when()
                    .get();
            validatableResponse = response.then().log().all();
            validatableResponse.statusCode(201);
        }
    }
