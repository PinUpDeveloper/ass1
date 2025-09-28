package org.example.metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvWriter {

    public static void writeHeader(PrintWriter writer) {
        writer.println("Algorithm,n,Time(ms),Comparisons,Allocations,MaxDepth");
    }

    public static void writeRow(PrintWriter writer, String algo, int n, Metrics metrics) {
        writer.printf("%s,%d,%.3f,%d,%d,%d%n",
                algo,
                n,
                metrics.getTimeMillis(),
                metrics.getComparisons(),
                metrics.getAllocations(),
                metrics.getMaxDepth()
        );
    }

    public static void writeAll(String filePath, String algo, List<Integer> sizes, List<Metrics> results) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writeHeader(writer);
            for (int i = 0; i < sizes.size(); i++) {
                writeRow(writer, algo, sizes.get(i), results.get(i));
            }
        } catch (IOException e) {
            System.err.println("Failed to write CSV: " + e.getMessage());
        }
    }
}
