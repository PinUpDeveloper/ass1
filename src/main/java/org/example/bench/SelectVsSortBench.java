package org.example.bench;

import org.example.algos.DeterministicSelect;
import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class SelectVsSortBench {

    @Param({"100", "1000", "10000"})
    int size;

    int[] data;
    int k;
    Random rand;

    @Setup(Level.Invocation)
    public void setup() {
        rand = new Random();
        data = rand.ints(size, 0, 1_000_000).toArray();
        k = rand.nextInt(size);
    }

    @Benchmark
    public int deterministicSelect() {
        int[] copy = Arrays.copyOf(data, data.length);
        return DeterministicSelect.select(copy, 0, copy.length - 1, k, null); // null → без метрик
    }

    @Benchmark
    public int fullSortThenPick() {
        int[] copy = Arrays.copyOf(data, data.length);
        Arrays.sort(copy);
        return copy[k];
    }
}
