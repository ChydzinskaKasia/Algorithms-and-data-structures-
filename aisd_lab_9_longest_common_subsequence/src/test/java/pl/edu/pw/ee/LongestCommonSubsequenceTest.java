package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class LongestCommonSubsequenceTest {

    public LongestCommonSubsequence lcs;

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowIllegalArgumentException_When_FirstStrin_Is_Null() {
        String firstString = null;
        String secondString = "KOMPOTY";

        lcs = new LongestCommonSubsequence(firstString, secondString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_ThrowIllegalArgumentException_When_SecondStrin_Is_Null() {
        String firstString = "LOKOMOTYWA";
        String secondString = null;

        lcs = new LongestCommonSubsequence(firstString, secondString);
    }

    @Test
    public void should_Return_LongestCommonSbsequence_When_TwoString_AreGiven() {
        String firstString = "LOKOMOTYWA";
        String secondString = "KOMPOTY";

        lcs = new LongestCommonSubsequence(firstString, secondString);

        String expectedString = "KOMOTY";
        String actualString = lcs.findLCS();

        assertEquals(expectedString, actualString);
    }

    @Test
    public void should_Return_LongestCommonSbsequence_When_TwoString_AreGiven_ISODExample() {
        String firstString = "często_z_odkrywaniem";
        String secondString = "rzeczy_nie_trzeba\n_się_spieszyć";

        lcs = new LongestCommonSubsequence(firstString, secondString);

        String expectedString = "cz__raie";
        String actualString = lcs.findLCS();

        assertEquals(expectedString, actualString);
    }

    @Test
    public void shouldDisplayTable_When_TwoStrings_AreGiven() {
        String firstString = "KINOL";
        String secondString = "INL";

        lcs = new LongestCommonSubsequence(firstString, secondString);
        lcs.display();

    }

}
