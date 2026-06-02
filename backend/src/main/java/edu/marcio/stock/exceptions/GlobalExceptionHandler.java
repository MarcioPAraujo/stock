package edu.marcio.stock.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import edu.marcio.stock.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyRegisteredException.class)
    public ResponseEntity<ErrorResponse> handelResourceAlreadyRegisteredException(
            ResourceAlreadyRegisteredException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(400, "bad request", e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(400, "bad request", e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(400, "bad request", e.getMessage(), LocalDateTime.now()));
    }

    // validation excption
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, Object> response = new HashMap<>();

        response.put("status", 400);
        response.put("error", "bad request");
        response.put("timestamp", LocalDateTime.now());

        List<Map<String, String>> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(field -> {
                    Map<String, String> errorDetails = new HashMap<>();
                    errorDetails.put("field", field.getField());
                    errorDetails.put("message", field.getDefaultMessage());
                    return errorDetails;
                })
                .collect(Collectors.toList());

        response.put("errors", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    // spring security exceptions
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFound(UsernameNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(400, "bad request", "invalid email or password", LocalDateTime.now()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(400, "bad request", "invalid email or password", LocalDateTime.now()));
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ErrorResponse> handleBlockedAccount(LockedException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(401, "unauthorized", "account is locked", LocalDateTime.now()));
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ErrorResponse> handleDisbledAccount(DisabledException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(401, "unauthorized", "account disabled", LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalErrorServer(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(500, String.format("internal error server: %s", e.getMessage()),
                        "an unexpected error occurred",
                        LocalDateTime.now()));
    }
}
