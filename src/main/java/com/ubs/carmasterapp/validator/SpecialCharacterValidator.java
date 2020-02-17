package com.ubs.carmasterapp.validator;

import org.springframework.validation.ValidationUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialCharacterValidator implements ConstraintValidator<NoSpecialCharacter, String> {
    public boolean isValid(String stringVal, ConstraintValidatorContext constraintValidatorContext){
        Pattern pattern = Pattern.compile("[A-Za-z0-9]*");
        Matcher matcher = pattern.matcher(stringVal);
        boolean validFlag = true;

        if(!matcher.matches()) {
            validFlag = false;
        }
        return  validFlag;
    }
}
