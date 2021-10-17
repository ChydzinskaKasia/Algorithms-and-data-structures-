package pl.edu.pw.ee;

import org.junit.*;
import pl.edu.pw.ee.services.Sorting;

public class InsertionSortTest {
    Sorting sorting = new InsertionSort();

    @org.junit.Test
    public void Should_BeEmptyArray_When_EmptyArrayInput() {

        double[] arrInput = {};
        double[] arrExpectedOutput = {};

        sorting.sort(arrInput);
        Assert.assertArrayEquals(arrExpectedOutput, arrInput, 0);

    }

    @org.junit.Test
    public void Should_SameOutputAsInput_When_OneElementArrayInput() {

        double[] arrInput = { 5.0 };
        double[] arrExpectedOutput = { 5.0 };

        sorting.sort(arrInput);
        Assert.assertArrayEquals(arrExpectedOutput, arrInput, 0);

    }

    @org.junit.Test
    public void Should_SortedOutputArray_When_PositiveArrayInput() {

        double[] arrInput = { 5.0, 1.0, 0.5, 0.1, 0.1, 80.0 };
        double[] arrExpectedOutput = { 0.1, 0.1, 0.5, 1.0, 5.0, 80.0 };

        sorting.sort(arrInput);
        Assert.assertArrayEquals(arrExpectedOutput, arrInput, 0);

    }

    @org.junit.Test
    public void Should_SortedOutputArray_When_NegativeArrayInput() {

        double[] arrInput = { -5.0, -1.0, -0.5, -0.1, -0.1, -80.0 };
        double[] arrExpectedOutput = { -80.0, -5.0, -1.0, -0.5, -0.1, -0.1 };

        sorting.sort(arrInput);
        Assert.assertArrayEquals(arrExpectedOutput, arrInput, 0);

    }

    @org.junit.Test
    public void Should_ZerosArrayOutputArray_When_ZerosArrayInput() {

        double[] arrInput = { 0.0, 0.0, 0.0, 0.0 };
        double[] arrExpectedOutput = { 0.0, 0.0, 0.0, 0.0 };

        sorting.sort(arrInput);
        Assert.assertArrayEquals(arrExpectedOutput, arrInput, 0);

    }

}
