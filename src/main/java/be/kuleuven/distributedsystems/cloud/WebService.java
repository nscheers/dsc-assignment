package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.Flight;
import reactor.core.publisher.Mono;

public interface WebService {
    public Mono<Flight> getFlights();
}
