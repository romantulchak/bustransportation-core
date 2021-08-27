package com.romantulchak.bustransportation.validator;

import com.romantulchak.bustransportation.validator.constraint.DateFormatConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class DateFormatValidator implements ConstraintValidator<DateFormatConstraint, LocalDateTime> {
    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(value)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("The selected date is out of date").addConstraintViolation();
            return false;
        }
        return true;
    }
}
