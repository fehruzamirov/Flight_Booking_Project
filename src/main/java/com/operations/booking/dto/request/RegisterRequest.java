package com.operations.booking.dto.request;




import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Surname cannot be null")
    private String surname;

    @Email
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    private Long contactNumber;

    @NotNull(message = "Date of birth cannot be empty")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Gender cannot be empty")
    private String gender;

    @Size(min = 8)
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    private String status;

    private String role;

}
