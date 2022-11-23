package be.kuleuven.distributedsystems.cloud.controller;

import be.kuleuven.distributedsystems.cloud.BookingManager;
import be.kuleuven.distributedsystems.cloud.WebService;
import be.kuleuven.distributedsystems.cloud.entities.Booking;
import be.kuleuven.distributedsystems.cloud.entities.Flight;
import be.kuleuven.distributedsystems.cloud.entities.Ticket;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("api")
public class BookingController {
    @Autowired
    private WebService webService;
    private BookingManager bookingManager = new BookingManager();

    private static String apiKey = "Iw8zeveVyaPNWonPNaU0213uw3g6Ei";



    @GetMapping("getBookings")
    public Booking[] getBookings(String jwt){
        return (Booking[]) bookingManager.getBookings().stream().filter(booking -> booking.getCustomer().equals(jwt)).toArray();
    }

    @GetMapping("getAllBookings")
    @PreAuthorize("hasAuthority('manager')")
    public Booking[] getAllBookings(){
        return (Booking[]) bookingManager.getBookings().toArray(new Booking[0]);
    }

    @GetMapping("getBestCustomers")
    @PreAuthorize("hasAuthority('manager')")
    public Flight[] getBestCustomers(){
        //Dit moet later maar gebeuren met firestore man

        bookingManager.getBookings().stream();
        return null;
    }

    @PostMapping("confirmQuotes")
    public void confirmQuotes(List<Ticket> tickets, String jwt){
        Booking booking = new Booking(UUID.randomUUID(), LocalDateTime.now(), tickets, jwt);
        bookingManager.addBooking(booking);
    }
}
