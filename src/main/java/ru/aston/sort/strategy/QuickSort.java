package ru.aston.sort.strategy;

import java.util.Comparator;
import java.util.List;

public class QuickSort implements SortStrategy {
    private int getPivotAndReorganize(List arr, int low, int high, Comparator comparator) {
        Object pivot = arr.get(high);       
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (comparator.compare(arr.get(j), pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i + 1, high);  
        return i + 1;
    }

    private void swap(List arr, int i, int j) {
        Object temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
    
    private void quickSort(List arr, int low, int high, Comparator comparator) {
        if (low < high) {            
            int pi = getPivotAndReorganize(arr, low, high, comparator);
            
            quickSort(arr, low, pi - 1, comparator);
            quickSort(arr, pi + 1, high, comparator);
        }
    }
    
    @Override
    public void sort(List arr, Comparator comparator) {
        if (arr == null || arr.size() < 2 || comparator == null) {
            return;
        }
        
        quickSort(arr, 0, arr.size() - 1, comparator);
    }
}