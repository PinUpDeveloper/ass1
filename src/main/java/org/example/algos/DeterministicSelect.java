package org.example.algos;

import org.example.metrics.Metrics;
import org.example.util.Util;

public class DeterministicSelect {

    public static int select(int[] arr, int left, int right, int k, Metrics metrics) {
        if (metrics != null) metrics.updateRecursionDepth(1);
        if (left == right) return arr[left];

        int pivot = medianOfMedians(arr, left, right, metrics);
        int pivotIndex = Util.partition(arr, left, right, pivot, metrics);
        int rank = pivotIndex - left;

        if (k == rank) {
            return arr[pivotIndex];
        } else if (k < rank) {
            return select(arr, left, pivotIndex - 1, k, metrics);
        } else {
            return select(arr, pivotIndex + 1, right, k - rank - 1, metrics);
        }
    }

    private static int medianOfMedians(int[] arr, int left, int right, Metrics metrics) {
        int n = right - left + 1;
        int numMedians = (n + 4) / 5;
        int[] medians = new int[numMedians];
        if (metrics != null) metrics.addAllocations(numMedians);

        for (int i = 0; i < numMedians; i++) {
            int subLeft = left + i * 5;
            int subRight = Math.min(subLeft + 4, right);
            insertionSort(arr, subLeft, subRight, metrics);
            int medianIndex = subLeft + (subRight - subLeft) / 2;
            medians[i] = arr[medianIndex];
        }

        return select(medians, 0, numMedians - 1, numMedians / 2, metrics);
    }

    private static void insertionSort(int[] arr, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && compare(arr[j], key, metrics) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static int compare(int a, int b, Metrics metrics) {
        if (metrics != null) metrics.incrementComparisons();
        return Integer.compare(a, b);
    }
}
