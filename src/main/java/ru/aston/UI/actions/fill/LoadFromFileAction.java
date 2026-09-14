package ru.aston.UI.actions.fill;

import ru.aston.model.Car;
import ru.aston.io.CarInputOutput;
import ru.aston.UI.state.ApplicationContext;
import ru.aston.UI.actions.CollectionModifyingAction;

import java.io.IOException;
import java.util.List;

public class LoadFromFileAction extends CollectionModifyingAction {

    private static final String DEFAULT_FILE_PATH =
            "src/main/resources/data.txt";

    private String filePath;
    private int loadedCount = 0;

    @Override
    protected boolean validatePreconditions(ApplicationContext context) {
        System.out.print("Enter file path (press Enter for default): ");
        System.out.println("  Default: " + DEFAULT_FILE_PATH);
        System.out.print("Your choice: ");

        String input = context.getScanner().nextLine().trim();

        if (input.isEmpty()) {
            filePath = DEFAULT_FILE_PATH;
            System.out.println("Using default path: " + DEFAULT_FILE_PATH);
        } else {
            filePath = input;
        }

        return true;
    }

    @Override
    protected List<Car> modify(ApplicationContext context, List<Car> cars) {
        try {
            List<Car> loadedCars = CarInputOutput.readFromFile(filePath);
            loadedCount = loadedCars.size();

            if (loadedCars.isEmpty()) {
                System.out.println("File is empty or contains no valid data.");
                return cars;
            }

            System.out.println("Successfully loaded " + loadedCars.size() +
                    " cars from file: " + filePath);
            return loadedCars;

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.err.println("Please check that the file exists and is readable.");
            return cars;
        }
    }

    @Override
    protected String getActionDescription() {
        return "Load from file: " + filePath;
    }

    @Override
    protected void displayResult(ApplicationContext context, List<Car> cars) {
        if (loadedCount > 0) {
            System.out.println("Total cars in collection: " + cars.size());
            System.out.println("Use 'Undo' to revert to previous state");
        }
    }
}