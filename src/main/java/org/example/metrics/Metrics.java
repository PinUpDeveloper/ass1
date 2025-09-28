package org.example.metrics;

public class Metrics {
    private long comparisons = 0;
    private long allocations = 0;
    private int currentDepth = 0;
    private int maxDepth = 0;
    private long startTime = 0;
    private long endTime = 0;

    public void reset() {
        comparisons = 0;
        allocations = 0;
        currentDepth = 0;
        maxDepth = 0;
        startTime = 0;
        endTime = 0;
    }

    public void incrementComparisons() {
        comparisons++;
    }

    public void addComparisons(long count) {
        comparisons += count;
    }

    public void incrementAllocations() {
        allocations++;
    }

    public void addAllocations(long count) {
        allocations += count;
    }

    public void enterRecursion() {
        currentDepth++;
        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void exitRecursion() {
        currentDepth--;
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
    }

    public long getTimeNanos() {
        return endTime - startTime;
    }

    public double getTimeMillis() {
        return (endTime - startTime) / 1_000_000.0;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getAllocations() {
        return allocations;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    @Override
    public String toString() {
        return "Time(ms): " + getTimeMillis() +
                ", Comparisons: " + comparisons +
                ", Allocations: " + allocations +
                ", MaxDepth: " + maxDepth;
    }
}
