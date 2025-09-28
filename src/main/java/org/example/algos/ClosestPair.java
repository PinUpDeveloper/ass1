package org.example.algos;

import org.example.metrics.Metrics;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class ClosestPair {

    public static Pair closestPair(Point[] points, Metrics metrics) {
        Point[] px = points.clone();
        Point[] py = points.clone();
        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));

        return closest(px, py, 0, points.length - 1, metrics);
    }

    private static Pair closest(Point[] px, Point[] py, int left, int right, Metrics metrics) {
        if (right - left <= 3) {
            double minDist = Double.POSITIVE_INFINITY;
            Pair closestPair = null;

            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    double dist = distance(px[i], px[j], metrics);
                    if (dist < minDist) {
                        minDist = dist;
                        closestPair = new Pair(px[i], px[j]);
                    }
                }
            }
            return closestPair;
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

        Pair closestLeft = closest(px, pyl, left, mid, metrics);
        Pair closestRight = closest(px, pyr, mid + 1, right, metrics);

        double leftDist = distance(closestLeft.p1, closestLeft.p2, metrics);
        double rightDist = distance(closestRight.p1, closestRight.p2, metrics);
        double minDist = Math.min(leftDist, rightDist);

        Pair closest = (minDist == leftDist) ? closestLeft : closestRight;

        Point[] strip = new Point[right - left + 1];
        int stripSize = 0;

        for (int i = 0; i < py.length; i++) {
            if (Math.abs(py[i].x - midPoint.x) < minDist) {
                strip[stripSize++] = py[i];
            }
        }

        Pair stripClosestPair = stripClosest(strip, stripSize, minDist, metrics);
        if (stripClosestPair != null) {
            double stripDist = distance(stripClosestPair.p1, stripClosestPair.p2, metrics);
            if (stripDist < minDist) {
                closest = stripClosestPair;
                minDist = stripDist;
            }
        }

        return closest;
    }

    private static Pair stripClosest(Point[] strip, int size, double d, Metrics metrics) {
        Pair closestPair = null;
        double minDist = d;

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < minDist; j++) {
                double dist = distance(strip[i], strip[j], metrics);
                if (dist < minDist) {
                    minDist = dist;
                    closestPair = new Pair(strip[i], strip[j]);
                }
            }
        }
        return closestPair;
    }

    private static double distance(Point p1, Point p2, Metrics metrics) {
        metrics.incrementComparisons();
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.hypot(dx, dy);
    }

    public static Point[] randomPoints(int n, int maxCoord) {
        Random rand = new Random();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = rand.nextInt(maxCoord);
            int y = rand.nextInt(maxCoord);
            points[i] = new Point(x, y);
        }
        return points;
    }

    public static class Point {
        public final double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public static class Pair {
        public final Point p1, p2;

        public Pair(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }

        public double dist() {
            return Math.hypot(p1.x - p2.x, p1.y - p2.y);
        }

        @Override
        public String toString() {
            return "(" + p1 + ", " + p2 + ")";
        }
    }
}
