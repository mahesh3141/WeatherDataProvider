package com.weatherDataProvider.services;

import com.weatherDataProvider.apiResponse.WeatherApiResponse;
import com.weatherDataProvider.entity.WeatherData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.base.url}")
    private String BASE_URL;
    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherData getWeatherByPostalCode(String postalCode) {
        String url = BASE_URL+"?zip=" + postalCode + "&appid=" + apiKey + "&units=metric";

        try {
            WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);
            if (response != null) {
                return new WeatherData(response.getMain().getTemp(), response.getMain().getHumidity(), response.getWeather().get(0).getDescription());
            }
        } catch (Exception e) {
            // Handle error
            throw new RuntimeException("Failed to fetch weather data", e);
        }
        return null;
    }


}
