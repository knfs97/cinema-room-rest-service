package cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"total_rows", "total_columns", "available_seats"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @JsonIgnore
    @SequenceGenerator(
            name = "room_sequence",
            sequenceName = "room_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence"
    )
    private Long id;
    @JsonProperty("total_rows")
    Integer totalRows;
    @JsonProperty("total_columns")
    Integer totalColumns;

    @JsonProperty("available_seats")
    @OneToMany(mappedBy = "room")
    private List<Ticket> tickets = new ArrayList<>();

    public Room(Long id) {
        this.id = id;
    }

    public Room(Integer totalRows, Integer totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
    }
}
