package cinema.exceptions;

public class WrongTokenException extends RuntimeException{
    private static final String CAUSE = "Wrong token!";

    public WrongTokenException() {super(CAUSE);}
}