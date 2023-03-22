package by.test.analyzer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(schema = "weather_storage")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double temperature;
    private double windSpeed;
    private double pressure;
    private int humidity;
    private String weatherConditions;
    private String location;
    private LocalDateTime dateTime;
}
