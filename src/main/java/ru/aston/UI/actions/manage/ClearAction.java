package ru.aston.UI.actions.manage;

import ru.aston.Car;
import ru.aston.CustomCollection.ListFactory;
import ru.aston.UI.state.ApplicationContext;
import ru.aston.UI.actions.CollectionModifyingAction;

import java.util.List;

public class ClearAction extends CollectionModifyingAction {

    private boolean confirmed = false;

    @Override
    protected boolean validatePreconditions(ApplicationContext context) {
        if (context.getCars().isEmpty()) {
            System.out.println("Collection is already empty.");
            return false;
        }

        System.out.print("Are you sure you want to clear the collection? (y/n): ");
        String answer = context.getScanner().nextLine().trim().toLowerCase();

        if (answer.equals("y") || answer.equals("yes")) {
            confirmed = true;
            return true;
        } else {
            System.out.println("Operation cancelled.");
            return false;
        }
    }

    @Override
    protected List<Car> modify(ApplicationContext context, List<Car> cars) {
        System.out.println("Clearing collection...");
        return ListFactory.create();
    }

    @Override
    protected String getActionDescription() {
        return "Clear collection";
    }

    @Override
    protected void displayResult(ApplicationContext context, List<Car> cars) {
        System.out.println("Collection cleared.");
        System.out.println("Use 'Undo' to restore the collection");
    }
}