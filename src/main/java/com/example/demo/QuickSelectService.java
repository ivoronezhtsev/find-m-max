package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class QuickSelectService {

    public QuickSelectService(ExcelNumbersReader excelNumbersReader) {
        this.excelNumbersReader = excelNumbersReader;
    }

    private final ExcelNumbersReader excelNumbersReader;

    public int findNthMax(String filePath, int n) {
        int[] array = excelNumbersReader.readNumbers(filePath);
        if (n < 1 || n > array.length) {
            throw new IllegalArgumentException("N must be between 1 and the size of the array.");
        }
        return quickSelect(array, 0, array.length - 1, n);
    }

    private int quickSelect(int[] array, int left, int right, int n) {
        if (left == right) {
            return array[left];
        }
        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1);
        pivotIndex = partition(array, left, right, pivotIndex);

        // Count the number of elements greater than the pivot
        int count = pivotIndex - left + 1; // Number of elements in the left partition

        if (count == n) {
            return array[pivotIndex]; // Found the nth max
        } else if (count > n) {
            return quickSelect(array, left, pivotIndex - 1, n); // Search in the left partition
        } else {
            return quickSelect(array, pivotIndex + 1, right, n - count); // Search in the right partition
        }
    }

    private int partition(int[] array, int left, int right, int pivotIndex) {
        int pivotValue = array[pivotIndex];
        swap(array, pivotIndex, right); // Move pivot to end
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (array[i] > pivotValue) { // We want the largest elements
                swap(array, storeIndex, i);
                storeIndex++;
            }
        }
        swap(array, storeIndex, right); // Move pivot to its final place
        return storeIndex; // Return the index of the pivot
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

