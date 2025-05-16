package com.kathiravanvp.tests.e2e_integration;

import com.kathiravanvp.base.BaseTest;
import com.kathiravanvp.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestIntegrationFlow3 extends BaseTest {
    // E2E Scenario 3

    // 1. Get a Booking from Get All
    // 2. Try to Delete that booking

    @Test(priority = 1)
    @Owner("Kathiravan")
    @Description("TC#1 - Verify all booking present")
    public void testCreateBooking(ITestContext iTestContext){
        requestSpecification.basePath("/booking");
        response = RestAssured.given(requestSpecification).when().log().all().get();
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        Integer bookingid = getBookingID();

        iTestContext.setAttribute("bookingid",bookingid);
    }

    @Test(priority = 2)
    @Owner("Kathiravan")
    @Description("TC#2 - Verify booking shall be deleted")
    public void testDeletebooking(ITestContext iTestContext) {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = getToken();
        iTestContext.setAttribute("token", token);

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid);
        response = RestAssured.given(requestSpecification).cookie("token", token).when().delete();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);
    }
}
