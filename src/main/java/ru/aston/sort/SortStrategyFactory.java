package ru.aston.sort;

import ru.aston.sort.strategy.BubbleSort;
import ru.aston.sort.strategy.SortStrategy;

public class SortStrategyFactory {
    public static SortStrategy create(SortAlgorithm algorithm) {
        return switch (algorithm) {
            case BUBBLE -> new BubbleSort();
        };
    }
}
