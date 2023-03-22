package by.test.analyzer;

import by.test.analyzer.dto.WeatherDto;
import by.test.analyzer.mapper.WeatherMapper;
import by.test.analyzer.service.WeatherService;
import by.test.analyzer.utils.WeatherUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.Objects;

@Configuration
@EnableScheduling
public class WeatherScheduler {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WeatherService weatherService;
    @Autowired
    private WeatherMapper weatherMapper;

    @Scheduled(fixedRate = WeatherUtils.UPDATE_INTERVAL)
    public void getAndSaveWeather() {
        try {
            Request request = new Request.Builder().url(WeatherUtils.API_URI).build();
            Response response = client.newCall(request).execute();
            WeatherDto weatherDto = objectMapper.readValue(Objects.requireNonNull(response.body()).string(), WeatherDto.class);
            weatherService.saveWeather(weatherMapper.convertDtoToWeather(weatherDto));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


