package ru.aston.UI.actions;

import ru.aston.Car;
import ru.aston.CustomCollection.ListFactory;
import ru.aston.UI.state.ApplicationContext;

import java.util.List;

public abstract class CollectionModifyingAction implements MenuAction {

    @Override
    public final void execute(ApplicationContext context) {
        if (!validatePreconditions(context)) {
            System.out.println("Operation cancelled or cannot be performed.");
            return;
        }

        List<Car> cars = context.getCars();
        if (cars == null) {
            cars = ListFactory.create();
        }

        List<Car> modifiedCars = modify(context, cars);

        if (modifiedCars != null) {
            context.setCars(modifiedCars);

            if (context.getOriginalCars().isEmpty()) {
                context.setOriginalCars(ListFactory.create(modifiedCars));
            }

            String description = getActionDescription();
            context.saveState(description);
        }

        postExecute(context, modifiedCars != null ? modifiedCars : cars);

        displayResult(context, modifiedCars != null ? modifiedCars : cars);
    }

    protected boolean validatePreconditions(ApplicationContext context) {
        return true;
    }

    protected abstract List<Car> modify(ApplicationContext context, List<Car> cars);

    protected abstract String getActionDescription();

    protected void postExecute(ApplicationContext context, List<Car> cars) {
        // Default: do nothing
    }

    protected void displayResult(ApplicationContext context, List<Car> cars) {
        System.out.println("Operation completed. Use 'Undo' to revert.");
        System.out.println("Current collection size: " + cars.size());
    }
}