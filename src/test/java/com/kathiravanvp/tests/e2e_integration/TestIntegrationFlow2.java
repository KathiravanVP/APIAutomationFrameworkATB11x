package com.kathiravanvp.tests.e2e_integration;

import com.kathiravanvp.base.BaseTest;
import com.kathiravanvp.endpoints.APIConstants;
import com.kathiravanvp.pojos.response.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestIntegrationFlow2 extends BaseTest {
    //  Test E2E Scenario 2

    // 1. Create Booking
    // 2. Delete Booking
    // 3. Verify the booking is not present

    @Test(priority = 1)
    @Owner("Kathiravan")
    @Description("TC#1 - Verify that booking shall be created")
    public void testCreateBooking(ITestContext iTestContext) {
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString())
                .log().all().post();
        System.out.println(response.asString());

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        System.out.println("Booking id: " + bookingResponse.getBookingid());

        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(), "Virat");

        iTestContext.setAttribute("bookingid", bookingResponse.getBookingid());
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

    @Test(priority = 3)
    @Owner("Kathiravan")
    @Description("TC#3 - Verify the booking by id")
    public void testverifyBookingbyID(ITestContext iTestContext) {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid);
        response = RestAssured.given(requestSpecification).when().log().all().get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);
    }
}
