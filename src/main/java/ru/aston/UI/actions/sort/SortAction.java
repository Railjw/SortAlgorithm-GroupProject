package ru.aston.UI.actions.sort;

import ru.aston.Car;
import ru.aston.UI.state.ApplicationContext;
import ru.aston.UI.actions.CollectionModifyingAction;
import ru.aston.sort.ComparatorFactory;
import ru.aston.sort.SortField;
import ru.aston.sort.strategy.EvenFieldOnlySort;
import ru.aston.sort.strategy.SortStrategy;

import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class SortAction extends CollectionModifyingAction {

    private final SortField field;
    private final boolean specialMode;
    private long executionTime;

    public SortAction(SortField field, boolean specialMode) {
        this.field = field;
        this.specialMode = specialMode;
    }

    public SortAction(SortField field) {
        this(field, false);
    }

    @Override
    protected boolean validatePreconditions(ApplicationContext context) {
        List<Car> cars = context.getCars();
        if (cars == null || cars.isEmpty()) {
            System.out.println("Collection is empty! Please fill it first.");
            return false;
        }
        return true;
    }

    @Override
    protected List<Car> modify(ApplicationContext context, List<Car> cars) {

        String fieldName = getFieldName();
        String modeInfo = specialMode ? " (even/odd)" : "";
        System.out.println("Sorting " + cars.size() + " cars by " + fieldName + modeInfo + "...");

        try {
            SortStrategy baseStrategy = context.getCurrentSortStrategy();
            Comparator<Car> comparator = ComparatorFactory.create(
                    SortField.valueOf(field.name())
            );

            System.out.println("Algorithm: " + context.getCurrentSortAlgorithm());

            long startTime = System.currentTimeMillis();

            if (specialMode) {
                System.out.println("Special mode: sorting only even values");

                SortStrategy strategy = new EvenFieldOnlySort(
                        baseStrategy,
                        getIntFieldExtractor()
                );

                strategy.sort(cars, comparator);
            } else {
                baseStrategy.sort(cars, comparator);
            }

            executionTime = System.currentTimeMillis() - startTime;

            System.out.println("Sorting completed in " + executionTime + " ms!");

        } catch (Exception e) {
            System.err.println("Sorting error: " + e.getMessage());
        }

        return cars;
    }

    @Override
    protected String getActionDescription() {
        return "Sort by " + getFieldName() + (specialMode ? " (even/odd)" : "");
    }

    @Override
    protected void displayResult(ApplicationContext context, List<Car> cars) {
        System.out.println("Use 'Undo' to revert to previous state");

        if (!cars.isEmpty()) {
            System.out.println("\nFirst 5 sorted cars:");
            for (int i = 0; i < Math.min(5, cars.size()); i++) {
                Car car = cars.get(i);
                System.out.println("  " + (i + 1) + ". " + formatCar(car));
            }
        }
    }

    private String getFieldName() {
        return switch (field) {
            case POWER -> "power";
            case MODEL -> "model";
            case PRODUCTION_YEAR -> "year";
        };
    }

    private String formatCar(Car car) {
        return String.format("%d HP, %s, %d",
                car.getPower(), car.getModel(), car.getProductionYear());
    }

    private ToIntFunction<Car> getIntFieldExtractor() {
        return switch (field) {
            case POWER -> Car::getPower;
            case PRODUCTION_YEAR -> Car::getProductionYear;
            case MODEL -> car -> car.getModel().length();
        };
    }
}