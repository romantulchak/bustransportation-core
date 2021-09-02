package com.romantulchak.bustransportation.validator.constraint;

import com.romantulchak.bustransportation.validator.BusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = BusValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface BusConstraint {
    String message() default "Bus cannot be null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
