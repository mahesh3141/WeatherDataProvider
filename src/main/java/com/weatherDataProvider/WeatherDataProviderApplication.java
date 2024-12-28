package com.weatherDataProvider;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Weather Data Provider",
                description = "Provide the current weather data on the basis of postal code",
                version = "${project.version}",
                contact = @Contact(
                        name = "Mahesh Nawale",
                        email = "nawale.mahesh85@gmail.com"
                )
        )


)
@SpringBootApplication
public class WeatherDataProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherDataProviderApplication.class, args);
    }

}
