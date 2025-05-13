package com.kathiravanvp.tests.crud;

import com.kathiravanvp.base.BaseTest;
import com.kathiravanvp.endpoints.APIConstants;
import com.kathiravanvp.modules.PayloadManager;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestCreateToken extends BaseTest {

    @Test(priority = 1)
    @Owner("Kathiravan")
    @Description("TC#2 - Verify that token shall be created")
    public void testCreateToken(){
        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.setAuthPayload())
                .log().all().post();
        System.out.println(response.asString());

        String token = payloadManager.getTokenFromJson(response.asString());

        assertActions.verifyStringKeyNotNull(token);
    }
}
