package com.operations.booking.annotation.validator;


import com.operations.booking.annotation.CustomDateConstraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class CustomDateValidator implements
        ConstraintValidator<CustomDateConstraint, LocalDate> {
    //müəyyən bir tarix formatını yoxlayır və bu formata uyğun olmayan tarixləri etibarsız hesab edecək.
    private static final String DATE_PATTERN = "MM/dd/yyyy";

    @Override
    public void initialize(CustomDateConstraint customDate) {
    }

    @Override
    public boolean isValid(LocalDate customDateField,
                           ConstraintValidatorContext cxt) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        try {
            sdf.setLenient(false);
            Date d = sdf.parse(String.valueOf(customDateField));
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
