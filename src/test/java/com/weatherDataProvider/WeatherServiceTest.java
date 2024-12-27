package com.weatherDataProvider;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.weatherDataProvider.entity.WeatherData;
import com.weatherDataProvider.services.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherServiceTest {
    @Autowired
    private WeatherService weatherService;

    @Test
    public void testWeatherApi() {
        String postalCode = "94040";
        WeatherData weatherData = weatherService.getWeatherByPostalCode(postalCode);
        assertNotNull(weatherData);
        assertTrue(weatherData.getTemperature() > -100); // Check for valid temperature value
    }

}
