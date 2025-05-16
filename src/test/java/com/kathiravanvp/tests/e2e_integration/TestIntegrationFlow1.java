package com.kathiravanvp.tests.e2e_integration;

import com.kathiravanvp.base.BaseTest;
import com.kathiravanvp.endpoints.APIConstants;
import com.kathiravanvp.pojos.request.Booking;
import com.kathiravanvp.pojos.response.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class TestIntegrationFlow1 extends BaseTest {
    //  Test E2E Scenario 1

    // 1. Create a Booking -> bookingID
    // 2. Create Token -> token
    // 3. Verify that the Create Booking is working - GET Request to bookingID
    // 4. Update the booking ( bookingID, Token) - Need to get the token, bookingID from above request
    // 5. Delete the Booking - Need to get the token, bookingID from above request

    @Test(priority = 1)
    @Owner("Kathiravan")
    @Description("TC#1 - Verify that booking shall be created")
    public void testCreateBooking(ITestContext iTestContext){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString())
                .log().all().post();
        System.out.println(response.asString());

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        System.out.println("Booking id: "+bookingResponse.getBookingid());

        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Virat");

        iTestContext.setAttribute("bookingid",bookingResponse.getBookingid());
    }

    @Test(priority = 2)
    @Owner("Kathiravan")
    @Description("TC#2 - Verify that token shall be created")
    public void testCreateToken(ITestContext iTestContext){
        String token = getToken();
        iTestContext.setAttribute("token",token);
    }

    @Test(priority = 3)
    @Owner("Kathiravan")
    @Description("TC#3 - Verify the booking by id")
    public void testverifyBookingbyID(ITestContext iTestContext){
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid);
        response = RestAssured.given(requestSpecification).when().log().all().get();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertActions.verifyStringKey(booking.getFirstname(),"Virat");
    }

    @Test(priority = 4)
    @Owner("Kathiravan")
    @Description("TC#4 - Verify booking shall be updated")
    public void testUpdatebooking(ITestContext iTestContext){
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = (String) iTestContext.getAttribute("token");

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid);
        response = RestAssured.given(requestSpecification).cookie("token",token)
                .when().body(payloadManager.fullUpdatePayloadAsString())
                .log().all().put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());

        assertActions.verifyStringKeyNotNull(booking.getFirstname());
        assertActions.verifyStringKey(booking.getFirstname(),"Rohit");
    }

    @Test(priority = 5)
    @Owner("Kathiravan")
    @Description("TC#5 - Verify booking shall be deleted")
    public void testDeletebooking(ITestContext iTestContext){
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String token = (String) iTestContext.getAttribute("token");

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingid);
        response = RestAssured.given(requestSpecification).cookie("token",token).when().delete();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);
    }


}
