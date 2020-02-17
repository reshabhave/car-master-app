package com.ubs.carmasterapp.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;

public class CarAgeValidator implements ConstraintValidator<OldCarCheck, Integer> {
    public boolean isValid(Integer yearOfManufacture, ConstraintValidatorContext constraintValidatorContext) {
        boolean validFlag = true;
        Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if(currentYear - yearOfManufacture > 15) {
            validFlag = false;
        }
        return validFlag;
    }
}
