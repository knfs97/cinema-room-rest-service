package cinema.exceptions;



public class TicketAlreadyPurchasedException extends RuntimeException{

    private static final String CAUSE = "The ticket has been already purchased!";
    public TicketAlreadyPurchasedException() {super(CAUSE);}
}
