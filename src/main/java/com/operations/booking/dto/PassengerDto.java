package com.operations.booking.dto;

import lombok.Data;

@Data
public class PassengerDto {
    private Integer id;
    private String firstname;
    private String lastname;
    private String gender;
    private Integer age;
    private String phone;
    private String email;
}
