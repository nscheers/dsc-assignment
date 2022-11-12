package be.kuleuven.distributedsystems.cloud.controller;

import be.kuleuven.distributedsystems.cloud.WebService;
import be.kuleuven.distributedsystems.cloud.entities.Flight;
import be.kuleuven.distributedsystems.cloud.entities.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Time;
import java.util.Collections;



@RestController
@RequestMapping("api")
public class BookingController {
    @Autowired
    private WebService webService;

    private static String apiKey = "Iw8zeveVyaPNWonPNaU0213uw3g6Ei";

    @GetMapping("getBookings")
    public Flight[] getBookings(){
        return null;
    }

    @GetMapping("getAllBookings")
    public Flight[] getAllBookings(){
        return null;
    }

    @GetMapping("getBestCustomers")
    public Flight[] getBestCustomers(){
        return null;
    }

    @PostMapping("confirmQuotes")
    public Flight[] confirmQuotes(){
        return null;
    }
}
