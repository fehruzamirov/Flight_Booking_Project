package com.operations.booking.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


import java.util.List;
import java.util.StringJoiner;

@Data
public class AirportDTO {

    @NotBlank(message = "{validation.messages.airport.name}")
    private String name;

    @NotEmpty(message = "{validation.messages.airport.address}")
    private List<@Valid Address> addresses;

    public String formatAddresses() {
        StringJoiner strJoiner = new StringJoiner(" // ");
        getAddresses().forEach(address -> strJoiner.add(address.dbFormat()));
        return strJoiner.toString();
    }
}
