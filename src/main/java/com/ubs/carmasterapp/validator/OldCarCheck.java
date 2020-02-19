package com.ubs.carmasterapp.validator;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CarAgeValidator.class)
@Documented
public @interface OldCarCheck {
    String message() default "Car should not be more than 15 years old";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
