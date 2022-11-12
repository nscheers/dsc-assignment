package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.Flight;

import be.kuleuven.distributedsystems.cloud.entities.Seat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class WebServiceImpl implements WebService{

    @Autowired
    private WebClient myWebClient;

    private static final ObjectMapper mapper = new ObjectMapper();
    private final static String key = "Iw8zeveVyaPNWonPNaU0213uw3g6Ei";


    public Flight[] getFlights() {
        var flights =  myWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/flights")
                        .queryParam("key", key)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CollectionModel<Flight>>() {}).log()
                .block()
                .getContent();

        return flights.toArray(new Flight[flights.size()]);
    }


    public Flight getFlight(String name, String flightId) {
        Mono<Flight> response = myWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/flights/"  + flightId)
                        .queryParam("key", key)
                        .build())
                .retrieve()
                .bodyToMono(Flight.class).log();
        return response.block();
    }

    @Override
    public Time[] getFlightTimes(String name, String flightId) {
        var times = myWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/flights/"  + flightId + "/seats" )
                        .queryParam("key", key)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CollectionModel<Time>>() {}).log()
                .block()
                .getContent();
        return times.toArray(new Time[times.size()]);
    }

    @Override
    public Seat[] getAvailableSeats(String name, String flightId, Time time) {
        var seats = myWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/flights/"  + flightId + "/seats" )
                        .queryParam("key", key)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CollectionModel<Seat>>() {}).log()
                .block()
                .getContent();
        return seats.toArray(new Seat[seats.size()]);
    }

    @Override
    public Seat getSeat(String name, String flightId, String seatId) {
        return myWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/flights/"  + flightId + "/seats/" + seatId)
                        .queryParam("key", key)
                        .build())
                .retrieve()
                .bodyToMono(Seat.class).log().block();
    }
}
