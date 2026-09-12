package ru.aston.UI.actions.fill;

import ru.aston.model.Car;
import ru.aston.io.CarInputOutput;
import ru.aston.CustomCollection.ListFactory;
import ru.aston.UI.state.ApplicationContext;
import ru.aston.UI.actions.CollectionModifyingAction;

import java.util.List;

public class ManualFillAction extends CollectionModifyingAction {

    private int addedCount = 0;

    @Override
    protected List<Car> modify(ApplicationContext context, List<Car> existingCars) {
        System.out.println("=== MANUAL CAR INPUT ===");
        System.out.println("Enter car details. Enter 0 for power to finish.\n");

        List<Car> newCars = ListFactory.create(ListFactory.ListType.LINKED);

        while (true) {
            System.out.println("\n--- Car #" + (newCars.size() + 1) + " ---");

            try {
                System.out.print("Enter power (or 0 to finish): ");
                String powerCheck = context.getScanner().nextLine().trim();

                if (powerCheck.isEmpty() || powerCheck.equals("0")) {
                    if (askFinish(context)) break;
                    continue;
                }

                Car car = CarInputOutput.inputFromConsole(context.getScanner());
                newCars.add(car);
                System.out.println("Car added successfully!");

                System.out.print("Add another car? (y/n): ");
                String answer = context.getScanner().nextLine().trim().toLowerCase();
                if (!answer.equals("y") && !answer.equals("yes")) {
                    break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Validation error: " + e.getMessage());
                System.out.println("Please try again.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        addedCount = newCars.size();

        if (!newCars.isEmpty()) {
            List<Car> allCars = ListFactory.create(existingCars);
            allCars.addAll(newCars);
            return allCars;
        }

        System.out.println("No cars were added.");
        return existingCars;
    }

    private boolean askFinish(ApplicationContext context) {
        System.out.print("Finish input? (y/n): ");
        String answer = context.getScanner().nextLine().trim().toLowerCase();
        return answer.equals("y") || answer.equals("yes");
    }

    @Override
    protected String getActionDescription() {
        return "Manual input: " + addedCount + " cars";
    }

    @Override
    protected void displayResult(ApplicationContext context, List<Car> cars) {
        if (addedCount > 0) {
            System.out.println("\nSuccessfully added " + addedCount + " cars!");
            System.out.println("  Total cars: " + cars.size());
        }
        System.out.println("\nUse 'Undo' to revert to previous state");
    }
}