package edu.marcio.stock.exceptions;

public class ResourceAlreadyRegisteredException extends RuntimeException {
    public ResourceAlreadyRegisteredException(String message) {
        super(message);
    }
}
