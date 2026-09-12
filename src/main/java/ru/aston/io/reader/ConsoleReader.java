package ru.aston.io.reader;

import ru.aston.model.Car;

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
}