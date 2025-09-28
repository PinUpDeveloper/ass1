package org.example.algos;

import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DeterministicSelectTest {

    private final Random rand = new Random(42);

    @Test
    public void testSelectMatchesSort() {
        for (int trial = 0; trial < 100; trial++) {
            int size = rand.nextInt(1000) + 1;
            int[] arr = rand.ints(size, 0, 10000).toArray();
            int[] copy = Arrays.copyOf(arr, arr.length);

            int k = rand.nextInt(size);
            int expected = getKthSorted(copy, k);
            Metrics metrics = new Metrics();

            int actual = DeterministicSelect.select(arr, 0, arr.length - 1, k, metrics);
            assertEquals(expected, actual,
                    "Mismatch at trial " + trial + " for k=" + k + ", size=" + size);
        }
    }

    @Test
    public void testSmallArrays() {
        int[] arr = {5};
        assertEquals(5, DeterministicSelect.select(arr, 0, 0, 0, new Metrics()));

        int[] arr2 = {7, 2};
        assertEquals(2, DeterministicSelect.select(arr2, 0, 1, 0, new Metrics()));
        assertEquals(7, DeterministicSelect.select(arr2, 0, 1, 1, new Metrics()));
    }

    @Test
    public void testDuplicates() {
        int[] arr = {5, 3, 5, 3, 5, 3};
        assertEquals(3, DeterministicSelect.select(arr.clone(), 0, arr.length - 1, 0, new Metrics()));
        assertEquals(3, DeterministicSelect.select(arr.clone(), 0, arr.length - 1, 1, new Metrics()));
        assertEquals(3, DeterministicSelect.select(arr.clone(), 0, arr.length - 1, 2, new Metrics()));
        assertEquals(5, DeterministicSelect.select(arr.clone(), 0, arr.length - 1, 3, new Metrics()));
    }

    private int getKthSorted(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[k];
    }
}
