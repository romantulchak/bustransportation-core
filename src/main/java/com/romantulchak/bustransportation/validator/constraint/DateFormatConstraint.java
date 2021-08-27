package com.romantulchak.bustransportation.validator.constraint;

import com.romantulchak.bustransportation.validator.DateFormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = DateFormatValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormatConstraint {
    String message() default "Wrong date format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
