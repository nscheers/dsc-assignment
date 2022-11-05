package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebServiceImpl implements WebService{

    @Autowired
    private WebClient myWebClient;


    public Mono<Flight> getFlights() {
        return myWebClient
                .get()
                .uri("/flights")
                .retrieve()
                .bodyToMono(Flight.class);
    }
}
