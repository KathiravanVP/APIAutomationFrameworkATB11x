package com.kathiravanvp.asserts;

import io.restassured.response.Response;
import org.testng.Assert;
import static org.assertj.core.api.Assertions.*;

public class AssertActions {

    public void verifyResponseBody(String actual,String expected,String description){
        Assert.assertEquals(actual,expected,description);
    }

    public void verifyResponseBody(int actual,int expected,String description){
        Assert.assertEquals(actual,expected,description);
    }

    public void verifyStatusCode(Response response, Integer expected){
        Assert.assertEquals(response.statusCode(),expected);
    }

    public void verifyStringKey(String keyExp, String keyAct){
        assertThat(keyExp).isNotBlank();
        assertThat(keyExp).isNotNull();
        assertThat(keyExp).isEqualTo(keyAct);
    }

    public void verifyStringKeyNotNull(Integer keyExpect){
        assertThat(keyExpect).isNotNull();
    }
    public void verifyStringKeyNotNull(String keyExpect){
        assertThat(keyExpect).isNotNull();
    }
}
