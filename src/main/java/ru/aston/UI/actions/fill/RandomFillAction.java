package ru.aston.UI.actions.fill;

import ru.aston.Car;
import ru.aston.CarInputOutput;
import ru.aston.UI.state.ApplicationContext;
import ru.aston.UI.actions.CollectionModifyingAction;

import java.util.List;

public class RandomFillAction extends CollectionModifyingAction {

    private int count = 0;

    @Override
    protected boolean validatePreconditions(ApplicationContext context) {
        try {
            System.out.print("Enter number of cars to generate (1-10000): ");
            String input = context.getScanner().nextLine().trim();
            count = Integer.parseInt(input);

            if (count <= 0 || count > 10000) {
                System.out.println("Please enter a number between 1 and 10000.");
                return false;
            }
            return true;

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please enter a valid integer.");
            return false;
        }
    }

    @Override
    protected List<Car> modify(ApplicationContext context, List<Car> cars) {
        System.out.println("Generating " + count + " random cars...");
        List<Car> generatedCars = CarInputOutput.generateRandom(count);

        if (generatedCars.isEmpty()) {
            System.out.println("Failed to generate cars.");
            return cars;
        }

        System.out.println("Successfully generated " + generatedCars.size() + " random cars!");
        return generatedCars;
    }

    @Override
    protected String getActionDescription() {
        return "Random generate: " + count + " cars";
    }

    @Override
    protected void displayResult(ApplicationContext context, List<Car> cars) {
        if (!cars.isEmpty()) {
            System.out.println("Total cars: " + cars.size());
            System.out.println("\nFirst 5 generated cars:");
            for (int i = 0; i < Math.min(5, cars.size()); i++) {
                Car car = cars.get(i);
                System.out.println("  " + (i + 1) + ". " + car.getPower() + " HP, " +
                        car.getModel() + ", " + car.getProductionYear());
            }
        }
        System.out.println("\nUse 'Undo' to revert to previous state");
    }
}