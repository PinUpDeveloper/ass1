package org.example.algos;

import org.example.metrics.Metrics;

import java.util.Random;

public class QuickSort {

    private static final Random random = new Random();

    public static void sort(int[] a, Metrics metrics) {
        metrics.startTimer();
        quickSort(a, 0, a.length - 1, metrics);
        metrics.stopTimer();
    }

    private static void quickSort(int[] a, int lo, int hi, Metrics metrics) {
        while (lo < hi) {
            metrics.enterRecursion();

            // Randomized pivot selection
            int pivotIndex = lo + random.nextInt(hi - lo + 1);
            swap(a, pivotIndex, hi, metrics);

            int p = partition(a, lo, hi, metrics);

            // Recurse on smaller part to bound stack depth
            if (p - lo < hi - p) {
                quickSort(a, lo, p - 1, metrics);
                lo = p + 1;
            } else {
                quickSort(a, p + 1, hi, metrics);
                hi = p - 1;
            }

            metrics.exitRecursion();
        }
    }

    private static int partition(int[] a, int lo, int hi, Metrics metrics) {
        int pivot = a[hi];
        metrics.incrementAllocations();

        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            metrics.incrementComparisons();
            if (a[j] <= pivot) {
                i++;
                swap(a, i, j, metrics);
            }
        }
        swap(a, i + 1, hi, metrics);
        return i + 1;
    }

    private static void swap(int[] a, int i, int j, Metrics metrics) {
        if (i == j) return;
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        metrics.addAllocations(3);
    }
}
