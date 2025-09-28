package org.example.cli;

import org.example.algos.*;
import org.example.algos.ClosestPair.Point;
import org.example.metrics.Metrics;
import org.example.util.Util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CLI {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java -jar app.jar [mergesort|quicksort|select|closest] <size> <seed>");
            return;
        }

        String algo = args[0];
        int size = Integer.parseInt(args[1]);
        long seed = Long.parseLong(args[2]);
        Random rand = new Random(seed);

        Metrics metrics = new Metrics();

        switch (algo.toLowerCase()) {
            case "mergesort" -> runMergeSort(size, rand, metrics);
            case "quicksort" -> runQuickSort(size, rand, metrics);
            case "select"    -> runSelect(size, rand, metrics);
            case "closest"   -> runClosestPair(size, rand, metrics);
            default -> System.out.println("Unknown algorithm: " + algo);
        }
    }

    private static void runMergeSort(int size, Random rand, Metrics metrics) {
        int[] arr = rand.ints(size, 0, size * 10).toArray();
        metrics.startTimer();
        MergeSort.sort(arr, metrics);
        metrics.stopTimer();
        writeCSV("mergesort", size, metrics);
    }

    private static void runQuickSort(int size, Random rand, Metrics metrics) {
        int[] arr = rand.ints(size, 0, size * 10).toArray();
        metrics.startTimer();
        QuickSort.sort(arr, metrics);
        metrics.stopTimer();
        writeCSV("quicksort", size, metrics);
    }

    private static void runSelect(int size, Random rand, Metrics metrics) {
        int[] arr = rand.ints(size, 0, size * 10).toArray();
        int k = size / 2;
        metrics.startTimer();
        int result = DeterministicSelect.select(arr, 0, arr.length - 1, k, metrics);
        metrics.stopTimer();
        writeCSV("select", size, metrics);
    }

    private static void runClosestPair(int size, Random rand, Metrics metrics) {
        Point[] points = new Point[size];
        for (int i = 0; i < size; i++) {
            points[i] = new Point(rand.nextDouble() * 1000, rand.nextDouble() * 1000);
        }
        metrics.startTimer();
        double result = ClosestPair.findClosest(points, metrics);
        metrics.stopTimer();
        writeCSV("closest", size, metrics);
    }

    private static void writeCSV(String algo, int size, Metrics m) {
        String filename = "output/" + algo + ".csv";
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(String.format("%d,%d,%d,%d\n",
                    size,
                    m.getElapsedTimeNano(),
                    m.getComparisons(),
                    m.getMaxRecursionDepth()));
            System.out.println("Saved: " + filename);
        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
    }
}
