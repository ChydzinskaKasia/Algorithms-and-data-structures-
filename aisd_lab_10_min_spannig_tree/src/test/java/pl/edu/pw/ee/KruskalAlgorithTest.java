package pl.edu.pw.ee;

import org.junit.Test;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class KruskalAlgorithTest {
    @Test(expected = RuntimeException.class)
    public void Should_Return_Exception() {
        new PrimAlgorithm().findMST(
                "C:/Users/Kasia/Algorytmy_repo/2021Z_AISD_lab_git_aisd_2021_3_gr3/aisd_lab_10_min_spannig_tree/extraValue.txt");
    }

    @Test(expected = RuntimeException.class)
    public void Should_Return_Exception2() {
        new PrimAlgorithm()
                .findMST(
                        "C:/Users/Kasia/Algorytmy_repo/2021Z_AISD_lab_git_aisd_2021_3_gr3/aisd_lab_10_min_spannig_tree/twoGraphs.xt");
    }

    @Test
    public void Should_Find_MinSpanningTree_When_LargeSingleLetter_IsGiven() {
        String expected = "A_3_B|B_1_C|D_7_E|C_1_D";
        String[] input = new PrimAlgorithm()
                .findMST(
                        "C:/Users/Kasia/Algorytmy_repo/2021Z_AISD_lab_git_aisd_2021_3_gr3/aisd_lab_10_min_spannig_tree/large.txt")
                .split("\\|");

        String[] expectedOutput = expected.split("\\|");
        Arrays.sort(input);
        Arrays.sort(expectedOutput);

        assertArrayEquals(expectedOutput, input);

    }

    @Test
    public void Should_Find_MinSpanningTree_When_SmallSingleLetter_IsGiven() {
        String expected = "a_3_b|b_1_c|d_7_e|c_1_d";
        String[] input = new PrimAlgorithm()
                .findMST(
                        "C:/Users/Kasia/Algorytmy_repo/2021Z_AISD_lab_git_aisd_2021_3_gr3/aisd_lab_10_min_spannig_tree/small.txt")
                .split("\\|");

        String[] expectedOutput = expected.split("\\|");
        Arrays.sort(input);
        Arrays.sort(expectedOutput);

        assertArrayEquals(expectedOutput, input);
    }

    @Test
    public void Should_Find_MinSpanningTree_When_AkasiaString_IsGiven() {
        String expected = "Akasia_3_B|B_1_C|D_7_E|C_1_D";
        String[] input = new PrimAlgorithm()
                .findMST(
                        "C:/Users/Kasia/Algorytmy_repo/2021Z_AISD_lab_git_aisd_2021_3_gr3/aisd_lab_10_min_spannig_tree/Akasia.txt")
                .split("\\|");

        String[] expectedOutput = expected.split("\\|");
        Arrays.sort(input);
        Arrays.sort(expectedOutput);

        assertArrayEquals(expectedOutput, input);
    }

}
