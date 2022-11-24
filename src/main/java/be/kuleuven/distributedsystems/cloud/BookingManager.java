package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.Booking;
import be.kuleuven.distributedsystems.cloud.entities.Quote;
import be.kuleuven.distributedsystems.cloud.entities.Ticket;
import be.kuleuven.distributedsystems.cloud.entities.User;
import com.google.api.core.ApiFunction;
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.v1.FirestoreClient;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class BookingManager {
    List<User> customers;
    List<Booking> bookings;



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
        getFirestore = FirestoreOptions.newBuilder()
                .setChannelProvider(InstantiatingGrpcChannelProvider.newBuilder()
                        .setEndpoint("localhost:8084")
                        .setChannelConfigurator(
                                ManagedChannelBuilder::usePlaintext)
                        .build())
                .setCredentials(new FirestoreOptions.EmulatorCredentials())
                .build().getService();
    }
    public Booking createBooking(Quote[] quotes){
        UUID uuid = UUID.randomUUID();
        List<Ticket> tickets = new ArrayList<>();
        tickets.addAll(Arrays.stream(quotes).map(q -> new Ticket(q.getAirline(), q.getFlightId(), q.getSeatId(), UUID.randomUUID(), "test", "Testref")).toList());
        Booking booking = new Booking(uuid, tickets,"test");
        addBooking(booking);
        System.out.printf("added booking");
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

    public void addBooking(Booking booking) {
        getFirestore.collection("bookings").document(booking.getId().toString()).set(booking);
    }
}

