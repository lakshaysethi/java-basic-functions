
/* *
 * this file is for referensing backend functions only;
 * * */

package test;

import javax.swing.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Functions {

    public static ArrayList<Slot> slotsList;
    public static ArrayList<User> users = new ArrayList<User>();

    public static void main(String[] args) {
        String licenceNumber = "123";
        User u1= createNewUser(licenceNumber);
        users.add(u1);
        String day = "2020/04/11";
        int hour = 900;
        String startDateString = "2020/04/11 09:00";
        ArrayList<Slot> slotsList = openShop(startDateString);

        if(slot_available(day,hour)!=null)
        {
            bookTimeSlot(u1,day,hour);
        }

        else {
            showNotBookedMessage();
        }

        printAllBookings(users);

    }

    private static void printAllBookings(ArrayList<User> users) {
        System.out.println("List of all bookings");
        for(int i=0;i<users.size();i++){
            System.out.println("for "+users.get(i).licenceNumber);
            for(int j=0;j<users.get(i).bookingsList.size();j++){
                System.out.println("      "+users.get(i).bookingsList.get(j).refNo);
                System.out.println("      "+users.get(i).bookingsList.get(j).slot.toString());
            }
        }

    }

    private static void showNotBookedMessage() {
        System.out.println("Not Booked");
    }

    private static ArrayList<Slot> openShop(String startDateString) {

        slotsList = new ArrayList<Slot>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date startDate = null;
        try {
            startDate = sdf.parse(startDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date dateForSlot = startDate;

        for (int i=0;i<40;i++){

            slotsList.add(new Slot(dateForSlot));
            // long millis = date.getTime();

            Calendar cal = Calendar.getInstance(); // creates calendar
            cal.setTime(dateForSlot); // sets calendar time/date
            cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
            dateForSlot=cal.getTime();


        }



        return slotsList;
    }

    private static void bookTimeSlot(User u1, String day, int hour) {
        Slot selectedSlot = slot_available(day, hour);
        Booking newBooking = new Booking(selectedSlot);
        //CHECK LATER - need to chk if this user has already booked this slot then he can not book again
        u1.bookingsList.add(newBooking);



    }
    private static Slot slot_available(String day, int hour) {
        String hourString = Integer.toString(hour);

        if(hour<959){
            hourString = "0"+hourString;
            hourString = hourString.substring(0, 2) + ":" + hourString.substring(2, hourString.length());
            System.out.println(hourString);

        }else{
            hourString = hourString.substring(0, 2) + ":" + hourString.substring(2, hourString.length());
        }


        String correctString = day + " "+ hourString;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date dateToCheck =  null;
        try {
            dateToCheck = sdf.parse(correctString);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        for (int i=0;i<slotsList.size();i++){
            Slot testSlot = slotsList.get(i);
            if(testSlot.date.equals(dateToCheck)){
                if(testSlot.remainingTimes>=1){
                    testSlot.remainingTimes--;
                    return testSlot;
                }
            }
        }

        return null;
    }

    private static User createNewUser(String licenceNumber) {
        return new User(licenceNumber);
    }


    public static class Slot{
        public int remainingTimes;
        public Date date;

        @Override
        public String toString() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            String dateString = sdf.format(date);
            return "date and time: " + dateString;
        }

        public Slot(Date date) {
            this.date = date;
            this.remainingTimes = 10;

        }

    }
    public static class User{
        private String licenceNumber;
        private ArrayList<Booking> bookingsList;

        public User(String licenceNumber) {
            this.licenceNumber = licenceNumber;
            this.bookingsList = new ArrayList<Booking>();

        }

        public String getLicenceNumber() {
            return licenceNumber;
        }

        public void setLicenceNumber(String licenceNumber) {
            this.licenceNumber = licenceNumber;
        }

        public ArrayList<Booking> getBookingsList() {
            return bookingsList;
        }

        public void setBookingsList(ArrayList<Booking> bookingsList) {
            this.bookingsList = bookingsList;
        }
    }
    public static class Booking{
        public Slot slot;
        public String refNo;

        public Booking(Slot slot) {
            this.slot = slot;
            this.refNo = "#" + UUID.randomUUID().toString() ;//+ " "+ this.slot.toString();
        }
    }
}
