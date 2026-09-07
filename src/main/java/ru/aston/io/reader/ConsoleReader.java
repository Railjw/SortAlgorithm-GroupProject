package ru.aston.io.reader;

import ru.aston.Car;
import ru.aston.CustomList;
import ru.aston.io.parser.CarParser;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

public class ConsoleReader {

    private static final Scanner scanner = new Scanner(System.in);

    public static Car readOne() {
        while (true) {
            try {
                System.out.print("Enter power (hp): ");
                int power = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Enter model: ");
                String model = scanner.nextLine();

                System.out.print("Enter production year: ");
                int year = scanner.nextInt();
                scanner.nextLine();

                return Car.builder()
                        .power(power)
                        .model(model)
                        .productionYear(year)
                        .build();

            } catch (IllegalArgumentException e) {
                System.err.println("Validation error: " + e.getMessage() + ". Try again.");
            } catch (InputMismatchException e) {
                System.err.println("Error: enter a number. Try again.");
                scanner.nextLine();
            }
        }
    }

    public static CustomList<Car> readMultiple(int count) {
        System.out.println("Enter " + count + " cars in format: Model, Power, Year");
        System.out.println("Example: BMW X5, 340, 2023");

        CustomList<Car> cars = new CustomList<>();
        Stream.generate(() -> {
                    System.out.print("Car #: ");
                    String line = scanner.nextLine();
                    return CarParser.parseLine(line).orElse(null);
                })
                .limit(count)
                .filter(Objects::nonNull)
                .forEach(cars::add);

        return cars;
    }

    public static CustomList<Car> readOneByOne(int count) {
        System.out.println("Enter data for " + count + " cars:");

        CustomList<Car> cars = new CustomList<>();
        Stream.generate(() -> {
                    System.out.println("\n-- Car #" + (cars.size() + 1) + " --");
                    return readOne();
                })
                .limit(count)
                .filter(Objects::nonNull)
                .forEach(cars::add);

        return cars;
    }
}