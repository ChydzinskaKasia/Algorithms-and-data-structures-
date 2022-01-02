package pl.edu.pw.ee;

import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class PrimAlgorithmTest {

    @Test(expected = RuntimeException.class)
    public void Should_Return_Exception() {
        new PrimAlgorithm().findMST("extraValue.txt");
    }

    @Test(expected = RuntimeException.class)
    public void Should_Return_Exception2() {
        new PrimAlgorithm().findMST("twoGraphs.xt");
    }

    @Test
    public void Should_Find_MinSpanningTree_When_LargeSingleLetter_IsGiven_() {
        String expected = "A_3_B|B_1_C|D_7_E|C_1_D";
        String[] input = new PrimAlgorithm().findMST("LargeLetters.txt").split("\\|");
        
        String[] expectedOutput = expected.split("\\|");
        Arrays.sort(input);
        Arrays.sort(expectedOutput);

        assertArrayEquals(expectedOutput, input);

    }

    @Test
    public void Should_Find_MinSpanningTree_When_LargeSingleLetter_IsGiven() {
        String expected = "a_3_b|b_1_c|d_7_e|c_1_d";
        String[] input = new PrimAlgorithm().findMST("SmallLetters.txt").split("\\|");
        
        String[] expectedOutput = expected.split("\\|");
        Arrays.sort(input);
        Arrays.sort(expectedOutput);

        assertArrayEquals(expectedOutput, input);
    }

    @Test
    public void Should_Find_MinSpanningTree_When_Akasia_IsGiven() {
        String expected = "Akasia_3_B|B_1_C|D_7_E|C_1_D";
        String[] input = new PrimAlgorithm().findMST("Akasia.txt").split("\\|");
        
        String[] expectedOutput = expected.split("\\|");
        Arrays.sort(input);
        Arrays.sort(expectedOutput);

        assertArrayEquals(expectedOutput, input);
    }

    

}
