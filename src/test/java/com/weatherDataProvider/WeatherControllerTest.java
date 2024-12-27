package com.weatherDataProvider;

import com.weatherDataProvider.controller.WeatherController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WeatherControllerTest {

	@Autowired
	private WeatherController weatherController;

	@Test
	public void testInvalidPostalCode() {
		ResponseEntity<String> response = weatherController.fetchWeather("900050", "John Doe");
		assertEquals(400, response.getStatusCodeValue()); // Expecting a bad request for invalid postal code
	}

	@Test
	public void testValidRequest() {
		ResponseEntity<String> response = weatherController.fetchWeather("94040", "John Doe");
		assertEquals(200, response.getStatusCodeValue()); // Expecting success for valid postal code
	}

	@Test
	public void testWeatherApiFailure() {
		ResponseEntity<String> response = weatherController.fetchWeather("99999", "John Doe"); // Invalid postal code
		assertEquals(500, response.getStatusCodeValue()); // Expecting failure for invalid API response
	}

}
