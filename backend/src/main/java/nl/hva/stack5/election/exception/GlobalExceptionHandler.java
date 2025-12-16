package nl.hva.stack5.election.exception;

import nl.hva.stack5.election.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * Handles runtime exceptions.
     *
     * @param ex The RuntimeException that was thrown
     * @return ResponseEntity with 500 status and error message
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(500, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    /**
     * Handles ResponseStatusExceptions
     *
     * @param ex The ResponseStatusException that was thrown
     * @return ResponseEntity with corresponding status code and error message
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        ErrorResponse error = new ErrorResponse(ex.getStatusCode().value(), ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }
}
