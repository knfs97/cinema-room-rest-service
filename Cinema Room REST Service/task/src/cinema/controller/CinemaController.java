package cinema.controller;

import cinema.entity.*;
import cinema.exceptions.InvalidPostRequest;
import cinema.exceptions.WrongPasswordException;
import cinema.exceptions.WrongTokenException;
import cinema.service.CinemaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CinemaController {

    private final CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {this.cinemaService = cinemaService;}

    @GetMapping("/seats")
    public Room getRoom() {
        return cinemaService.findRoomById(1L);
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchasedTicket> purchaseTicket(@RequestBody Ticket ticket ){
        // missing parameter in post request
        if (ticket.getRow() == null || ticket.getCol() == null) throw new InvalidPostRequest();
        PurchasedTicket purchasedTicket = cinemaService.purchaseTicket(ticket);
        return new ResponseEntity<>(purchasedTicket, HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<ReturnedTicket> returnTicket(@RequestBody Token token ){
        // missing parameter in post request
        if (token == null) throw new WrongTokenException();
        ReturnedTicket returnedTicket = cinemaService.returnTicket(token.getToken());
        return new ResponseEntity<>(returnedTicket, HttpStatus.OK);
    }

    @PostMapping(path = "stats")
    public ResponseEntity<Stats> showStats(@RequestParam(required = false) String password) {
        if (!"super_secret".equals(password)) throw new WrongPasswordException();

        Stats stats = cinemaService.getStats();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

}
