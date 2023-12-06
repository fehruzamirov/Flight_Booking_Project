package com.operations.booking.annotation.validator;


import com.operations.booking.annotation.FlightIDExists;
import com.operations.booking.exception.newexcep.NotFoundException;
import com.operations.booking.service.FlightService;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
public class FlightIDExistingValidator implements ConstraintValidator<FlightIDExists, Long> {

    private final FlightService flightService;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        // əgər yoxdursa not found exception qaytarir.
        try {
            flightService.getFlight(id.intValue());
        } catch (NotFoundException e) {
            return true;
        }
        return false;
    }
}
