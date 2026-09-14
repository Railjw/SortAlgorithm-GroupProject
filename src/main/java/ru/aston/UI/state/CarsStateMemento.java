package ru.aston.UI.state;

import ru.aston.model.Car;
import ru.aston.CustomCollection.ListFactory;

import java.util.List;

public class CarsStateMemento {
    private final List<Car> carsState;
    private final List<Car> originalCarsState;
    private final String actionDescription;
    private final long timestamp;

    public CarsStateMemento(List<Car> cars, List<Car> originalCars, String actionDescription) {
        this.carsState = cars != null ? ListFactory.create(cars) : ListFactory.create();
        this.originalCarsState = originalCars != null ? ListFactory.create(originalCars) : ListFactory.create();
        this.actionDescription = actionDescription;
        this.timestamp = System.currentTimeMillis();
    }

    public List<Car> getCarsState() {
        return ListFactory.create(carsState);
    }

    public List<Car> getOriginalCarsState() {
        return ListFactory.create(originalCarsState);
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (cars: %d)",
                new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date(timestamp)),
                actionDescription,
                carsState.size());
    }
}