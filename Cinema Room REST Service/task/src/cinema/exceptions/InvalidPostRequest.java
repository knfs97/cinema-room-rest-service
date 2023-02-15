package cinema.exceptions;

public class InvalidPostRequest extends RuntimeException{
    private static final String CAUSE = "Missing parameters in post request";

    public InvalidPostRequest() {super(CAUSE);}
}
