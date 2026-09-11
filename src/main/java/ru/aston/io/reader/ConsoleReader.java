package ru.aston.io.reader;

import ru.aston.Car;
import ru.aston.CustomList;
import ru.aston.io.parser.CarParser;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleReader {

    public static Car readOne(Scanner scanner) {
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

    public static CustomList<Car> readMultiple(Scanner scanner, int count) {
        System.out.println("Enter " + count + " cars in format: Model, Power, Year");
        System.out.println("Example: BMW X5, 340, 2023");

        CustomList<Car> cars = new CustomList<>();
        for (int i = 0; i < count; i++) {
            System.out.print("Car #" + (i + 1) + ": ");
            CarParser.parseLine(scanner.nextLine()).ifPresent(cars::add);
        }
        return cars;
    }

    public static CustomList<Car> readOneByOne(Scanner scanner, int count) {
        System.out.println("Enter data for " + count + " cars:");

        CustomList<Car> cars = new CustomList<>();
        for (int i = 0; i < count; i++) {
            System.out.println("\n-- Car #" + (i + 1) + " --");
            cars.add(readOne(scanner));
        }
        return cars;
    }
}