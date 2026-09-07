package ru.aston.sort;

import ru.aston.sort.strategy.BubbleSort;
import ru.aston.sort.strategy.QuickSort;
import ru.aston.sort.strategy.SortStrategy;

public class SortStrategyFactory {
    public static SortStrategy create(SortAlgorithm algorithm) {
        return switch (algorithm) {
            case SortAlgorithm.BUBBLE -> new BubbleSort();
            case SortAlgorithm.QUICK -> new QuickSort();
            default -> throw new IllegalStateException("Unexpected value: " + algorithm);
        };
    }
}