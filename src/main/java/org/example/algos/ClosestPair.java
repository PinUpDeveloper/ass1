package org.example.algos;

import org.example.metrics.Metrics;
import org.example.util.Util;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static double findClosest(Point[] points, Metrics metrics) {
        Point[] px = points.clone();
        Point[] py = points.clone();
        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));
        metrics.addAllocations(px.length * 2); // px and py clones

        return closest(px, py, 0, px.length - 1, metrics);
    }

    private static double closest(Point[] px, Point[] py, int left, int right, Metrics metrics) {
        metrics.enterRecursion();

        if (right - left <= 3) {
            double minDist = Double.POSITIVE_INFINITY;
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    minDist = Math.min(minDist, distance(px[i], px[j], metrics));
                }
            }
            metrics.exitRecursion();
            return minDist;
        }

        int mid = (left + right) / 2;
        Point midPoint = px[mid];

        Point[] pyl = new Point[mid - left + 1];
        Point[] pyr = new Point[right - mid];
        int li = 0, ri = 0;

        for (int i = 0; i < py.length; i++) {
            if (py[i].x <= midPoint.x && li < pyl.length) {
                pyl[li++] = py[i];
            } else {
                pyr[ri++] = py[i];
            }
        }
        metrics.addAllocations(pyl.length + pyr.length);

        double dl = closest(px, pyl, left, mid, metrics);
        double dr = closest(px, pyr, mid + 1, right, metrics);

        double d = Math.min(dl, dr);

        Point[] strip = new Point[right - left + 1];
        int stripSize = 0;

        for (int i = 0; i < py.length; i++) {
            if (Math.abs(py[i].x - midPoint.x) < d) {
                strip[stripSize++] = py[i];
            }
        }

        metrics.addAllocations(stripSize);

        double minStrip = stripClosest(strip, stripSize, d, metrics);

        metrics.exitRecursion();
        return Math.min(d, minStrip);
    }

    private static double stripClosest(Point[] strip, int size, double d, Metrics metrics) {
        double min = d;

        for (int i = 0; i < size; ++i) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; ++j) {
                min = Math.min(min, distance(strip[i], strip[j], metrics));
            }
        }
        return min;
    }

    private static double distance(Point p1, Point p2, Metrics metrics) {
        metrics.incrementComparisons();
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.hypot(dx, dy);
    }

    public static class Point {
        public final double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
