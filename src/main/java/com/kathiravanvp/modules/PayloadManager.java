package com.kathiravanvp.modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.kathiravanvp.pojos.request.Auth;
import com.kathiravanvp.pojos.request.Booking;
import com.kathiravanvp.pojos.request.Bookingdates;
import com.kathiravanvp.pojos.response.BookingResponse;
import com.kathiravanvp.pojos.response.TokenResponse;

public class PayloadManager {
    Gson gson;

    public String createPayloadBookingAsString(){
        Booking booking = new Booking();
        booking.setFirstname("Virat");
        booking.setLastname("Kohli");
        booking.setTotalprice(5000);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2025-06-01");
        bookingdates.setCheckout("2025-06-30");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;
    }

    public BookingResponse bookingResponseJava(String responseString){
        gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }

    public String setAuthPayload(){
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson = new Gson();
        String jsonStringAuth = gson.toJson(auth);
        return jsonStringAuth;
    }

    public String getTokenFromJson(String tokenResponse){
        gson = new Gson();
        TokenResponse tokenResponse1 = gson.fromJson(tokenResponse, TokenResponse.class);
        return tokenResponse1.getToken();
    }

    public String createPayloadBookingFakerJS(){
        Faker faker = new Faker();
        Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setLastname(faker.name().lastName());
        booking.setTotalprice(faker.random().nextInt(1000,5000));
        booking.setDepositpaid(faker.random().nextBoolean());

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2025-06-01");
        bookingdates.setCheckout("2025-06-30");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Lunch");

        gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        return jsonStringBooking;
    }

    public Booking getResponseFromJSON(String getResponse){
        gson = new Gson();
        Booking booking = gson.fromJson(getResponse, Booking.class);
        return booking;
    }

    public String fullUpdatePayloadAsString() {
        Booking booking = new Booking();
        booking.setFirstname("Rohit");
        booking.setLastname("Sharma");
        booking.setTotalprice(1000);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-03-01");
        bookingdates.setCheckout("2024-03-05");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Lunch");
        return gson.toJson(booking);
    }

    public String partialUpdatePayloadAsString() {
        Booking booking = new Booking();
        booking.setFirstname("Ravi");
        booking.setLastname("Ashwin");

        return gson.toJson(booking);
    }
}
