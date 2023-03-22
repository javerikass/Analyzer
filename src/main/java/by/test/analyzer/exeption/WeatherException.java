package by.test.analyzer.exeption;

public class WeatherException extends RuntimeException {

    private final int status;

    public WeatherException(String message, int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
