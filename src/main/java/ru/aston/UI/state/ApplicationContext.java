package ru.aston.UI.state;

import ru.aston.Car;
import ru.aston.CustomCollection.ListFactory;
import ru.aston.UI.menu.MenuItem;
import ru.aston.sort.SortAlgorithm;
import ru.aston.sort.SortStrategyFactory;
import ru.aston.sort.strategy.SortStrategy;

import java.util.List;
import java.util.Scanner;

public class ApplicationContext {

    private final Scanner scanner;
    private boolean running;
    private MenuItem currentMenu;

    private List<Car> cars;
    private List<Car> originalCars;

    private SortAlgorithm currentSortAlgorithm;
    private final StateManager undoManager;

    public ApplicationContext() {
        this.scanner = new Scanner(System.in);
        this.running = true;
        this.cars = ListFactory.create();
        this.originalCars = ListFactory.create();
        this.currentSortAlgorithm = SortAlgorithm.BUBBLE;
        this.undoManager = new StateManager();

        saveState("Application started");
    }

    public Scanner getScanner() {
        return scanner;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public MenuItem getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(MenuItem currentMenu) {
        this.currentMenu = currentMenu;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getOriginalCars() {
        return originalCars;
    }

    public void setOriginalCars(List<Car> originalCars) {
        this.originalCars = originalCars;
    }

    public SortAlgorithm getCurrentSortAlgorithm() {
        return currentSortAlgorithm;
    }

    public void setCurrentSortAlgorithm(SortAlgorithm currentSortAlgorithm) {
        this.currentSortAlgorithm = currentSortAlgorithm;
    }

    public StateManager getUndoManager() {
        return undoManager;
    }

    public SortStrategy getCurrentSortStrategy() {
        return SortStrategyFactory.create(currentSortAlgorithm);
    }

    public void saveState(String actionDescription) {
        CarsStateMemento memento = new CarsStateMemento(cars, originalCars, actionDescription);
        undoManager.addState(memento);
    }

    public boolean undo() {
        CarsStateMemento memento = undoManager.undo();
        if (memento != null) {
            restoreState(memento);
            System.out.println("Undo performed: " + memento.getActionDescription());
            return true;
        } else {
            System.out.println("Nothing to undo");
            return false;
        }
    }

    public boolean redo() {
        CarsStateMemento memento = undoManager.redo();
        if (memento != null) {
            restoreState(memento);
            System.out.println("Redo performed: " + memento.getActionDescription());
            return true;
        } else {
            System.out.println("Nothing to redo");
            return false;
        }
    }

    private void restoreState(CarsStateMemento memento) {
        this.cars = memento.getCarsState();
        this.originalCars = memento.getOriginalCarsState();
    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    public void showHistory() {
        System.out.println(undoManager.getHistorySummary());
    }

    public void resetToOriginal() {
        if (originalCars != null && !originalCars.isEmpty()) {
            cars = ListFactory.create(originalCars);
            saveState("Reset to original");
            System.out.println("Collection restored to original state");
        } else {
            System.out.println("No original data to restore");
        }
    }

    public void waitForEnter() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
}