package cinema.exceptions;



public class InvalidRowColumnException extends RuntimeException{
    private static final String CAUSE = "The number of a row or a column is out of bounds!";

    public InvalidRowColumnException() {super(CAUSE);}
}
