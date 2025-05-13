package com.kathiravanvp.tests.crud;

import com.kathiravanvp.base.BaseTest;
import com.kathiravanvp.endpoints.APIConstants;
import com.kathiravanvp.pojos.response.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestCreateBooking extends BaseTest {

    @Test(priority = 1)
    @Owner("Kathiravan")
    @Description("TC#1 - Verify that booking shall be created")
    public void testCreateBookingPOST_Positive(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString())
                .log().all().post();
        System.out.println(response.asString());

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
        assertActions.verifyStringKey(bookingResponse.getBooking().getFirstname(),"Virat");
    }

    @Test(priority = 2)
    @Owner("Kathiravan")
    @Description("TC#2 - Verify that booking shall not be created, when payload is null")
    public void testCreateBookingPOST_Negative(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body("{}")
                .log().all().post();
        System.out.println(response.asString());

        validatableResponse = response.then().log().all();

        validatableResponse.statusCode(500);
    }

    @Test(priority = 3)
    @Owner("Kathiravan")
    @Description("TC#3 - Verify that booking shall be created using random value")
    public void testCreateBookingPOST_RandomValues(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingFakerJS())
                .log().all().post();
        System.out.println(response.asString());

        BookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());

        assertActions.verifyStringKeyNotNull(bookingResponse.getBookingid());
    }
}
