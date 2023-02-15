package cinema.handler;

import cinema.exceptions.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(InvalidPostRequest.class)
    protected ResponseEntity<ErrorResponse> handlerCustomAppException(InvalidPostRequest ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRowColumnException.class)
    protected ResponseEntity<ErrorResponse> handlerCustomAppException(InvalidRowColumnException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
         return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TicketAlreadyPurchasedException.class)
    protected ResponseEntity<ErrorResponse> handlerCustomAppException(TicketAlreadyPurchasedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
         return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TickerNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handlerCustomAppException(TickerNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
         return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongTokenException.class)
    protected ResponseEntity<ErrorResponse> handlerCustomAppException(WrongTokenException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    protected ResponseEntity<ErrorResponse> handlerCustomAppException(WrongPasswordException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
