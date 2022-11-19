package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.Booking;
import be.kuleuven.distributedsystems.cloud.entities.User;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    List<User> customers;
    List<Booking> bookings;


    public BookingManager() {
        this.customers = new ArrayList<User>();
        this.bookings = new ArrayList<Booking>();
    }

    public List<User> getCustomers() {
        return customers;
    }

    public void setCustomers(List<User> customers) {
        this.customers = customers;
    }

    public void addCustomer(User user){
        this.customers.add(user);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBooking(Booking booking){
        this.bookings.add(booking);
    }
}

