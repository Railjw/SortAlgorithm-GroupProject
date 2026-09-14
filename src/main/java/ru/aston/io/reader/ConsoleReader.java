package ru.aston.io.reader;

import ru.aston.model.Car;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleReader {

    public static Optional<Car> readOne(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter power (hp) or 0 to finish: ");
                if (!scanner.hasNextLine()) {
                    return Optional.empty();
                }
                String powerLine = scanner.nextLine().trim();

                if (powerLine.isEmpty()) {
                    System.out.println("Power cannot be empty. Try again.");
                    continue;
                }

                int power;
                try {
                    power = Integer.parseInt(powerLine);
                } catch (NumberFormatException e) {
                    System.out.println("Error: enter a number. Try again.");
                    continue;
                }

                if (power == 0) {
                    return Optional.empty();
                }

                System.out.print("Enter model: ");
                String model = scanner.nextLine().trim();

                System.out.print("Enter production year: ");
                String yearLine = scanner.nextLine().trim();

                int year;
                try {
                    year = Integer.parseInt(yearLine);
                } catch (NumberFormatException e) {
                    System.out.println("Error: year must be a number. Try again.");
                    continue;
                }

                Car car = Car.builder()
                        .power(power)
                        .model(model)
                        .productionYear(year)
                        .build();

                return Optional.of(car);

            } catch (IllegalArgumentException e) {
                System.err.println("Validation error: " + e.getMessage() + ". Try again.");
            } catch (InputMismatchException e) {
                System.err.println("Error: enter a number. Try again.");
                scanner.nextLine();
            }
        }
    }
}