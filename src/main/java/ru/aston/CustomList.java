package ru.aston;

import java.util.Iterator;
import java.util.function.Supplier;

public class CustomList<T> implements Iterable<T> {
    private Object[] elements;
    private int size = 0;

    public CustomList() {
        this.elements = new Object[10];
    }

    public void add(T element) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }

    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        elements[index] = element;
    }

    public int size() {
        return size;
    }

    private void resize() {
        Object[] newElements = new Object[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }
    public static <E> CustomList<E> generate(Supplier<E> supplier, int count) {
        CustomList<E> list = new CustomList<>();
        java.util.stream.Stream.generate(supplier)
                .limit(count)
                .forEach(list::add);
        return list;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;
            @Override
            public boolean hasNext() { return cursor < size; }
            @SuppressWarnings("unchecked")
            @Override
            public T next() { return (T) elements[cursor++]; }
        };
    }
}