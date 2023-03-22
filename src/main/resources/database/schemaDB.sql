CREATE SCHEMA IF NOT EXISTS weather_storage;

CREATE TABLE IF NOT EXISTS weather_storage.weather
(
    id                 BIGSERIAL,
    temperature        FLOAT8,
    wind_speed         FLOAT8,
    pressure           FLOAT8,
    humidity           FLOAT8,
    weather_conditions VARCHAR(255),
    location           VARCHAR(255),
    date_time          TIMESTAMP,
    CONSTRAINT WeatherPK PRIMARY KEY (id)
);