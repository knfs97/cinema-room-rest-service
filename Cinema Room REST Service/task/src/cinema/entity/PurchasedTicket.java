package cinema.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PurchasedTicket {

    @GeneratedValue
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Id
    @Column(length = 36, unique = true, nullable = false)
    @JsonSerialize(using = ToStringSerializer.class)
    private UUID token;

    @JsonProperty("ticket")
    @OneToOne
    @JoinColumn(name = "id", nullable = false)
    private Ticket ticket_details;

    public PurchasedTicket(Ticket ticket_details) {
        this.ticket_details = ticket_details;
    }
}
