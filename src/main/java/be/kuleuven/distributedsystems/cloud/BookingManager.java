package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.Booking;
import be.kuleuven.distributedsystems.cloud.entities.Quote;
import be.kuleuven.distributedsystems.cloud.entities.Ticket;
import be.kuleuven.distributedsystems.cloud.entities.User;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.v1.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingManager {
    List<User> customers;
    List<Booking> bookings;


    @Autowired
    private Firestore getFirestore;



    /*
    @PostConstruct
    void constructor(){
        this.db.collection("tickets").get();
    }

     */




    public BookingManager()  {
        this.customers = new ArrayList<User>();
        this.bookings = new ArrayList<Booking>();
    }
    public Booking createBooking(Quote[] quotes){
        UUID uuid = UUID.randomUUID();
        List<Ticket> tickets = new ArrayList<>();
        for (Quote quote: quotes) {
            tickets.add(new Ticket(quote.getAirline(), quote.getFlightId(), quote.getSeatId(), UUID.randomUUID(), "test", uuid.toString()));
        }
        Booking booking = new Booking(uuid, LocalDateTime.now(), tickets,"test");
        addBooking(booking);
        return booking;
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
        List<Booking> bookingList = new ArrayList<>();
        try {
            List<QueryDocumentSnapshot> documents = getFirestore.collection("bookings").get().get().getDocuments();
            for (DocumentSnapshot document : documents) {
                bookingList.add(document.toObject(Booking.class));
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return  bookingList;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBooking(Booking booking){
        getFirestore.collection("bookings").add(booking);
        this.bookings.add(booking);
    }
}

