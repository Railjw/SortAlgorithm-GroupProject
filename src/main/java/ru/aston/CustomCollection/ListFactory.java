package ru.aston.CustomCollection;

import java.util.Collection;
import java.util.List;

public class ListFactory {

    public enum ListType {
        ARRAY,
        LINKED

    }

    public static <T> List<T> create(ListType type) {
        return switch (type) {
            case ARRAY -> new CustomArrayList<>();
            case LINKED -> new CustomLinkedList<>();
        };
    }

    public static <T> List<T> create(int initialCapacity) {
        return new CustomArrayList<>(initialCapacity);
    }

    public static <T> List<T> create(ListType type, Collection<? extends T> collection) {
        return switch (type) {
            case ARRAY -> new CustomArrayList<>(collection);
            case LINKED -> new CustomLinkedList<>(collection);
        };
    }

    public static <T> List<T> create() {
        return new CustomArrayList<>();
    }

    public static <T> List<T> create(Collection<? extends T> collection) {
        return new CustomArrayList<>(collection);
    }
}