package com.kathiravanvp.base;

import com.kathiravanvp.asserts.AssertActions;
import com.kathiravanvp.endpoints.APIConstants;
import com.kathiravanvp.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class BaseTest {
    public RequestSpecification requestSpecification;
    public Response response;
    public ValidatableResponse validatableResponse;
    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public JsonPath jsonPath;

    @BeforeTest
    public void setup() {
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();
    }

    public String getToken(){
        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.setAuthPayload())
                .log().all().post();
        System.out.println(response.asString());

        String token = payloadManager.getTokenFromJson(response.asString());

        assertActions.verifyStringKeyNotNull(token);
        return token;
    }

    public Integer getBookingID(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification).when().log().all().get();
        List<Integer> bookingIDs = response.jsonPath().getList("bookingid");
        Integer bookingid = bookingIDs.get(3);
        return bookingid;
    }

    @AfterTest
    public void tearDown(){
        System.out.println("Closing the test!!!");
    }
}
