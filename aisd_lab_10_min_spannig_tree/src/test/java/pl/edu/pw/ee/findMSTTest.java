package pl.edu.pw.ee;

import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

import pl.edu.pw.ee.services.MinSpanningTree;

public abstract class findMSTTest {

    private MinSpanningTree mst;

    public findMSTTest(MinSpanningTree mst) {
        this.mst = mst;
    }

    @Test(expected = RuntimeException.class)
    public void should_Return_Exception_Null() {
        mst.findMST(null);
    }

    @Test(expected = RuntimeException.class)
    public void should_Return_Exception() {
        mst.findMST(
                "extraValue.txt");
    }

    @Test(expected = RuntimeException.class)
    public void should_Return_Exception_NotExists() {
        mst.findMST(
                "niema.txt");
    }

    @Test(expected = RuntimeException.class)
    public void should_Return_Exception_twoGraphs() {
        mst.findMST("twoGraphs.xt");
    }

    @Test
    public void should_Find_MinSpanningTree_When_LargeSingleLetter_IsGiven() {
        String expected = "A_3_B|B_1_C|D_7_E|C_1_D";
        String[] input = mst
                .findMST("large.txt")
                .split("\\|");

        String[] expectedOutput = expected.split("\\|");
        Arrays.sort(input);
        Arrays.sort(expectedOutput);

        assertArrayEquals(expectedOutput, input);

    }

    @Test
    public void should_Find_MinSpanningTree_When_SmallSingleLetter_IsGiven() {
        String expected = "a_3_b|b_1_c|d_7_e|c_1_d";
        String[] input = mst
                .findMST("small.txt")
                .split("\\|");

        String[] expectedOutput = expected.split("\\|");
        Arrays.sort(input);
        Arrays.sort(expectedOutput);

        assertArrayEquals(expectedOutput, input);
    }

    @Test
    public void should_Find_MinSpanningTree_When_AkasiaString_IsGiven() {
        String expected = "Akasia_3_B|B_1_C|D_7_E|C_1_D";
        String[] input = mst
                .findMST("Akasia.txt")
                .split("\\|");

        String[] expectedOutput = expected.split("\\|");
        Arrays.sort(input);
        Arrays.sort(expectedOutput);

        assertArrayEquals(expectedOutput, input);
    }

}
