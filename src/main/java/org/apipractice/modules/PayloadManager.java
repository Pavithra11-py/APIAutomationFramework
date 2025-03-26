package org.apipractice.modules;

import com.google.gson.Gson;
import org.apipractice.pojos.*;

public class PayloadManager {

    Gson gson = new Gson();

    public String createpayloadBookingasString() {

        Booking booking = new Booking();
        booking.setFirstname("Praveen");
        booking.setLastname("Kumar");
        booking.setDepositpaid(true);

        Bookingdates bookindates = new Bookingdates();
        bookindates.setCheckin("2018-01-01");
        bookindates.setCheckout("2019-01-01");
        booking.setBookingdates(bookindates);
        booking.setAdditionalneeds("Brunch");

        //Now this is JAVA object (we can't transfer this to server directly)
        //  so convert java Object to JSON using gson

        Gson gson = new Gson();
        String jsonSTring = gson.toJson(booking);

        System.out.println(jsonSTring);
        return jsonSTring;
    }

    public static BookingResponse bookingresponsejava(String responseString) {
        Gson gson = new Gson();
        BookingResponse bookingresponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingresponse;
    }

    public String Auth_payload() {
        AUth auth = new AUth();
        auth.setUsername("admin");
        auth.setPassword("password123");

        gson = new Gson();
        String jsonpayloadstring = gson.toJson(auth);
        System.out.println("Payload set to ---> " + jsonpayloadstring);
        return jsonpayloadstring;
    }

    // JSON to Java
    public static String getTokenJson(String tokenResponse) {
        Gson gson = new Gson();
        TokenResponse tokenResponse1 = gson.fromJson(tokenResponse, TokenResponse.class);
        return tokenResponse1.getToken().toString();
    }


    public Booking getResponseFromJSON(String getResponse) {
        gson = new Gson();
        Booking booking = gson.fromJson(getResponse, Booking.class);
        return booking;
    }

    public String fullUpdatePayloadAsString() {
        Booking booking = new Booking();
        booking.setFirstname("Lucky");
        booking.setLastname("Dutta");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-05");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        return gson.toJson(booking);

    }
}