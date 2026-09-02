package ru.aston.sort.strategy;

import ru.aston.Car;

import java.util.Comparator;
import java.util.List;

public class BubbleSort implements SortStrategy {
    @Override
    public void sort(List<Car> list, Comparator<Car> comparator) {
        for (int i = 0; i < list.size() - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    Car temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
}
