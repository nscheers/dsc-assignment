package be.kuleuven.distributedsystems.cloud.controller;

import be.kuleuven.distributedsystems.cloud.WebService;
import be.kuleuven.distributedsystems.cloud.entities.Flight;
import be.kuleuven.distributedsystems.cloud.entities.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Time;
import java.util.Collections;



@RestController
@RequestMapping("api")
public class FlightController {
    @Autowired
    private WebService webService;

    private static String apiKey = "Iw8zeveVyaPNWonPNaU0213uw3g6Ei";

    @GetMapping("getFlights")
    public Flight[] getFlights(){
        return webService.getFlights();
    }

    @GetMapping("getFlight")
    public Flight getFlights(String name, String flightId){
        return webService.getFlight(name, flightId);
    }

    @GetMapping("getFlightTimes")
    public String[] getFlightTimes(String name, String flightId){
        return webService.getFlightTimes(name, flightId);
    }

    @GetMapping("getAvailableSeats")
    public Seat[] getAvailableSeats(String name, String flightId, String time){
        return webService.getAvailableSeats(name, flightId, time);
    }

    @GetMapping("getSeat")
    public Seat getSeat(String name, String flightId, String seatId){
        return webService.getSeat(name, flightId, seatId);
    }
}
