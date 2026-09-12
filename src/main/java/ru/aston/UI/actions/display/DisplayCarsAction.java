package ru.aston.UI.actions.display;

import ru.aston.UI.state.ApplicationContext;
import ru.aston.UI.actions.MenuAction;
import ru.aston.io.CarInputOutput;
import ru.aston.model.Car;

import java.util.List;

public class DisplayCarsAction implements MenuAction {

    private static final int DEFAULT_LIMIT = 20;

    @Override
    public void execute(ApplicationContext context) {
        List<Car> cars = context.getCars();

        if (cars == null || cars.isEmpty()) {
            System.out.println("Collection is empty! Nothing to display.");
            return;
        }

        System.out.println("\n=== DISPLAY CARS ===");
        System.out.println("Collection size: " + cars.size());
        System.out.println("\nChoose display mode:");
        System.out.println("1. Show first 10 cars");
        System.out.println("2. Show first 20 cars");
        System.out.println("3. Show all cars");
        System.out.println("4. Show custom range");
        System.out.print("\nYour choice: ");

        try {
            int choice = Integer.parseInt(context.getScanner().nextLine().trim());

            switch (choice) {
                case 1:
                    System.out.println("\nFirst 10 cars:");
                    CarInputOutput.print(cars, 10);
                    break;
                case 2:
                    System.out.println("\nFirst 20 cars:");
                    CarInputOutput.print(cars, 20);
                    break;
                case 3:
                    System.out.println("\nAll cars:");
                    CarInputOutput.print(cars);
                    break;
                case 4:
                    showCustomRange(cars, context);
                    break;
                default:
                    System.out.println("Invalid choice. Showing first 10 cars:");
                    CarInputOutput.print(cars, 10);
            }

            System.out.println("\nTotal cars: " + cars.size());

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Showing first 10 cars:");
            CarInputOutput.print(cars, 10);
        }
    }

    private void showCustomRange(List<Car> cars, ApplicationContext context) {
        try {
            System.out.print("Enter start index (0-based): ");
            int startIndex = Integer.parseInt(context.getScanner().nextLine().trim());

            System.out.print("Enter end index (exclusive): ");
            int endIndex = Integer.parseInt(context.getScanner().nextLine().trim());

            if (startIndex < 0 || endIndex > cars.size() || startIndex >= endIndex) {
                System.out.println("Invalid range. Showing first 10 cars instead:");
                CarInputOutput.print(cars, 10);
                return;
            }

            System.out.println("\nCars from index " + startIndex + " to " + endIndex + ":");
            CarInputOutput.print(cars, startIndex, endIndex);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Showing first 10 cars:");
            CarInputOutput.print(cars, 10);
        }
    }
}