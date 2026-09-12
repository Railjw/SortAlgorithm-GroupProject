package ru.aston.UI.actions.display;

import ru.aston.UI.state.ApplicationContext;
import ru.aston.UI.actions.MenuAction;
import ru.aston.io.CarInputOutput;
import ru.aston.model.Car;

import java.io.IOException;
import java.util.List;

public class SaveToFileAction implements MenuAction {

    private static final String DEFAULT_FILE_PATH =
            "src/main/resources/data.txt";

    @Override
    public void execute(ApplicationContext context) {
        List<Car> cars = context.getCars();

        if (cars == null || cars.isEmpty()) {
            System.out.println("Collection is empty! Nothing to save.");
            return;
        }

        System.out.println("\n=== SAVE CARS TO FILE ===");
        System.out.println("Collection size: " + cars.size());
        System.out.println("\nChoose save mode:");
        System.out.println("1. Save all cars (overwrite)");
        System.out.println("2. Append all cars with comment");
        System.out.println("3. Save first N cars");
        System.out.println("4. Save custom range");
        System.out.print("\nYour choice: ");

        try {
            int choice = Integer.parseInt(context.getScanner().nextLine().trim());

            switch (choice) {
                case 1:
                    saveAllCars(cars, context);
                    break;
                case 2:
                    appendAllCars(cars, context);
                    break;
                case 3:
                    saveWithLimit(cars, context);
                    break;
                case 4:
                    saveCustomRange(cars, context);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    private String promptFileName(ApplicationContext context) {
        System.out.print("Enter file name (press Enter for default): ");
        System.out.println("  Default: " + DEFAULT_FILE_PATH);
        System.out.print("Your choice: ");

        String fileName = context.getScanner().nextLine().trim();

        if (fileName.isEmpty()) {
            System.out.println("Using default path: " + DEFAULT_FILE_PATH);
            return DEFAULT_FILE_PATH;
        }

        return fileName;
    }

    private void saveAllCars(List<Car> cars, ApplicationContext context) {
        String fileName = promptFileName(context);

        try {
            CarInputOutput.write(fileName, cars);
            System.out.println("Successfully saved " + cars.size() + " cars to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    private void appendAllCars(List<Car> cars, ApplicationContext context) {
        String fileName = promptFileName(context);

        System.out.print("Enter comment: ");
        String comment = context.getScanner().nextLine().trim();

        try {
            CarInputOutput.append(fileName, cars, comment);
            System.out.println("Successfully appended " + cars.size() + " cars to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error appending to file: " + e.getMessage());
        }
    }

    private void saveWithLimit(List<Car> cars, ApplicationContext context) {
        String fileName = promptFileName(context);

        try {
            System.out.print("Enter number of cars to save: ");
            int limit = Integer.parseInt(context.getScanner().nextLine().trim());

            if (limit <= 0 || limit > cars.size()) {
                System.out.println("Invalid limit. Must be between 1 and " + cars.size());
                return;
            }

            CarInputOutput.write(fileName, cars, limit);
            System.out.println("Successfully saved " + limit + " cars to file: " + fileName);

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    private void saveCustomRange(List<Car> cars, ApplicationContext context) {
        String fileName = promptFileName(context);

        try {
            System.out.print("Enter start index (0-based): ");
            int startIndex = Integer.parseInt(context.getScanner().nextLine().trim());

            System.out.print("Enter end index (exclusive): ");
            int endIndex = Integer.parseInt(context.getScanner().nextLine().trim());

            if (startIndex < 0 || endIndex > cars.size() || startIndex >= endIndex) {
                System.out.println("Invalid range. Must be between 0 and " + cars.size());
                return;
            }

            CarInputOutput.write(fileName, cars, startIndex, endIndex);
            System.out.println("Successfully saved cars from index " + startIndex +
                    " to " + endIndex + " to file: " + fileName);

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }
}