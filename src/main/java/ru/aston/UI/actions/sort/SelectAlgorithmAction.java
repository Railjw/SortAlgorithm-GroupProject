package ru.aston.UI.actions.sort;

import ru.aston.UI.state.ApplicationContext;
import ru.aston.UI.actions.MenuAction;
import ru.aston.sort.SortAlgorithm;

public class SelectAlgorithmAction implements MenuAction {

    @Override
    public void execute(ApplicationContext context) {
        System.out.println("\n=== SELECT SORTING ALGORITHM ===");
        System.out.println("Current algorithm: " + context.getCurrentSortAlgorithm());
        System.out.println("\nAvailable algorithms:");

        SortAlgorithm[] algorithms = SortAlgorithm.values();
        for (int i = 0; i < algorithms.length; i++) {
            String marker = (algorithms[i] == context.getCurrentSortAlgorithm()) ? " (current)" : "";
            System.out.println("  " + (i + 1) + ". " + algorithms[i] + marker);
        }

        System.out.println("\n0. Back");
        System.out.print("\nYour choice: ");

        try {
            int choice = Integer.parseInt(context.getScanner().nextLine().trim());

            if (choice == 0) {
                return;
            }

            if (choice > 0 && choice <= algorithms.length) {
                SortAlgorithm selected = algorithms[choice - 1];
                context.setCurrentSortAlgorithm(selected);
                System.out.println("Algorithm changed to: " + selected);
            } else {
                System.out.println("Invalid choice.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a number.");
        }
    }
}