package com.operations.booking.dto.response;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private int id;

    private String email;

    private String password;

    private LocalDate dateOfBirth;

    private String gender;

    private String status;

    private Long contactNumber;

    private LocalDate registrationDate;

    private List<UserLogResponse> userLogs;
}
