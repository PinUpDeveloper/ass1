package org.example.algos;

import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {

    private static final Random random = new Random();

    private int[] generateRandomArray(int size) {
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = random.nextInt(1_000_000);
        }
        return a;
    }

    @Test
    public void testRandomArrays() {
        for (int size : new int[]{10, 100, 1_000, 10_000}) {
            int[] a = generateRandomArray(size);
            int[] expected = Arrays.copyOf(a, a.length);
            Arrays.sort(expected);

            Metrics metrics = new Metrics();
            QuickSort.sort(a, metrics);

            assertArrayEquals(expected, a, "Failed on size: " + size);
            int logn = (int) (Math.log(size) / Math.log(2));
            assertTrue(metrics.getMaxRecursionDepth() <= 2 * logn + 10, "Stack depth too high");
        }
    }

    @Test
    public void testSortedArray() {
        int[] a = new int[100];
        for (int i = 0; i < 100; i++) a[i] = i;

        int[] expected = Arrays.copyOf(a, a.length);
        Metrics metrics = new Metrics();
        QuickSort.sort(a, metrics);

        assertArrayEquals(expected, a);
    }

    @Test
    public void testReverseSortedArray() {
        int[] a = new int[100];
        for (int i = 0; i < 100; i++) a[i] = 100 - i;

        int[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);

        Metrics metrics = new Metrics();
        QuickSort.sort(a, metrics);

        assertArrayEquals(expected, a);
    }

    @Test
    public void testDuplicates() {
        int[] a = {5, 3, 3, 5, 2, 2, 5, 1, 1};
        int[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);

        Metrics metrics = new Metrics();
        QuickSort.sort(a, metrics);

        assertArrayEquals(expected, a);
    }
}
