package org.example.algos;

import org.example.metrics.Metrics;

public class MergeSort {

    private static final int CUTOFF = 16;

    public static void sort(int[] a, Metrics metrics) {
        int[] aux = new int[a.length];
        metrics.incrementAllocations();
        metrics.startTimer();
        sort(a, 0, a.length - 1, aux, metrics);
        metrics.stopTimer();
    }

    private static void sort(int[] a, int lo, int hi, int[] aux, Metrics metrics) {
        metrics.enterRecursion();
        if (hi - lo + 1 <= CUTOFF) {
            insertionSort(a, lo, hi, metrics);
            metrics.exitRecursion();
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid, aux, metrics);
        sort(a, mid + 1, hi, aux, metrics);
        merge(a, lo, mid, hi, aux, metrics);
        metrics.exitRecursion();
    }

    private static void merge(int[] a, int lo, int mid, int hi, int[] aux, Metrics metrics) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
            metrics.incrementAllocations();
        }

        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
                metrics.incrementAllocations();
            } else if (j > hi) {
                a[k] = aux[i++];
                metrics.incrementAllocations();
            } else {
                metrics.incrementComparisons();
                if (aux[j] < aux[i]) {
                    a[k] = aux[j++];
                    metrics.incrementAllocations();
                } else {
                    a[k] = aux[i++];
                    metrics.incrementAllocations();
                }
            }
        }
    }

    private static void insertionSort(int[] a, int lo, int hi, Metrics metrics) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i];
            metrics.incrementAllocations();
            int j = i - 1;
            while (j >= lo && a[j] > key) {
                metrics.incrementComparisons();
                a[j + 1] = a[j];
                metrics.incrementAllocations();
                j--;
            }
            if (j >= lo) metrics.incrementComparisons();
            a[j + 1] = key;
            metrics.incrementAllocations();
        }
    }
}
