package com.weatherDataProvider.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "weatherReport")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postalCode;
    private String userName;
    private LocalDateTime timestamp;
    private double temperature;
    private double humidity;
    private String weatherCondition;
}
