package ru.aston.sort.strategy;

import ru.aston.Car;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class EvenFieldOnlySort implements SortStrategy {
    private final SortStrategy baseStrategy;
    private final ToIntFunction<Car> intFieldExtractor;

    public EvenFieldOnlySort(SortStrategy baseStrategy, ToIntFunction<Car> intFieldExtractor) {
        this.baseStrategy = baseStrategy;
        this.intFieldExtractor = intFieldExtractor;
    }

    @Override
    public void sort(List<Car> list, Comparator<Car> comparator) {
        List<Integer> evenPosition = new ArrayList<>();
        List<Car> evenElements = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (intFieldExtractor.applyAsInt(list.get(i)) % 2 == 0) {
                evenPosition.add(i);
                evenElements.add(list.get(i));
            }
        }

        baseStrategy.sort(evenElements, comparator);

        for (int i = 0; i < evenElements.size(); i++) {
            list.set(evenPosition.get(i), evenElements.get(i));
        }
    }
}
