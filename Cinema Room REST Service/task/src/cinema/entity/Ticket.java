package cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@JsonPropertyOrder({"row", "column", "price"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Ticket {
    @JsonIgnore
    @Id
    @SequenceGenerator(
            name = "ticket_sequence",
            sequenceName = "ticket_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ticket_sequence"
    )
    private Long id;
    private Integer row;

    @JsonProperty("column")
    private Integer col;
    private Integer price;
    @JsonIgnore
    private boolean isAvailable;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;


    public Ticket(Integer row, Integer col, Integer price, boolean isAvailable, Room room) {
        this.row = row;
        this.col = col;
        this.price = price;
        this.isAvailable = isAvailable;
        this.room = room;
    }
}
