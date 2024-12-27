package com.weatherDataProvider.repository;

import com.weatherDataProvider.entity.WeatherRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherRequestRepository extends JpaRepository<WeatherRequest, Long> {
    WeatherRequest findByPostalCode(String postalCode);
    List<WeatherRequest> findAllByPostalCode(String postalCode);
    WeatherRequest findByUserName(String user);

    List<WeatherRequest> findAllByUserName(String userName);
}
