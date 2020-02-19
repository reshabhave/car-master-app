package com.ubs.carmasterapp.validator;

import com.ubs.carmasterapp.model.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarCustomValidatorTest {
    @Autowired
    private Validator validator;

    @Test
    public void testWhiteSpaceInName(){
        Car car = new Car("Tata Indica", 600000, "Vista", 2012, "DIESEL");
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        assertEquals(1, violations.size(), 0);
        assertEquals("Special Characters and whitespaces not allowed", ((ConstraintViolation)violations.iterator().next()).getMessage());
    }

    @Test
    public void testSpecialCharacterInName(){
        Car car = new Car("Tata$Indica", 600000, "Vista", 2012, "DIESEL");
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        assertEquals(1, violations.size(), 0);
        assertEquals("Special Characters and whitespaces not allowed", ((ConstraintViolation)violations.iterator().next()).getMessage());
    }

    @Test
    public void testCarAge(){
        Car car = new Car("TataIndica", 600000, "Vista", 2004, "DIESEL");
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        assertEquals(1, violations.size(), 0);
        assertEquals("Car should not be more than 15 years old", ((ConstraintViolation)violations.iterator().next()).getMessage());
    }

    @Test
    public void testCarValidations(){
        Car car = new Car("Tata Indica", 600000, "Vista", 2004, "DIESEL");
        Set<ConstraintViolation<Car>> violations = validator.validate(car);
        assertEquals(2, violations.size(), 0);
    }
}
