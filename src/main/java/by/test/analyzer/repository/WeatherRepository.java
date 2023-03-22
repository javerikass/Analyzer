package by.test.analyzer.repository;

import by.test.analyzer.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Weather findFirstByOrderByDateTimeDesc();

    @Query(value = "select avg(w.temperature) from Weather w where w.dateTime BETWEEN :from AND :to")
    double getAverageTemperatures(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

}
