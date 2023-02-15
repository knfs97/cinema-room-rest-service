package cinema.repository;

import cinema.entity.PurchasedTicket;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PurchasedTicketRepository extends CrudRepository<PurchasedTicket, UUID> {}
