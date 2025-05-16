package com.kathiravanvp.tests.e2e_integration;

import com.kathiravanvp.base.BaseTest;
import com.kathiravanvp.endpoints.APIConstants;
import com.kathiravanvp.pojos.request.Booking;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestIntegrationFlow5 extends BaseTest {
    // E2E Scenario 5

    // 1. Delete a Booking
    // 2. Try to Update it
    @Test(priority = 1)
    @Owner("Kathiravan")
    @Description("TC#1 - Verify booking shall be deleted")
    public void testDeletebooking(ITestContext iTestContext) {
        Integer bookingid = getBookingID();
        iTestContext.setAttribute("bookingid", bookingid);

        String token = getToken();
        iTestContext.setAttribute("token", token);

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid);
        response = RestAssured.given(requestSpecification).cookie("token", token).when().delete();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);
    }

    @Test(priority = 2)
    @Owner("Kathiravan")
    @Description("TC#2 - Verify booking shall be updated")
    public void testUpdatebooking(ITestContext iTestContext){
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = (String) iTestContext.getAttribute("token");

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid);
        response = RestAssured.given(requestSpecification).cookie("token",token)
                .when().body(payloadManager.fullUpdatePayloadAsString())
                .log().all().put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(405);

    }
}
