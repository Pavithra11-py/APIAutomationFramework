package org.apipractice.base;
import org.apipractice.Asserts.AssertActions;
import org.apipractice.endpoints.APIconstants;
import org.apipractice.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    // common to all testcases like  base url, content type - json
    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;
    public Response response;
    public ValidatableResponse validatableResponse;

    @BeforeTest
    public void setup(){
        payloadManager  = new PayloadManager();
        assertActions = new AssertActions();

//        requestSpecification = RestAssured.given().baseUri(APIconstants.Base_URL)
//                .contentType(ContentType.JSON).log().all();
// or
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIconstants.Base_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();



    }

}
