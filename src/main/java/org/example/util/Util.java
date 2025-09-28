package org.example.util;

import org.example.metrics.Metrics;

import java.util.Arrays;
import java.util.Random;

public class Util {

    private static final Random random = new Random();

    //Swap, Partition, Shuffle

    public static void swap(int[] a, int i, int j, Metrics metrics) {
        if (i == j) return;
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        metrics.addAllocations(3);
    }

    public static int partition(int[] a, int lo, int hi, int pivotIndex, Metrics metrics) {
        swap(a, pivotIndex, hi, metrics);
        int pivot = a[hi];
        metrics.incrementAllocations();

        int i = lo;
        for (int j = lo; j < hi; j++) {
            metrics.incrementComparisons();
            if (a[j] <= pivot) {
                swap(a, i, j, metrics);
                i++;
            }
        }
        swap(a, i, hi, metrics);
        return i;
    }

    public static void shuffle(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
    }

    //Array Utilities

    public static int[] generateRandomArray(int size, int bound) {
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = random.nextInt(bound);
        }
        return a;
    }

    public static int[] copy(int[] original) {
        return Arrays.copyOf(original, original.length);
    }

    public static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) return false;
        }
        return true;
    }

    public static int[] reverseSortedArray(int size) {
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = size - i;
        }
        return a;
    }

    public static int[] sortedArray(int size) {
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = i;
        }
        return a;
    }

    public static int[] constantArray(int size, int value) {
        int[] a = new int[size];
        Arrays.fill(a, value);
        return a;
    }
}
