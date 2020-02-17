package com.ubs.carmasterapp.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SpecialCharacterValidator.class)
@Documented
public @interface NoSpecialCharacter {
    String message() default "Special Characters and whitespaces not allowed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
