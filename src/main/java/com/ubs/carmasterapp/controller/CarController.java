package com.ubs.carmasterapp.controller;

import com.ubs.carmasterapp.exception.ResourceNotFoundException;
import com.ubs.carmasterapp.model.Car;
import com.ubs.carmasterapp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable(value = "id") Integer carId)
            throws ResourceNotFoundException {
        System.out.println("Controller called service");
        Car car = carService.getCarById(carId);
        return ResponseEntity.ok().body(car);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        List<Car> allCars = carService.getAllCars();
        return allCars;
    }

    @PostMapping("/cars")
    public Car createCar(@Valid @RequestBody Car car) {
        Car newCar =  carService.createCar(car);
        return newCar;
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable(value = "id") Integer carId,
                                                   @Valid @RequestBody Car car) throws ResourceNotFoundException {
        Car updatedCar = carService.updateCar(car, carId);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/cars/{id}")
    public Map<String, Boolean> deleteCar(@PathVariable(value = "id") Integer carId)
            throws ResourceNotFoundException {
        Boolean deleted = carService.deleteCar(carId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return response;
    }
}
