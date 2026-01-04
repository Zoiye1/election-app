package nl.hva.stack5.election.exception;

import jakarta.persistence.EntityNotFoundException;
import nl.hva.stack5.election.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * Handles EntityNotFoundException
     *
     * @param ex The EntityNotFoundException that was thrown
     * @return ResponseEntity with 404 status and error message
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        logger.warn("EntityNotFoundException: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Handles ResponseStatusExceptions
     *
     * @param ex The ResponseStatusException that was thrown
     * @return ResponseEntity with corresponding status code and error message
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException ex) {
        logger.warn("ResponseStatusException: {}", ex.getReason());
        ErrorResponse error = new ErrorResponse(ex.getStatusCode().value(), ex.getReason());
        return ResponseEntity.status(ex.getStatusCode()).body(error);
    }

    /**
     * Handles JpaObjectRetrievalFailureException (entity not found via JPA)
     *
     * @param ex The JpaObjectRetrievalFailureException that was thrown
     * @return ResponseEntity with 404 status and error message
     */
    @ExceptionHandler(JpaObjectRetrievalFailureException.class)
    public ResponseEntity<ErrorResponse> handleJpaObjectRetrievalFailureException(JpaObjectRetrievalFailureException ex) {
        logger.warn("JpaObjectRetrievalFailureException: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse(404, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Handles runtime exceptions.
     *
     * @param ex The RuntimeException that was thrown
     * @return ResponseEntity with 500 status and error message
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        logger.error("RuntimeException: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse(500, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
