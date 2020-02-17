package com.ubs.carmasterapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CarMasterAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarMasterAppApplication.class, args);
	}

}
