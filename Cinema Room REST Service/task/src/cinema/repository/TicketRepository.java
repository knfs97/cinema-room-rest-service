package cinema.repository;

import cinema.entity.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    @Query("SELECT t FROM Ticket t WHERE t.row = :row AND t.col = :col AND t.room.id = :room_id")
    Optional<Ticket> findTicketByRowColAndRoomId(@Param("row") Integer row, @Param("col") Integer column, @Param("room_id") Long roomId);
}
