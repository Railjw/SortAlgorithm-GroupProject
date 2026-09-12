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
            return Optional.empty();
        }

        try {
            return Optional.of(Car.builder()
                    .model(parts[0].trim())
                    .power(Integer.parseInt(parts[1].trim()))
                    .productionYear(Integer.parseInt(parts[2].trim()))
                    .build());
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}