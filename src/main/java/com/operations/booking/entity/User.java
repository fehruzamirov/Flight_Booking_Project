package com.operations.booking.entity;




import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Slf4j
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "user_data", schema = "fligh_booking")
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    @Email
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Column(name = "password")
    @Size(min = 8)
    @NotEmpty(message = "Name cannot be empty")
    private String password;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Date of birth cannot be empty")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    @NotBlank(message = "Gender cannot be empty")
    private String gender;

    @Column(name = "status")
    @NotBlank(message = "status cannot be empty")
    private String status;

    @Column(name = "contact_number")
    @NotBlank(message = "status cannot be empty")
    private Long contactNumber;

    @Column(name = "registration_date")
    @CreationTimestamp
    @NotNull(message = "Date cannot be empty")
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<UserLog> userLogs;


    //@Enumerated(EnumType.STRING)
    //private Role role;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
        //return List.of(new SimpleGrantedAuthority(role.name()));
    }
    //@OneToOne(mappedBy = "user")
    //private Booking booking;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
