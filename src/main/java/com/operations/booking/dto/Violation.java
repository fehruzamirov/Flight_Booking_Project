package com.operations.booking.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class Violation {
    private final String fieldName;
    private final String message;
}
