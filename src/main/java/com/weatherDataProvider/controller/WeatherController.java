package com.weatherDataProvider.controller;

import com.weatherDataProvider.apiResponse.CommonResponse;
import com.weatherDataProvider.entity.WeatherData;
import com.weatherDataProvider.entity.WeatherRequest;
import com.weatherDataProvider.exceptionHandler.CustomExceptionHandler;
import com.weatherDataProvider.repository.WeatherRequestRepository;
import com.weatherDataProvider.services.UserService;
import com.weatherDataProvider.services.WeatherService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/weather")
@Validated
public class WeatherController {
    private final WeatherService weatherService;
    private final WeatherRequestRepository weatherRequestRepository;

    private final UserService userService;

    @Autowired
    public WeatherController(WeatherService weatherService, WeatherRequestRepository weatherRequestRepository, UserService userService) {
        this.weatherService = weatherService;
        this.weatherRequestRepository = weatherRequestRepository;
        this.userService = userService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("Weather application is running");
    }

    @PostMapping("/fetch")
    public ResponseEntity<String> fetchWeather(@RequestParam @Pattern(regexp = "\\d{5}", message = "Invalid postal code")
                                               String postalCode, @RequestParam
                                               @NotBlank(message = "User name cannot be empty.") String userName) {
        try {
            boolean userStatus = userService.checkUserStatus(userName);
            if (userStatus) {
                WeatherData weatherData = weatherService.getWeatherByPostalCode(postalCode);

                WeatherRequest weatherRequest = new WeatherRequest();
                weatherRequest.setPostalCode(postalCode);
                weatherRequest.setUserName(userName);
                weatherRequest.setTimestamp(LocalDateTime.now());
                weatherRequest.setTemperature(weatherData.getTemperature());
                weatherRequest.setHumidity(weatherData.getHumidity());
                weatherRequest.setWeatherCondition(weatherData.getWeatherCondition());
                weatherRequestRepository.save(weatherRequest);
                return ResponseEntity.ok("Weather data fetched successfully!");
            } else {
                return ResponseEntity.ok(String.format("User %s is currently deactivated", userName));
            }


        } catch (CustomExceptionHandler ex) {
            new CustomExceptionHandler(String.format("Error fetching weather data: %s", ex.getMessage()));
            return ResponseEntity.status(500).body("Error fetching weather data: " + ex.getMessage());
        } catch (Exception e) {
            new CustomExceptionHandler(String.format("Invalid request: %s", e.getMessage()));
            return ResponseEntity.status(400).body("Invalid request: " + e.getMessage());
        }

    }

    @GetMapping("/historyByPostalCode/{postalCode}")
    public ResponseEntity<CommonResponse> getWeatherResponseByPostalCode(@PathVariable("postalCode") @Pattern(regexp = "\\d{5}", message = "Invalid postal code") String postalCode) throws CustomExceptionHandler {
        try {
            CommonResponse response = null;
            List<WeatherData> dataList = new ArrayList<>();
            List<WeatherRequest> weatherRequest = weatherRequestRepository.findAllByPostalCode(postalCode);
            if(weatherRequest.size()>0){
                weatherRequest.stream().forEach(data->{
                    WeatherData weatherData = new WeatherData();
                    weatherData.setWeatherCondition(data.getWeatherCondition());
                    weatherData.setHumidity(data.getHumidity());
                    weatherData.setTemperature(data.getTemperature());
                    dataList.add(weatherData);
                });
                response = new CommonResponse(dataList);
            }else{
                response = new CommonResponse(new String(String.format("No any record found for postal code %s in database",postalCode)));
            }
            return ResponseEntity.ok(response);
        } catch (CustomExceptionHandler ex) {
            new CustomExceptionHandler(String.format("Error for getting data from DB for PostalCode:- %s, error message %s", postalCode, ex.getMessage()));
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/historyByUser/{user}")
    public ResponseEntity<CommonResponse> getWeatherDataByUser(@PathVariable("user") String user) {
        try {
            CommonResponse response = null;
            boolean isUserActive = userService.checkUserStatus(user);
            if (isUserActive) {
                List<WeatherRequest> weatherRequest = weatherRequestRepository.findAllByUserName(user);
                List<WeatherData> dataList = new ArrayList<>();
                weatherRequest.stream().forEach(data -> {
                    WeatherData weatherData = new WeatherData();
                    weatherData.setWeatherCondition(data.getWeatherCondition());
                    weatherData.setHumidity(data.getHumidity());
                    weatherData.setTemperature(data.getTemperature());
                    dataList.add(weatherData);
                });
                response = new CommonResponse(dataList);
            } else {
                response = new CommonResponse(new String(String.format("Given %s user is not active", user)));
            }
            return ResponseEntity.ok(response);

        } catch (CustomExceptionHandler ex) {
            new CustomExceptionHandler(String.format("Error for getting data from DB for user:- %s, error message %s", user, ex.getMessage()));
            return ResponseEntity.status(404).body(null);
        }
    }

}