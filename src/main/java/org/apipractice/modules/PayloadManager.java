package org.apipractice.modules;

import com.google.gson.Gson;
import org.apipractice.pojos.Booking;
import org.apipractice.pojos.BookingResponse;
import org.apipractice.pojos.Bookingdates;

public class PayloadManager {

    Gson gson = new Gson();

    public String createpayloadBookingasString() {

        Booking booking = new Booking();
        booking.setFirstname("Jim");
        booking.setLastname("Brown");
        booking.setDepositpaid(true);

        Bookingdates bookindates = new Bookingdates();
        bookindates.setCheckin("2018-01-01");
        bookindates.setCheckout("2019-01-01");
        booking.setBookingdates(bookindates);
        booking.setAdditionalneeds("Breakfast");

        //Now this is JAVA object (we can't transfer this to server directly)
        //  so convert java Object to JSON using gson

        Gson gson = new Gson();
        String jsonSTring = gson.toJson(booking);

        System.out.println(jsonSTring);
        return jsonSTring;
    }
        public static BookingResponse bookingresponsejava(String responseString){
            Gson gson = new Gson();
            BookingResponse bookingresponse = gson.fromJson(responseString, BookingResponse.class);
            return bookingresponse;
        }

    }
