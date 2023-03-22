package by.test.analyzer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class WeatherDto implements Serializable {

    @JsonProperty("temp_c")
    private double temperature;

    @JsonProperty("wind_mph")
    private double windSpeed;

    @JsonProperty("pressure_mb")
    private double pressure;

    @JsonProperty("humidity")
    private int humidity;

    @JsonProperty("condition")
    private String weatherConditions;

    private String location;

    @JsonProperty("localtime")
    private LocalDateTime dateTime;

    @JsonProperty("location")
    private void unpackNestedLocation(Map<String, Object> location) {
        this.location = (String) location.get("name");
        this.dateTime = LocalDateTime.parse(location.get("localtime").toString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm"));
    }

    @JsonProperty("current")
    private void unpackNestedCurrent(Map<String, Object> current) {
        this.temperature = (double) current.get("temp_c");
        this.windSpeed = (double) current.get("wind_mph");
        this.pressure = (double) current.get("pressure_mb");
        this.humidity = (int) current.get("humidity");

        Map<String, Object> condition = (Map<String, Object>) current.get("condition");
        this.weatherConditions = (String) condition.get("text");
    }
}
