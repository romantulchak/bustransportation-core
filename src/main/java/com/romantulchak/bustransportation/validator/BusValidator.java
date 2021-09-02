package com.romantulchak.bustransportation.validator;

import com.romantulchak.bustransportation.model.Bus;
import com.romantulchak.bustransportation.service.impl.UserDetailsImpl;
import com.romantulchak.bustransportation.validator.constraint.BusConstraint;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BusValidator implements ConstraintValidator<BusConstraint, Bus> {
    @Override
    public boolean isValid(Bus bus, ConstraintValidatorContext context) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (bus == null){
            return false;
        }
        if (bus.getUser().getId() != user.getId()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(String.format("Current bus %s is not belong to user in system", bus.getName()));
            return false;
        }

        return true;
    }
}
