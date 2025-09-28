package org.example.algos;

import org.example.algos.ClosestPair.Point;
import org.example.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ClosestPairTest {

    @Test
    public void testTinyPoints() {
        Point[] points = {
                new Point(0, 0),
                new Point(1, 0),
                new Point(2, 0)
        };
        Metrics m = new Metrics();
        double d = ClosestPair.findClosest(points, m);
        assertEquals(1.0, d, 1e-6);
    }

    @Test
    public void testDuplicatePoints() {
        Point[] points = {
                new Point(1, 1),
                new Point(1, 1),
                new Point(2, 2)
        };
        Metrics m = new Metrics();
        double d = ClosestPair.findClosest(points, m);
        assertEquals(0.0, d, 1e-6);
    }

    @Test
    public void testCompareToBruteForce() {
        Random rand = new Random(42);
        int n = 100;
        Point[] points = new Point[n];

        for (int i = 0; i < n; i++) {
            points[i] = new Point(rand.nextDouble() * 1000, rand.nextDouble() * 1000);
        }

        Metrics m = new Metrics();
        double dFast = ClosestPair.findClosest(points, m);
        double dNaive = bruteForce(points);

        assertEquals(dNaive, dFast, 1e-6);
    }

    private double bruteForce(Point[] points) {
        double minDist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dx = points[i].x - points[j].x;
                double dy = points[i].y - points[j].y;
                double dist = Math.hypot(dx, dy);
                minDist = Math.min(minDist, dist);
            }
        }
        return minDist;
    }

    @Test
    public void testLinearPoints() {
        Point[] points = new Point[100];
        for (int i = 0; i < 100; i++) {
            points[i] = new Point(i * 10, 0);
        }
        Metrics m = new Metrics();
        double d = ClosestPair.findClosest(points, m);
        assertEquals(10.0, d, 1e-6);
    }
}
