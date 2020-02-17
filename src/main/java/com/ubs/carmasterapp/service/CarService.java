package com.ubs.carmasterapp.service;

import com.ubs.carmasterapp.dao.CarDAO;
import com.ubs.carmasterapp.exception.ResourceNotFoundException;
import com.ubs.carmasterapp.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    CarDAO carDAO;

    @Cacheable("getCarById")
    public Car getCarById(Integer carId) throws ResourceNotFoundException {
        Car car = carDAO.findCarById(carId);
        System.out.println("service called DAO");
        return car;
    }

    public List<Car> getAllCars() {
        return carDAO.findAllCars();
    }

    public Car createCar(Car car) {
        return carDAO.saveCar(car);
    }

    public Car updateCar(Car car, Integer carId) throws ResourceNotFoundException {
        return carDAO.updateCar(car, carId);
    }

    public Boolean deleteCar(Integer carId) throws ResourceNotFoundException {
        return carDAO.deleteCar(carId);
    }
}
