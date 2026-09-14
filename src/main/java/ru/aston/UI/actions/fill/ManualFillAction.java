package ru.aston.UI.actions.fill;

import ru.aston.model.Car;
import ru.aston.io.CarInputOutput;
import ru.aston.UI.state.ApplicationContext;
import ru.aston.UI.actions.CollectionModifyingAction;

import java.util.List;
import java.util.Optional;

public class ManualFillAction extends CollectionModifyingAction {

    private int addedCount = 0;

    @Override
    protected List<Car> modify(ApplicationContext context, List<Car> existingCars) {
        System.out.println("=== MANUAL CAR INPUT ===");
        System.out.println("Enter car details. Enter 0 for power to finish.\n");

        List<Car> newCars = CarInputOutput.createCarList();

        while (true) {
            System.out.println("\n--- Car #" + (newCars.size() + 1) + " ---");

            Optional<Car> maybeCar = CarInputOutput.inputFromConsole(context.getScanner());

            if (maybeCar.isEmpty()) {
                if (askFinish(context)) {
                    break;
                } else {
                    continue;
                }
            }

            newCars.add(maybeCar.get());
            System.out.println("Car added successfully!");

            System.out.print("Add another car? (y/n): ");
            String answer = context.getScanner().nextLine().trim().toLowerCase();
            if (!answer.equals("y") && !answer.equals("yes")) {
                break;
            }
        }

        addedCount = newCars.size();

        if (!newCars.isEmpty()) {
            List<Car> allCars = CarInputOutput.copyCarList(existingCars);
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