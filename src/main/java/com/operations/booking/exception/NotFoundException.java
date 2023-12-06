
package com.operations.booking.exception;


public class NotFoundException extends RuntimeException {
    public NotFoundException(String exception){
        super(exception + "Not Found");
    }
}

