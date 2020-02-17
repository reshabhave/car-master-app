package com.ubs.carmasterapp.model;

import com.ubs.carmasterapp.validator.NoSpecialCharacter;
import com.ubs.carmasterapp.validator.OldCarCheck;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    private Integer carId;

    @NoSpecialCharacter
    private String carName;
    private double price;
    private String model;

    @OldCarCheck
    private Integer yearOfManufacture;
    private String fuelType;

    public Car() {}

    public Car(String carName, double price, String model, Integer yearOfManufacture, String fuelType){
        this.carName = carName;
        this.price = price;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.fuelType = fuelType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    @Column(name = "car_name", nullable = false)
    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    @Column(name = "car_price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "car_model", nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "year_of_manufacture", nullable = false)
    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    @Column(name = "fuel_type", nullable = false)
    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
