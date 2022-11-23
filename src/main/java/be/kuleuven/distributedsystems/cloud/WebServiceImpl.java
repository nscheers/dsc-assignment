package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.Flight;

import be.kuleuven.distributedsystems.cloud.entities.Seat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class WebServiceImpl implements WebService{

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static final ObjectMapper mapper = new ObjectMapper();
    private final static String key = "Iw8zeveVyaPNWonPNaU0213uw3g6Ei";

    List<WebClient> airlines;

    public WebServiceImpl(WebClient.Builder webClientBuilder){
        this.webClientBuilder = webClientBuilder;
        airlines = new ArrayList<WebClient>();
        airlines.add(this.webClientBuilder.baseUrl("https://reliable-airline.com").build());
        airlines.add(this.webClientBuilder.baseUrl("https://unreliable-airline.com").build());

    }


    public Flight[] getFlights() {
        Flight[] allFlights = new Flight[0];

        Iterator itr = airlines.iterator();
        while (itr.hasNext())
        {
            WebClient x = (WebClient)itr.next();
            var result =  x
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/flights")
                            .queryParam("key", key)
                            .build())
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<CollectionModel<Flight>>() {}).log()
                    .block()
                    .getContent();
            Flight[] flights = result.toArray(new Flight[result.size()]);
            Flight[] both = Arrays.copyOf(allFlights, allFlights.length + flights.length);
            System.arraycopy(flights, 0, both, allFlights.length, flights.length);
            allFlights = both;
        }

        return allFlights;
    }


    public Flight getFlight(String name, String flightId) {
        Mono<Flight> response = webClientBuilder.baseUrl("https://" + name).build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/flights/"  + flightId)
                        .queryParam("key", key)
                        .build())
                .retrieve()
                .bodyToMono(Flight.class).log();
        return response.block();
    }

    public String[] getFlightTimes(String name, String flightId) {
        var times = webClientBuilder.baseUrl("https://" + name).build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/flights/"  + flightId + "/times" )
                        .queryParam("key", key)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CollectionModel<String>>() {}).log()
                .block()
                .getContent();

        return times.toArray(new String[times.size()]);
    }

    public Seat[] getAvailableSeats(String name, String flightId, String time) {
        var seats = webClientBuilder.baseUrl("https://" + name).build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/flights/"  + flightId + "/seats")
                        .queryParam("time", time)
                        .queryParam("available", true)
                        .queryParam("key", key)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CollectionModel<Seat>>() {}).log()
                .block()
                .getContent();
        return seats.toArray(new Seat[seats.size()]);
    }

    public Seat getSeat(String name, String flightId, String seatId) {
        return webClientBuilder.baseUrl("https://" + name).build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/flights/"  + flightId + "/seats/" + seatId)
                        .queryParam("key", key)
                        .build())
                .retrieve()
                .bodyToMono(Seat.class).log().block();
    }
}
