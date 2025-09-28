package org.example.algos;

import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class MergeSortTest {

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
        for (int i = 1; i <= 10_000; i *= 10) {
            int[] a = generateRandomArray(i);
            int[] expected = Arrays.copyOf(a, a.length);
            Arrays.sort(expected);

            Metrics metrics = new Metrics();
            MergeSort.sort(a, metrics);

            assertArrayEquals(expected, a, "MergeSort failed for size " + i);
            assertTrue(metrics.getMaxDepth() <= (int)(2 * Math.log(i) / Math.log(2)) + 10);
        }
    }

    @Test
    public void testAlreadySortedArray() {
        int[] a = new int[100];
        for (int i = 0; i < 100; i++) a[i] = i;
        int[] expected = Arrays.copyOf(a, a.length);

        Metrics metrics = new Metrics();
        MergeSort.sort(a, metrics);

        assertArrayEquals(expected, a);
    }

    @Test
    public void testReverseSortedArray() {
        int[] a = new int[100];
        for (int i = 0; i < 100; i++) a[i] = 100 - i;
        int[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);

        Metrics metrics = new Metrics();
        MergeSort.sort(a, metrics);

        assertArrayEquals(expected, a);
    }

    @Test
    public void testDuplicates() {
        int[] a = new int[]{5, 3, 5, 2, 5, 1, 5};
        int[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);

        Metrics metrics = new Metrics();
        MergeSort.sort(a, metrics);

        assertArrayEquals(expected, a);
    }
}
