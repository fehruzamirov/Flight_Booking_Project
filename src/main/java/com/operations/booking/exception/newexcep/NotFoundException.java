package com.operations.booking.exception.newexcep;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message + " not found!");
    }
}
