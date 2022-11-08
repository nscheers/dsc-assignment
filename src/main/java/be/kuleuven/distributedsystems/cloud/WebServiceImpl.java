package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.Flight;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class WebServiceImpl implements WebService{

    @Autowired
    private WebClient myWebClient;

    private static final ObjectMapper mapper = new ObjectMapper();
    private final static String key = "Iw8zeveVyaPNWonPNaU0213uw3g6Ei";


    public List<Flight> getFlights() {
        Mono<List<Flight>> response = myWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/flights")
                        .queryParam("key", key)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Flight>>() {}).log();
        return response.block();

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
}
