package by.test.analyzer.service;

import by.test.analyzer.entity.Weather;

import java.time.LocalDateTime;

public interface WeatherService {

    Weather getCurrentWeather();

    double getAvgTemperature(LocalDateTime from, LocalDateTime to);

    void saveWeather(Weather weather);
}
