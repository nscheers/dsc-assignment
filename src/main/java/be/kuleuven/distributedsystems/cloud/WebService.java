package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.Flight;
import be.kuleuven.distributedsystems.cloud.entities.Seat;
import reactor.core.publisher.Mono;

import java.lang.reflect.Array;
import java.sql.Time;
import java.util.List;

public interface WebService {
    public Flight[] getFlights();
    public Flight getFlight(String name, String flightId);

    public String[] getFlightTimes(String name, String flightId);

    public Seat[] getAvailableSeats(String name, String flightId, Time time);

    public Seat getSeat(String name, String flightId, String seatId);
}
