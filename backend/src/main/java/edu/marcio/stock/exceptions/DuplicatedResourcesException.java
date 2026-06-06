package edu.marcio.stock.exceptions;

public class DuplicatedResourcesException extends RuntimeException {
    public DuplicatedResourcesException(String message) {
        super(message);
    }
}
