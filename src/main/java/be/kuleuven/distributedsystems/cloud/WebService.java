package be.kuleuven.distributedsystems.cloud;

import be.kuleuven.distributedsystems.cloud.entities.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface WebService {
    public Flight[] getFlights();
    public Flight getFlight(String airline, String flightId);

    public String[] getFlightTimes(String airline, String flightId);

    public Map<String, List<Seat>> getAvailableSeats(String airline, String flightId, String time);

    public Seat getSeat(String airline, String flightId, String seatId);

    public Ticket putSeat(String airline , String flightId, String seatId, String user, String bookingReference);

    void cancelTicket(String airline, String toString, String toString1, String ticketId);
}
