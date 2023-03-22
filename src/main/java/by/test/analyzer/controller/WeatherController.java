package by.test.analyzer.controller;

import by.test.analyzer.controller.response.WeatherResponse;
import by.test.analyzer.dto.WeatherDto;
import by.test.analyzer.entity.Weather;
import by.test.analyzer.exeption.WeatherException;
import by.test.analyzer.mapper.WeatherMapper;
import by.test.analyzer.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;
    @Autowired
    private WeatherMapper weatherMapper;

    @GetMapping("/current")
    public ResponseEntity<WeatherDto> getCurrentWeather() {
        Weather weather = weatherService.getCurrentWeather();
        if (weather == null) {
            throw new WeatherException("Failed to get current weather", HttpStatus.NOT_FOUND.value());
        }
        return ResponseEntity.ok(weatherMapper.convertWeatherToDto(weather));
    }

    @GetMapping("/temperature")
    public ResponseEntity<WeatherResponse> getTemperatureData(@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime from,
                                                              @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime to) {
        try {
            double avgTemperature = weatherService.getAvgTemperature(from, to);
            return ResponseEntity.ok(WeatherResponse.builder().averageTemp(avgTemperature).build());
        } catch (Exception e) {
            throw new WeatherException("Failed to get average weather", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

}
