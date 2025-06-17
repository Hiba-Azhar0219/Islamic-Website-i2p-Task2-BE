package org.i2p.fidduniyabe.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        err -> err.getField(),
                        err -> err.getDefaultMessage()
                ));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Handle DB constraint violations (like unique constraint)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String rootMsg = ex.getRootCause() != null ? ex.getRootCause().getMessage() : "";

        String message;

        // Customize based on unique constraint
        if (rootMsg.contains("duplicate key") || rootMsg.contains("Duplicate entry")) {
            message = "Duplicate entry found.";
        } else {
            message = "A required field is missing (null value) that must not be null.";
        }
        return new ResponseEntity<>(Map.of("error", message), HttpStatus.CONFLICT);
    }

    // Optional: catch any other unhandled exceptions to avoid server errors leaking
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllOtherExceptions(Exception ex) {
        return new ResponseEntity<>(Map.of("error", "Internal server error: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
