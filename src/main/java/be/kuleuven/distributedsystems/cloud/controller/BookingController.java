package be.kuleuven.distributedsystems.cloud.controller;

import be.kuleuven.distributedsystems.cloud.BookingManager;
import be.kuleuven.distributedsystems.cloud.WebService;
import be.kuleuven.distributedsystems.cloud.WebServiceImpl;
import be.kuleuven.distributedsystems.cloud.entities.*;
import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
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
/*
    @Resource(name = "db")
    private Firestore db;

    @PostConstruct
    void constructor(){
        this.db.collection("tickets").get();
    }
*/
    @GetMapping("getBookings")
    public Booking[] getBookings(){
        //Todo: Firestore logica
        List<Booking> bookings = bookingManager.getBookings().stream().filter(booking -> booking.getCustomer().equals("test")).toList();
        return bookings.toArray(Booking[]::new);
    }

    @GetMapping("getAllBookings")
    @PreAuthorize("hasAuthority('manager')")
    public Booking[] getAllBookings(){
        //Todo: Firestore logica
        return (Booking[]) bookingManager.getBookings().toArray(new Booking[0]);
    }

    @GetMapping("getBestCustomers")
    @PreAuthorize("hasAuthority('manager')")
    public Flight[] getBestCustomers(){
        //Todo: Firestore logica

        bookingManager.getBookings().stream();
        return null;
    }

    @PostMapping("confirmQuotes")
    public void confirmQuotes(@RequestBody Quote[] quotes){
        //ToDo: Get user from request
        //Todo: Firestore logica

        String bookingReference = bookingManager.createBooking(quotes).getId().toString();
        for (Quote quote: quotes) {
            webService.putSeat(quote.getAirline(), quote.getFlightId().toString(), quote.getSeatId().toString(), "test", bookingReference);
        }
    }
}
