package cinema.util;

import cinema.entity.Room;
import cinema.entity.Ticket;
import cinema.repository.RoomRepository;
import cinema.repository.TicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoomConfig {
    @Bean
    CommandLineRunner commandLineRunner(RoomRepository roomRepository, TicketRepository ticketRepository) {
        return args -> {
            Room room = new Room(9, 9);
            roomRepository.save(room);

            for (int i = 1; i <= 9; i++) {
                for (int j = 1; j <= 9; j++) {
                    int price = i < 5 ? 10 : 8;
                    Ticket ticket = new Ticket(i, j, price, true, new Room(1L));
                    ticketRepository.save(ticket);
                }
            }
        };
    }
}
