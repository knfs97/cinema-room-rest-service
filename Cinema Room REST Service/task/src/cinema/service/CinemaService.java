package cinema.service;

import cinema.entity.*;
import cinema.exceptions.InvalidRowColumnException;
import cinema.exceptions.TickerNotFoundException;
import cinema.exceptions.TicketAlreadyPurchasedException;
import cinema.exceptions.WrongTokenException;
import cinema.repository.PurchasedTicketRepository;
import cinema.repository.RoomRepository;
import cinema.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.PushbackInputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CinemaService {

    private final RoomRepository roomRepository;
    private final TicketRepository ticketRepository;
    private final PurchasedTicketRepository purchasedTicketRepository;

    @Autowired
    public CinemaService(RoomRepository roomRepository, TicketRepository ticketRepository, PurchasedTicketRepository purchasedTicketRepository) {
        this.roomRepository = roomRepository;
        this.ticketRepository = ticketRepository;
        this.purchasedTicketRepository = purchasedTicketRepository;
    }


    public PurchasedTicket purchaseTicket(Ticket ticket) {

        Room room = findRoomById(1L);
        // invalid rows or columns
        if (isInvalidColsOrRows(room, ticket)) throw new InvalidRowColumnException();

        Integer row = ticket.getRow();
        Integer col = ticket.getCol();
        Long roomId = room.getId();
        Optional<Ticket> ticketOpt = ticketRepository.findTicketByRowColAndRoomId(row, col, roomId);

        // ticket is not found
        if (ticketOpt.isEmpty()) throw new InvalidRowColumnException();

        if (!ticketOpt.get().isAvailable()) throw new TicketAlreadyPurchasedException();
        ticketOpt.get().setAvailable(false);
        ticketRepository.save(ticketOpt.get());
        // create token
        return purchasedTicketRepository.save(new PurchasedTicket(ticketOpt.get()));
    }
    public Room findRoomById(long id) {
        Optional<Room> roomOpt =  roomRepository.findById(id);
        if (roomOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room with id " + id + " does not exists");
        }
        Room room = roomOpt.get();
        room.setTickets(filterAvailableSeats(room));
        return roomOpt.get();
    }

    public boolean isInvalidColsOrRows(Room room, Ticket ticket) {
        return room.getTotalColumns() < ticket.getCol() || room.getTotalRows() < ticket.getRow();
    }

    public List<Ticket> filterAvailableSeats(Room room) {
        return room.getTickets().stream().filter(Ticket::isAvailable).toList();
    }

    public ReturnedTicket returnTicket(UUID token) {
       Optional<PurchasedTicket> purchasedTicketOpt = purchasedTicketRepository.findById(token);

        if (purchasedTicketOpt.isEmpty()) throw new WrongTokenException();

        PurchasedTicket purchasedTicket = purchasedTicketOpt.get();

        Optional<Ticket> ticketOpt = ticketRepository.findById(purchasedTicket.getTicket_details().getId());
        if (ticketOpt.isEmpty()) throw new WrongTokenException();
        Ticket ticket = ticketOpt.get();
        ticket.setAvailable(true);
        ticketRepository.save(ticket);

        return new ReturnedTicket(ticket);
    }

    public Stats getStats() {
        Optional<Room> roomOpt = roomRepository.findById(1L);
        if (roomOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found");
        }
        Room room = roomOpt.get();

        List<Ticket> purchasedTickets = room.getTickets().stream().filter(ticket -> !ticket.isAvailable()).toList();
        int numberOfAvailableSeats = filterAvailableSeats(room).size();
        int numberOfPurchasedTickets = purchasedTickets.size();
        int currentIncome = purchasedTickets.stream().mapToInt(Ticket::getPrice).sum();

        return new Stats(currentIncome, numberOfAvailableSeats, numberOfPurchasedTickets);
    }
}
