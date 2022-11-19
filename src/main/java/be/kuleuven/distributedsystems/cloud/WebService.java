package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.Booking;
import be.kuleuven.distributedsystems.cloud.entities.Flight;
import be.kuleuven.distributedsystems.cloud.entities.Seat;
import be.kuleuven.distributedsystems.cloud.entities.User;

public interface WebService {
    public Flight[] getFlights();
    public Flight getFlight(String name, String flightId);

    public String[] getFlightTimes(String name, String flightId);

    public Seat[] getAvailableSeats(String name, String flightId, String time);

    public Seat getSeat(String name, String flightId, String seatId);

}
