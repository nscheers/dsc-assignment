package be.kuleuven.distributedsystems.cloud.controller;

import be.kuleuven.distributedsystems.cloud.WebService;
import be.kuleuven.distributedsystems.cloud.entities.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;



@RestController
@RequestMapping("api")
public class FlightController {
    @Autowired
    private WebService webService;

    private static String apiKey = "Iw8zeveVyaPNWonPNaU0213uw3g6Ei";

    @GetMapping("getFlights")
    public void getFlights(){
        System.out.println(webService.getFlights().toString());

    }

    @GetMapping("getFlight")
    public Flight getFlights(String name, String flightId){
        return webService.getFlight(name, flightId);
    }
}
