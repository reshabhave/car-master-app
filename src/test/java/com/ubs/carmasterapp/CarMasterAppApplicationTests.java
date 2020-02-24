package com.ubs.carmasterapp;

import com.ubs.carmasterapp.model.Car;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
class CarMasterAppApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort()
	int randomServerPort;

	@Value("${car.service.URL}")
	private String carServiceURL;

	@Test
	public void testCreateCarSuccess() throws URISyntaxException
	{
		final String baseUrl = carServiceURL;
		URI uri = new URI(baseUrl);
		Car carObj = new Car("Honda", 0, "Amaze", 2014, "PETROL");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Car> request = new HttpEntity<>(carObj, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
	}

	@Test
	public void testCreateCarNameValidation() throws URISyntaxException
	{
		final String baseUrl = carServiceURL;
		URI uri = new URI(baseUrl);
		Car carObj = new Car("Maruti Suzuki", 0, "Alto", 2012, "PETROL");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Car> request = new HttpEntity<>(carObj, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

		Assert.assertEquals(500, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("Special Characters and whitespaces not allowed"));
	}

	@Test
	public void testCreateCarAgeValidation() throws URISyntaxException
	{
		final String baseUrl = carServiceURL;
		URI uri = new URI(baseUrl);
		Car carObj = new Car("Maruti", 0, "Alto", 2004, "PETROL");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Car> request = new HttpEntity<>(carObj, headers);

		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

		Assert.assertEquals(500, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("Car should not be more than 15 years old"));
	}

	@Test
	public void testGetCarById() throws URISyntaxException
	{
		final String baseUrl = carServiceURL + "/1";
		URI uri = new URI(baseUrl);

		ResponseEntity<Car> result = this.restTemplate.getForEntity(uri, Car.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals("Honda", result.getBody().getCarName());
	}

	@Test
	public void testGetAllCars() throws URISyntaxException
	{
		final String baseUrl = carServiceURL;
		URI uri = new URI(baseUrl);

		ResponseEntity<List> result = this.restTemplate.getForEntity(uri, List.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(1, result.getBody().size());
		Assert.assertEquals("Honda", ((Car)result.getBody().get(0)).getCarName());
	}

	@Test
	public void testUpdateCar() throws URISyntaxException
	{
		final String baseUrl = carServiceURL + "/1";
		URI uri = new URI(baseUrl);

		Car carObj = new Car("Honda", 900000, "Amaze", 2014, "PETROL");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Car> request = new HttpEntity<>(carObj, headers);

		ResponseEntity<Car> result = this.restTemplate.exchange(uri, HttpMethod.PUT, request, Car.class);
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(900000, result.getBody().getPrice(), 0);
	}

	@Test
	public void testDeleteCar() throws URISyntaxException
	{
		final String baseUrl = carServiceURL + "/1";
		URI uri = new URI(baseUrl);

		Car carObj = new Car("Honda", 900000, "Amaze", 2014, "PETROL");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> request = new HttpEntity<>("", headers);

		ResponseEntity<String> result = this.restTemplate.exchange(uri, HttpMethod.DELETE, request, String.class);
		Assert.assertEquals(200, result.getStatusCodeValue());

	}
}
