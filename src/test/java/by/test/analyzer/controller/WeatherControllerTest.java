package by.test.analyzer.controller;

import by.test.analyzer.dto.WeatherDto;
import by.test.analyzer.entity.Weather;
import by.test.analyzer.mapper.WeatherMapper;
import by.test.analyzer.service.WeatherService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @InjectMocks
    private WeatherController weatherController;

    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @MockBean
    private WeatherMapper weatherMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(weatherController, weatherMapper).build();
    }

    @Test
    void testGetTemperatureData() throws Exception {
        LocalDateTime from = LocalDateTime.of(2022, 3, 1, 0, 0);
        LocalDateTime to = LocalDateTime.of(2022, 3, 31, 23, 59);
        double avgTemperature = 15.0;

        Mockito.when(weatherService.getAvgTemperature(from, to)).thenReturn(avgTemperature);

        mockMvc.perform(MockMvcRequestBuilders.get("/weather/temperature")
                        .param("from", "2022-03-01 00:00")
                        .param("to", "2022-03-31 23:59"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.averageTemp", Matchers.is(15.0)));
    }

    @Test
    void testGetCurrentWeather() throws Exception {
        Weather weather = Weather.builder()
                .id(1)
                .humidity(1)
                .windSpeed(2)
                .temperature(3)
                .weatherConditions("Cloudy")
                .pressure(4)
                .location("Minsk")
                .dateTime(LocalDateTime.parse("2023-03-21 13:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .build();

        WeatherDto weatherDto = WeatherDto.builder()
                .humidity(1)
                .windSpeed(2)
                .temperature(3)
                .weatherConditions("Cloudy")
                .pressure(4)
                .location("Minsk")
                .dateTime(LocalDateTime.parse("2023-03-21 13:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .build();

        Mockito.when(weatherService.getCurrentWeather()).thenReturn(weather);
        Mockito.when(weatherMapper.convertWeatherToDto(weather)).thenReturn(weatherDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/weather/current"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.humidity", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.wind_mph", Matchers.is(2.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temp_c", Matchers.is(3.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.condition", Matchers.is("Cloudy")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pressure_mb", Matchers.is(4.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location", Matchers.is("Minsk")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.localtime", Matchers.anything()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(7)));
    }

}
