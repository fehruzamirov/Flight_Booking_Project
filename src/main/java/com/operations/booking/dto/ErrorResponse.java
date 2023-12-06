package com.operations.booking.dto;

import lombok.Data;

@Data
public class ErrorResponse {

    private String clazz;
    private String message;

    public ErrorResponse(RuntimeException e) {
        setClazz(e.getClass().toString());
        setMessage(e.getMessage());
    }
}
