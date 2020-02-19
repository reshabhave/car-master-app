package com.ubs.carmasterapp.service;

import com.ubs.carmasterapp.dao.CarDAO;
import com.ubs.carmasterapp.exception.ResourceNotFoundException;
import com.ubs.carmasterapp.model.Car;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTests {
    @InjectMocks
    CarService carService;

    @Mock
    CarDAO carDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCarByIdTest() throws ResourceNotFoundException{
        Car carObj = new Car("Honda", 900000, "Amaze", 2014, "PETROL");
        carObj.setCarId(1);
        when(carDAO.findCarById(1)).thenReturn(carObj);

        Car car = carService.getCarById(1);
        assertEquals("Honda", car.getCarName());
        assertEquals(900000, car.getPrice(), 0);
        assertEquals("Amaze", car.getModel());
        assertEquals(new Integer(2014), car.getYearOfManufacture());
        assertEquals("PETROL", car.getFuelType());
    }

    @Test
    public void getAllCarsTest(){
        Car car1 = new Car("Honda", 900000, "Amaze", 2014, "PETROL");
        Car car2 = new Car("Maruti", 0, "Alto", 2011, "PETROL");
        car1.setCarId(1);
        car2.setCarId(2);
        List<Car> allCars = new ArrayList<Car>();
        allCars.add(car1);
        allCars.add(car2);
        when(carDAO.findAllCars()).thenReturn(allCars);

        List<Car> cars = carService.getAllCars();
        assertEquals(2, cars.size());
        verify(carDAO, times(1)).findAllCars();
    }

    @Test
    public void createCarTest(){
        Car car3 = new Car("TataIndica", 600000, "Vista", 2012, "DIESEL");
        carService.createCar(car3);
        verify(carDAO, times(1)).saveCar(car3);
    }

    @Test
    public void updateCarTest() throws ResourceNotFoundException{
        Car car2 = new Car("Maruti", 250000, "Alto", 2011, "PETROL");
        car2.setCarId(2);
        when(carDAO.updateCar(car2, 2)).thenReturn(car2);

        Car updatedCAr = carService.updateCar(car2, 2);
        assertEquals(250000, car2.getPrice(), 0);
        verify(carDAO, times(1)).updateCar(car2, 2);
    }

    @Test
    public void deleteCarTest() throws ResourceNotFoundException{
        when(carDAO.deleteCar(2)).thenReturn(Boolean.TRUE);

        Boolean isCarDeleted = carService.deleteCar(2);
        assertEquals(isCarDeleted, Boolean.TRUE);
        verify(carDAO, times(1)).deleteCar(2);
    }
}
