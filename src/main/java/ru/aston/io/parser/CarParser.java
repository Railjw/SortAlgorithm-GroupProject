package ru.aston.io.parser;

import ru.aston.Car;
import java.util.Optional;

public class CarParser {

    private static final int EXPECTED_PARTS = 3;

    public static Optional<Car> parseLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            return Optional.empty();
        }

        String[] parts = line.split(",");
        if (parts.length != EXPECTED_PARTS) {
            System.err.println("Invalid format: " + line + " (expected " + EXPECTED_PARTS + " parts, got " + parts.length + ")");
            return Optional.empty();
        }

        try {
            String model = parts[0].trim();
            int power = Integer.parseInt(parts[1].trim());
            int year = Integer.parseInt(parts[2].trim());

            Car car = Car.builder()
                    .model(model)
                    .power(power)
                    .productionYear(year)
                    .build();

            return Optional.of(car);
        } catch (NumberFormatException e) {
            System.err.println("Number format error in: " + line);
            return Optional.empty();
        } catch (IllegalArgumentException e) {
            System.err.println("Validation error in: " + line + " - " + e.getMessage());
            return Optional.empty();
        }
    }
}