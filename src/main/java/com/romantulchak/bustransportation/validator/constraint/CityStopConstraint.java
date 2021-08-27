package com.romantulchak.bustransportation.validator.constraint;

import com.romantulchak.bustransportation.validator.CityStopValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = CityStopValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface CityStopConstraint {
    String message() default "Size of stops are not correct";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
