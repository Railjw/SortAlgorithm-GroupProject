package ru.aston.sort;

import ru.aston.Car;

import java.util.Comparator;

public class ComparatorFactory {
    public static Comparator<Car> create(SortField field) {
        return switch (field) {
            case POWER -> Comparator.comparingInt(Car::getPower);
            case MODEL -> Comparator.comparing(Car::getModel);
            case PRODUCTION_YEAR -> Comparator.comparingInt(Car::getProductionYear);
        };
    }
}
