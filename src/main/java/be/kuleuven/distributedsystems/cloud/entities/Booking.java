package be.kuleuven.distributedsystems.cloud.entities;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.ServerTimestamp;
import com.google.type.DateTime;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Booking {
    private UUID id;
    private @ServerTimestamp Timestamp time;
    private List<Ticket> tickets;
    private String customer;

    public Booking(UUID id, List<Ticket> tickets, String customer) {
        this.id = id;
        this.tickets = tickets;
        this.customer = customer;
    }

    public UUID getId() {
        return this.id;
    }

    public Timestamp getTime() {
        return this.time;
    }

    public List<Ticket> getTickets() {
        return this.tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getCustomer() {
        return this.customer;
    }
}
