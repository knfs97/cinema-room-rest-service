package cinema.exceptions;

public class TickerNotFoundException extends RuntimeException{
    private static final String CAUSE = "Ticket does not exists";
    public TickerNotFoundException() {super(CAUSE);}
}
