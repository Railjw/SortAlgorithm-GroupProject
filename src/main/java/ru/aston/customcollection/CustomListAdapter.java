package ru.aston.customcollection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class CustomListAdapter<T> implements List<T> {    
    private static final int START_ELEMENTS_COUNT = 10;
    private CustomList<T> innerCustomList;
    
    public CustomListAdapter() {
        this.innerCustomList = new CustomList<T>();
    }
    
    public CustomListAdapter(Collection<? extends T> collection) {
        this.innerCustomList = new CustomList<T>();
        
        if (collection != null) {
            swapInnerObjectsArrayWithNew(collection.toArray(), collection.size());   
        }
    }
    
    private void swapInnerObjectsArrayWithNew(Object[] arr, int usefulElementsCount) {
        if (arr == null) {
            return;
        }
        
        Class<?> clazz = this.innerCustomList.getClass();
        
        try {
            Field elementsField = clazz.getDeclaredField("elements");
            elementsField.setAccessible(true);
            elementsField.set(this.innerCustomList, usefulElementsCount > 0 ? arr : new Object[START_ELEMENTS_COUNT]);
            elementsField.setAccessible(false);
            
            Field sizeField = clazz.getDeclaredField("size");
            sizeField.setAccessible(true);
            sizeField.setInt(this.innerCustomList, usefulElementsCount);
            sizeField.setAccessible(false);
        }
        catch(Exception exc) {
            throw new UnsupportedOperationException(exc.getMessage());
        }
    }
    
    @Override
    public int size() {
        return this.innerCustomList.size();
    }

    @Override
    public boolean isEmpty() {
        return this.innerCustomList.size() < 1;
    }

    @Override
    public boolean contains(Object o) {
        int lstSize = this.innerCustomList.size();
        
        for (int i = 0; i < lstSize; i++) {
            if (Objects.equals(o, this.innerCustomList.get(i))) {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return this.innerCustomList.iterator();
    }

    @Override
    public Object[] toArray() {
        Object[] copy = new Object[this.innerCustomList.size()];
        
        for (int i = 0; i < copy.length; i++) {
            copy[i] = this.innerCustomList.get(i);
        }
        
        return copy;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Object[] copy = toArray();

        if (a == null || a.length < this.innerCustomList.size()) {
            return (T[]) Arrays.copyOf(copy, copy.length);
        }
        
        System.arraycopy(copy, 0, a, 0, copy.length);
        
        if (a.length > copy.length) {
            a[copy.length] = null;
        }
        
        return a;
    }

    @Override
    public boolean add(T e) {
        try  {
            this.innerCustomList.add(e);
        }
        catch(Exception exc) {
            return false;
        }
        
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int size = this.innerCustomList.size();
        
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, this.innerCustomList.get(i))) {
                remove(i);
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            return false;
        }
                
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            return false;
        }
                
        for (T t : c) {
            add(t);
        }
        
        return !c.isEmpty();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c == null) {
            return false;
        }
                
        for (T t : c) {
            add(index++, t);
        }
        
        return !c.isEmpty();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            return false;
        }
                
        boolean modified = false;
        
        for (Object o : c) {
            while (remove(o)) {
                modified = true;
            }
        }
        
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            return false;
        }
        
        boolean somethingRemoved = false;
        int size = this.innerCustomList.size();
        
        for (int i = 0; i < size; i++) {
            if (!c.contains(this.innerCustomList.get(i))) {
                remove(i);
                size = this.innerCustomList.size();
                i--;
                somethingRemoved = true;
            }
        }
        
        return somethingRemoved;
    }

    @Override
    public void clear() throws UnsupportedOperationException {
        Object[] cleanedArr = new Object[START_ELEMENTS_COUNT];
        swapInnerObjectsArrayWithNew(cleanedArr, 0);
    }

    @Override
    public T get(int index) {
        return this.innerCustomList.get(index);
    }

    @Override
    public T set(int index, T element) {
        T prevElem = this.innerCustomList.get(index);        
        this.innerCustomList.set(index, element);
        
        return prevElem;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > this.innerCustomList.size()) {
            throw new IndexOutOfBoundsException();
        }
        
        if (index == 0 && this.innerCustomList.size() == 0) {
            add(element);
            return;
        }

        int originalSize = this.innerCustomList.size();
        Object[] copy = new Object[originalSize + 1];        
        int offset = 0;
        
        for (int i = 0; i < originalSize; i++)
            if (i == index) {
                copy[i] = element;
                i--;
                index = -1;
                offset++;
            }
            else {
                copy[i + offset] = this.innerCustomList.get(i);
            }
        
        swapInnerObjectsArrayWithNew(copy, copy.length);
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > this.innerCustomList.size()) {
            throw new IndexOutOfBoundsException();
        }
        
        T prevElem = this.innerCustomList.get(index);
        int originalSize = this.innerCustomList.size();
        Object[] copy = new Object[originalSize - 1];        
        int offset = 0;
        
        for (int i = 0; i < originalSize; i++)
            if (i == index) {
                offset++;
            }
            else {
                copy[i - offset] = this.innerCustomList.get(i);
            }
        
        swapInnerObjectsArrayWithNew(copy, copy.length);
        return prevElem;
    }

    @Override
    public int indexOf(Object o) {
        int size = this.innerCustomList.size();
        
        for (int i = 0; i < size; i++) {
            if (Objects.equals(o, this.innerCustomList.get(i))) {
                return i;
            }
        }
        
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int size = this.innerCustomList.size();
        
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(o, this.innerCustomList.get(i))) {
                return i;
            }
        }
        
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new CustomListAdapterIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new CustomListAdapterIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex > this.size() || toIndex < fromIndex || toIndex > this.size()) {
                throw new IndexOutOfBoundsException();
            }
        
        CustomListAdapter<T> cla = new CustomListAdapter<T>();
        
        for (int i = fromIndex; i < toIndex; i++)
            cla.add(this.innerCustomList.get(i));
        
        return cla;
    }        
    
    //--
    private class CustomListAdapterIterator implements ListIterator<T> {
        private int cursor;

        public CustomListAdapterIterator(int index) {
            if (index < 0 || index > CustomListAdapter.this.size()) {
                throw new IndexOutOfBoundsException();
            }
            
            this.cursor = index;
        }

        @Override
        public boolean hasNext() {
            return this.cursor < CustomListAdapter.this.size();
        }

        @Override
        public T next() {
            return get(cursor++);
        }

        @Override
        public boolean hasPrevious() {
            return cursor > 0;
        }

        @Override
        public T previous() {
            return get(cursor--);
        }

        @Override
        public int nextIndex() {
            return cursor + 1;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {            
            CustomListAdapter.this.remove(this.cursor);
        }

        @Override
        public void set(T e) {
            CustomListAdapter.this.set(this.cursor, e);
        }

        @Override
        public void add(T e) {
            CustomListAdapter.this.add(this.cursor, e);
        }
    }
}