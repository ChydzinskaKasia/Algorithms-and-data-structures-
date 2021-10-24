package pl.edu.pw.ee;

import java.util.*;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Assert;
import org.junit.Before;
import pl.edu.pw.ee.services.Sorting;

public class HeapSortTest {
    private Sorting sorting;

    @Before
    public void setUp() {
        sorting = new HeapSort();
    }

    @org.junit.Rule
    public ExpectedException thrown = ExpectedException.none();

    @org.junit.Test
    public void Should_ThrowIllegalArgument_When_NullArrInput() throws Exception {
        double[] nums = null;
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Nums array cannot be null");
        sorting.sort(nums);
    }

    @org.junit.Test
    public void Should_SortedOutput_When_RandomArrayInput() {
        Random rand = new Random();
        double[] arrInput = new double[20];
        for (int i = 0; i < arrInput.length; i++) {
            arrInput[i] = rand.nextDouble();
        }
        double[] copiedArrInput = Arrays.copyOf(arrInput, arrInput.length);

        sorting.sort(arrInput);
        Arrays.sort(copiedArrInput);

        Assert.assertArrayEquals(copiedArrInput, arrInput, 0);
    }

    @Test
    public void Should_SortedOutputArray_When_PositiveArrayInput() {
        double[] arrInput = { 1.0, 3.0, 5.0, 2.0, 4.0, 8.0, 7.0, 6.0, 9.0 };

        sorting.sort(arrInput);

        double[] arrExpectedOutput = { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0 };
        Assert.assertArrayEquals(arrInput, arrExpectedOutput, 0);

    }

    @Test
    public void Should_SortedOutputArray_When_NegativeArrayInput() {

        double[] arrInput = { -1.0, -5.0, -8.0, -12.6, -56.0, -98.0 };

        sorting.sort(arrInput);

        double[] arrExpectedOutput = { -98.0, -56.0, -12.6, -8.0, -5.0, -1.0 };
        Assert.assertArrayEquals(arrInput, arrExpectedOutput, 0);
    }

    @Test
    public void Should_BeEmptyArray_When_EmptyArrayInput() {

        double[] arrInput = {};
  
        sorting.sort(arrInput);
       
        double[] arrExpectedOutput = {};
        Assert.assertArrayEquals(arrExpectedOutput, arrInput, 0);
    }
}
