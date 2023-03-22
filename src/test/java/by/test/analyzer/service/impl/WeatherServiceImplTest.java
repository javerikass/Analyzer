package by.test.analyzer.service.impl;

import by.test.analyzer.entity.Weather;
import by.test.analyzer.repository.WeatherRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class WeatherServiceImplTest {

    @InjectMocks
    private WeatherServiceImpl weatherService;
    @Mock
    private WeatherRepository weatherRepository;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testGetCurrentWeather() {
        Weather expectedWeather = new Weather();
        Mockito.when(weatherRepository.findFirstByOrderByDateTimeDesc()).thenReturn(expectedWeather);

        Weather actualWeather = weatherService.getCurrentWeather();

        assertEquals(expectedWeather, actualWeather);
    }

    @Test
    void testGetAvgTemperature() {
        LocalDateTime from = LocalDateTime.of(2023, 1, 1, 0, 0, 0);
        LocalDateTime to = LocalDateTime.of(2023, 1, 31, 23, 59, 59);
        double expectedAvgTemperature = 10.0;
        Mockito.when(weatherRepository.getAverageTemperatures(from, to)).thenReturn(expectedAvgTemperature);

        double actualAvgTemperature = weatherService.getAvgTemperature(from, to);

        assertEquals(expectedAvgTemperature, actualAvgTemperature, 0.001);
    }

    @Test
    void testSaveWeather() {
        Weather weather = new Weather();
        weatherService.saveWeather(weather);

        Mockito.verify(weatherRepository, Mockito.times(1)).save(weather);
    }
}