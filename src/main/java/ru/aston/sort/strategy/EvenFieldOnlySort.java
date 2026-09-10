package ru.aston.sort.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class EvenFieldOnlySort<T> implements SortStrategy<T> {
    private final SortStrategy<T> baseStrategy;
    private final ToIntFunction<T> intFieldExtractor;

    public EvenFieldOnlySort(SortStrategy<T> baseStrategy, ToIntFunction<T> intFieldExtractor) {
        this.baseStrategy = baseStrategy;
        this.intFieldExtractor = intFieldExtractor;
    }

    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        List<Integer> evenPosition = new ArrayList<>();
        List<T> evenElements = new ArrayList<>();

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
