package org.example.metrics;

public class Metrics {

    private int comparisons = 0;
    private int allocations = 0;

    private int currentDepth = 0;
    private int maxRecursionDepth = 0;

    private long startTime = 0;
    private long endTime = 0;

    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementAllocations() {
        allocations++;
    }

    public void updateRecursionDepth(int depth) {
        if (depth > maxRecursionDepth) {
            maxRecursionDepth = depth;
        }
    }

    public void enterRecursion() {
        currentDepth++;
        updateRecursionDepth(currentDepth);
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

    public long getElapsedTimeNano() {
        return endTime - startTime;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getAllocations() {
        return allocations;
    }

    public int getMaxRecursionDepth() {
        return maxRecursionDepth;
    }

    public void reset() {
        comparisons = 0;
        allocations = 0;
        currentDepth = 0;
        maxRecursionDepth = 0;
        startTime = 0;
        endTime = 0;
    }
}
