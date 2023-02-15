package cinema.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Stats {
    @JsonProperty("current_income")
    private Integer currentIncome;
    @JsonProperty("number_of_available_seats")
    private Integer numberOfAvailableSeats;
    @JsonProperty("number_of_purchased_tickets")
    private Integer numberOfPurchasedTickets;
}
