package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.Flight;
import reactor.core.publisher.Mono;

import java.util.List;

public interface WebService {
    public List<Flight> getFlights();
    public Flight getFlight(String name, String flightId);
}
