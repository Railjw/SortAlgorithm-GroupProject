package ru.aston.sort;

import ru.aston.sort.strategy.BubbleSort;
import ru.aston.sort.strategy.QuickSort;
import ru.aston.sort.strategy.SortStrategy;

public class SortStrategyFactory {
    public static <T> SortStrategy<T> create(SortAlgorithm algorithm) {
        return switch (algorithm) {
            case BUBBLE -> new BubbleSort<>();
            case QUICK -> new QuickSort<>();
            default -> throw new IllegalStateException("Unexpected value: " + algorithm);
        };
    }
}