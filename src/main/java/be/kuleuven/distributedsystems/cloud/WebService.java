package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.Flight;
import reactor.core.publisher.Mono;

import java.lang.reflect.Array;
import java.util.List;

public interface WebService {
    public Flight[] getFlights();
    public Flight getFlight(String name, String flightId);
}
