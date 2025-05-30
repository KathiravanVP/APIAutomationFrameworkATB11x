package com.kathiravanvp.tests.sample;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIntegrationSample {
    // Create A Booking, Create a Token
    // Verify that Get booking
    // Update the Booking
    // Delete the Booking

    @Test(priority = 1)
    @Owner("Kathiravan")
    @Description("TC#1 - Step 1 - Verify that the Booking can be Created")
    public void testCreateBooking(){
        Assert.assertTrue(true);
    }

    @Test(priority = 2)
    @Owner("Kathiravan")
    @Description("TC#INT1 - Step 2 - Verify that Booking By ID")
    public void testVerifyBookingId(){
        Assert.assertTrue(true);
    }

    @Test(priority = 3)
    @Owner("Kathiravan")
    @Description("TC#INT1 - Step 3 - Verify Updated Booking by ID")
    public void testUpdateBookingByID(){
        Assert.assertTrue(true);
    }

    @Test(priority = 4)
    @Owner("Kathiravan")
    @Description("TC#INT1 - Step 4 - Delete the Booking by ID")
    public void testDeleteBookingById(){
        Assert.assertTrue(true);
    }
}
