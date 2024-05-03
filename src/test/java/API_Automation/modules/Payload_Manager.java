package API_Automation.modules;

import API_Automation.payloads.Auth;
import API_Automation.payloads.Booking;
import API_Automation.payloads.Booking_dates;
import com.google.gson.Gson;

public class Payload_Manager {

    static Gson gson;
     public static String createPayload(){
         Booking booking = new Booking();
         booking.setFirstname("Prasad");
         booking.setLastname("Narala");
         booking.setTotalprice(200);
         booking.setDepositpaid(true);
        Booking_dates bookingDates = new Booking_dates();
        bookingDates.setCheckin("01-05-2024");
        bookingDates.setCheckout("03-05-2024");
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Breakfast and Lunch");

         gson = new Gson();
         String bookingString = gson.toJson(booking);
         return bookingString;
     }

     public String createToken(){
         Auth  auth = new Auth();
         auth.setUsername("admin");
         auth.setPassword("password123");
         gson = new Gson();
         String authpayload = gson.toJson(auth);
         return authpayload;

     }
     public String updatePayload() {
         Booking updateBooking = new Booking();
         updateBooking.setFirstname("Prasad");
         updateBooking.setLastname("ne");
         updateBooking.setTotalprice(112);
         updateBooking.setDepositpaid(true);

         Booking_dates bookingDates = new Booking_dates();
         bookingDates.setCheckin("05-05-2024");
         bookingDates.setCheckout("07-05-2024");
         updateBooking.setBookingdates(bookingDates);
         updateBooking.setAdditionalneeds("Breakfast, Lunch and Dinner");

         Gson gson1 = new Gson();
         String updateBookingContent = gson1.toJson(updateBooking);
         return updateBookingContent;

     }

}
