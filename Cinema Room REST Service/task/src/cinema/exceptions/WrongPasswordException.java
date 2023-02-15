package cinema.exceptions;

public class WrongPasswordException extends RuntimeException{
    private static final String CAUSE = "The password is wrong!";

    public WrongPasswordException() {super(CAUSE);}
}
