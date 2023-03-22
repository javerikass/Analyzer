package by.test.analyzer.service.impl;

import by.test.analyzer.entity.Weather;
import by.test.analyzer.repository.WeatherRepository;
import by.test.analyzer.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public Weather getCurrentWeather() {
        return weatherRepository.findFirstByOrderByDateTimeDesc();
    }

    @Override
    public double getAvgTemperature(LocalDateTime from, LocalDateTime to) {
        return weatherRepository.getAverageTemperatures(from, to);
    }

    @Override
    public void saveWeather(Weather weather) {
        weatherRepository.save(weather);
    }
}

