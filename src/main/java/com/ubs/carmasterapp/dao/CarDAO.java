package com.ubs.carmasterapp.dao;

import com.ubs.carmasterapp.exception.ResourceNotFoundException;
import com.ubs.carmasterapp.model.Car;
import com.ubs.carmasterapp.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarDAO {
    @Autowired
    CarRepository carRepository;

    public Car findCarById(Integer carId) throws ResourceNotFoundException {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car not found for this carId :: " + carId));
        return car;
    }

    public List<Car> findAllCars(){
        List<Car> allCars = carRepository.findAll();
        return allCars;
    }

    public Car saveCar(Car car){
        Car newCar = carRepository.save(car);
        return newCar;
    }

    public Car updateCar(Car car, Integer carId) throws ResourceNotFoundException{
        Car existingCar = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car cannot be updated as not found for carId :: " + carId));
        existingCar.setCarName(car.getCarName());
        existingCar.setPrice(car.getPrice());
        existingCar.setModel(car.getModel());
        existingCar.setYearOfManufacture(car.getYearOfManufacture());
        existingCar.setFuelType(car.getFuelType());
        Car updatedCar = carRepository.save(existingCar);
        return updatedCar;
    }

    public Boolean deleteCar(Integer carId) throws ResourceNotFoundException{
        Car existingCar = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car cannot be deleted as not found for carId :: " + carId));
        carRepository.delete(existingCar);
        return Boolean.TRUE;
    }

}
