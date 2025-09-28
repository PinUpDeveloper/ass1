# Divide and Conquer Algorithms Project

## Description
This project implements several classic algorithms, including **MergeSort**, **QuickSort**, **Deterministic Select** (Median of Medians), and the **Closest Pair of Points** in a 2D space using the divide-and-conquer strategy. Additionally, the project features **metrics** to analyze recursion depth and comparison counts while running these algorithms.

The project is implemented in Java, utilizing **JMH** for **performance benchmarking** and **JUnit** for testing.

## Algorithms

### 1. **MergeSort (D&C, Master Case 2)**
Implemented using the classic **divide and conquer** approach. A **linear buffer** is used during the merge step to improve performance.

### 2. **QuickSort (Robust)**
Uses a **randomized pivot** and recursively sorts the smaller subarray to limit recursion depth.

### 3. **Deterministic Select (Median of Medians)**
This algorithm selects a pivot based on the **median of medians**, ensuring **linear time complexity** for selecting the k-th smallest element.

### 4. **Closest Pair of Points (2D, O(n log n))**
Solves the problem of finding the closest pair of points using the **divide and conquer** approach. The points are sorted along the x and y axes, and a check is made on the **strip** using the sorted points.

---

## Project Structure

1. **Algorithms**:
    - `MergeSort.java`
    - `QuickSort.java`
    - `DeterministicSelect.java`
    - `ClosestPair.java`

2. **Metrics**:
    - `Metrics.java` — for tracking recursion depth and comparison counts.

3. **Tests**:
    - `MergeSortTest.java`
    - `QuickSortTest.java`
    - `DeterministicSelectTest.java`
    - `ClosestPairTest.java`

4. **Benchmarks**:
    - `SelectVsSortBench.java` — for benchmarking between selection algorithms and sorting.

5. **Main Class**:
    - `Main.java` — for demonstrating the execution of all algorithms.

---

## How to Use

1. **Run the Project**:

   After cloning the repository, compile the project using Maven:

    ```bash
    mvn clean install
    ```

2. **Run the Main Program**:

   To run the program that demonstrates the algorithms, use the following command:

    ```bash
    mvn exec:java -Dexec.mainClass="org.example.Main"
    ```

3. **Testing**:

   To run the tests:

    ```bash
    mvn test
    ```

4. **Benchmarking**:

   To run the JMH benchmarks:

    ```bash
    mvn clean install
    mvn exec:java -Dexec.mainClass="org.example.bench.SelectVsSortBench"
    ```

---

## Sample Output from `Main.java`

```txt
Running sample executions...
MergeSort result: [0, 1, 2, 3, 5, 6, 7, 8, 8, 9]
  Depth: 10, Comparisons: 34

QuickSort result: [0, 1, 2, 3, 5, 6, 7, 8, 8, 9]
  Depth: 8, Comparisons: 26

Select (5-th smallest): 5
  Depth: 6, Comparisons: 15

Closest pair distance: 1.4142135623730951
  Points: ((1.0, 1.0), (2.0, 2.0))
  Depth: 10
