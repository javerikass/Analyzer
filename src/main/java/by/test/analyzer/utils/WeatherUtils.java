package by.test.analyzer.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WeatherUtils {

    public static final String API_KEY = "29e0e83028cd4a66a72132702231803";
    public static final String CITY = "Minsk";
    public static final String API_URI = "https://api.weatherapi.com/v1/current.json?key=" + API_KEY + "&q=" + CITY;
    public static final int UPDATE_INTERVAL = 600000;

}
