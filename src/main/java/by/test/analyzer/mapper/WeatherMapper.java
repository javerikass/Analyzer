package by.test.analyzer.mapper;

import by.test.analyzer.dto.WeatherDto;
import by.test.analyzer.entity.Weather;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class WeatherMapper {

    private final ModelMapper modelMapper;

    public WeatherMapper() {
        this.modelMapper = new ModelMapper();
    }

    public WeatherDto convertWeatherToDto(Weather weather) {
        return modelMapper.map(weather, WeatherDto.class);
    }

    public Weather convertDtoToWeather(WeatherDto weatherDto) {
        return modelMapper.map(weatherDto, Weather.class);
    }

}
