package com.weatherDataProvider.apiResponse;

import lombok.Data;

import java.util.List;
@Data
public class WeatherApiResponse {

    private Main main;
    private List<Weather> weather;

    @Data
    public static class Main {
        private double temp;
        private double humidity;

    }
    @Data
    public static class Weather {
        private String description;

    }
}
