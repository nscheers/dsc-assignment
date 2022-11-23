package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.Booking;
import be.kuleuven.distributedsystems.cloud.entities.Flight;
import be.kuleuven.distributedsystems.cloud.entities.Seat;
import be.kuleuven.distributedsystems.cloud.entities.User;

import java.util.List;
import java.util.Map;

public interface WebService {
    public Flight[] getFlights();
    public Flight getFlight(String airline, String flightId);

    public String[] getFlightTimes(String airline, String flightId);

    public Map<String, List<Seat>> getAvailableSeats(String airline, String flightId, String time);

    public Seat getSeat(String airline, String flightId, String seatId);

}
