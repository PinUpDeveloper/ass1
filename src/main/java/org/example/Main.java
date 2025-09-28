package org.example;

import org.example.algos.*;
import org.example.metrics.Metrics;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running sample executions...");

        int[] array = new Random().ints(20, 0, 100).toArray();
        int[] copy;

        copy = Arrays.copyOf(array, array.length);
        Metrics m1 = new Metrics();
        MergeSort.sort(copy, m1);
        System.out.println("MergeSort result: " + Arrays.toString(copy));
        System.out.println("  Depth: " + m1.getMaxRecursionDepth() + ", Comparisons: " + m1.getComparisons());

        copy = Arrays.copyOf(array, array.length);
        Metrics m2 = new Metrics();
        QuickSort.sort(copy, m2);
        System.out.println("QuickSort result: " + Arrays.toString(copy));
        System.out.println("  Depth: " + m2.getMaxRecursionDepth() + ", Comparisons: " + m2.getComparisons());

        int k = array.length / 2;
        Metrics m3 = new Metrics();
        int kth = DeterministicSelect.select(Arrays.copyOf(array, array.length), 0, array.length - 1, k, m3);
        System.out.println("Select (" + k + "-th smallest): " + kth);
        System.out.println("  Depth: " + m3.getMaxRecursionDepth() + ", Comparisons: " + m3.getComparisons());

        ClosestPair.Point[] points = ClosestPair.randomPoints(20, 1000);
        Metrics m4 = new Metrics();
        ClosestPair.Pair closest = ClosestPair.closestPair(points, m4);
        System.out.println("Closest pair distance: " + closest.dist());
        System.out.println("  Points: " + closest);
        System.out.println("  Depth: " + m4.getMaxRecursionDepth());
    }
}
