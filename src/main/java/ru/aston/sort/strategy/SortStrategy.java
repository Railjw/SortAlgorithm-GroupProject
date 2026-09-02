package ru.aston.sort.strategy;

import ru.aston.Car;

import java.util.Comparator;
import java.util.List;

public interface SortStrategy {
    void sort(List<Car> list, Comparator<Car> comparator);
}
