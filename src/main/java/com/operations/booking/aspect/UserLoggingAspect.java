package com.operations.booking.aspect;

import com.operations.booking.dto.request.AuthenticationRequest;
import com.operations.booking.entity.User;
import com.operations.booking.entity.UserLog;
import com.operations.booking.repository.UserLogRepository;
import com.operations.booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class UserLoggingAspect {   // User Loglama əməliyyatı @AfterReturning
    private final UserLogRepository userLogRepository;

    private final UserRepository userRepository;

    @AfterReturning("execution(* com.operations.booking.service.impl.AuthenticationService.authenticate(..))")
    public void logSignIn(JoinPoint joinPoint) {
        UserLog log = new UserLog();
        AuthenticationRequest authenticationRequest = (AuthenticationRequest) joinPoint.getArgs()[0];
        String username = authenticationRequest.getEmail();

        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            log.setUser(user);
            log.setLastLoginDate(LocalDate.now());
            userLogRepository.save(log);
        }
    }
}



