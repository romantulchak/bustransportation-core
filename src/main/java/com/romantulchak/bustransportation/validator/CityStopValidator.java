package com.romantulchak.bustransportation.validator;


import com.romantulchak.bustransportation.model.CityStop;
import com.romantulchak.bustransportation.validator.constraint.CityStopConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class CityStopValidator implements ConstraintValidator<CityStopConstraint, List<CityStop>> {
    @Override
    public boolean isValid(List<CityStop> value, ConstraintValidatorContext context) {
        boolean correctSize = value.size() >= 2;
        if (correctSize) {
            value.stream().filter(stop -> !stop.isBusStop() && stop.getPrice() != 0).forEach(stop -> stop.setPrice(0));
            List<CityStop> stops = new ArrayList<>(value);
            ListIterator<CityStop> cityStopListIterator = stops.listIterator();
            while (cityStopListIterator.hasNext()) {
                if (stops.size() == 1)
                    break;
                CityStop previous = cityStopListIterator.next();
                for (CityStop stop : stops) {
                    if (!(Objects.equals(previous, stop)) && stop.getDeparture().isBefore(previous.getDeparture())) {
                        context.disableDefaultConstraintViolation();
                        context.buildConstraintViolationWithTemplate("Check that the dates are correct").addConstraintViolation();
                        return false;
                    }
                }
                cityStopListIterator.remove();
            }
            return true;
        }
        return false;
    }

}
