package pl.edu.pw.ee;
import org.junit.rules.ExpectedException;
import java.util.*;
import org.junit.*;
import pl.edu.pw.ee.services.Sorting;
import java.util.Random;

public class InsertionSortTest {
    Sorting sorting = new InsertionSort();

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
    }

    @org.junit.Test
    public void Should_EmptyArray_When_EmptyArrayInput() {

        double[] arrInput = {};
        double[] arrExpectedOutput = {};

        sorting.sort(arrInput);
        Assert.assertArrayEquals(arrExpectedOutput, arrInput, 0);

    }

    @org.junit.Test
    public void Should_SameOutputAsInput_When_RandArrayInput() {

        // double x = 0.0;
        // double y = 100.0;
        Random rand = new Random(15071998);
        double[] arrInput = new double[50];
        for (int i = 0; i < arrInput.length; i++) {
            arrInput[i] = rand.nextDouble();// *(y-x)+x;
        }

        double[] copiedArrInput = Arrays.copyOf(arrInput, arrInput.length);

        sorting.sort(arrInput);
        Arrays.sort(copiedArrInput);

        Assert.assertArrayEquals(copiedArrInput, arrInput, 0);

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

    @org.junit.Test
    public void Should_SortedOutputArray_When_OptimisticArrayInput() {

        double[] arrInput = { 1.0, 2.5, 50.0, 520.0, 722.6 };
        double[] arrExpectedOutput = { 1.0, 2.5, 50.0, 520.0, 722.6 };

        sorting.sort(arrInput);
        Assert.assertArrayEquals(arrExpectedOutput, arrInput, 0);

    }

    @org.junit.Test
    public void Should_SortedOutputArray_When_PessimisticArrayInput() {

        double[] arrInput = { 361.0, 263.5, 150.0, 20.0, 2.6 };
        double[] arrExpectedOutput = { 2.6, 20.0, 150.0, 263.5, 361.0 };

        sorting.sort(arrInput);
        Assert.assertArrayEquals(arrExpectedOutput, arrInput, 0);

    }

}
