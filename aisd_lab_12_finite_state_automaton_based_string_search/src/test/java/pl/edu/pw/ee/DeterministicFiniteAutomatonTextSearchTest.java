package pl.edu.pw.ee;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class DeterministicFiniteAutomatonTextSearchTest {

    public DeterministicFiniteAutomatonTextSearch dfa;

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowIllegalArgumentException_When_Null_IsGiven_() {
        dfa = new DeterministicFiniteAutomatonTextSearch(
                "ABCB");
        dfa.findFirst(null);
    }

    @Test
    public void Should_Return_MinusOne_When_Pattern_Empty() {
        dfa = new DeterministicFiniteAutomatonTextSearch(
                "");

        int actuals = dfa.findFirst("ABCBX");

        int expected = -1;
        assertEquals(expected, actuals);
    }

    @Test
    public void Should_Return_Zero_When_Pattern_IsEqual_Text() {
        dfa = new DeterministicFiniteAutomatonTextSearch(
                "ABCBX");

        int actuals = dfa.findFirst("ABCBX");

        int expected = 0;
        assertEquals(expected, actuals);
    }

    @Test
    public void Should_Return_Zero_When_Pattern_AtTheBeginning() {
        dfa = new DeterministicFiniteAutomatonTextSearch(
                "ABCBX");

        int actuals = dfa.findFirst("ABCBXXDFGHHHABCBXP");

        int expected = 0;
        assertEquals(expected, actuals);
    }

    @Test
    public void Should_Return_FirstIndex_When_Pattern_InTheMiddle() {
        dfa = new DeterministicFiniteAutomatonTextSearch(
                "ABCBX");

        int actuals = dfa.findFirst("ABCXXDFGABCBXHHHABCBP");

        int expected = 8;
        assertEquals(expected, actuals);
    }

    @Test
    public void Should_Return_FirstIndex_When_Pattern_AtTheEnd() {
        dfa = new DeterministicFiniteAutomatonTextSearch(
                "Kasia");

        int actuals = dfa.findFirst("ABCDEFGKasia");

        int expected = 7;
        assertEquals(expected, actuals);
    }

    @Test
    public void Should_Return_MinusOne_When_NoPattern_InText() {
        dfa = new DeterministicFiniteAutomatonTextSearch(
                "Kasia");

        int actuals = dfa.findFirst("ABCDEFGKasi");

        int expected = -1;
        assertEquals(expected, actuals);
    }

    @Test(expected = IllegalArgumentException.class)
    public void Should_ThrowIllegalArgumentException_When_Null_IsGiven() {
        dfa = new DeterministicFiniteAutomatonTextSearch(
                "ABCB");
        dfa.findAll(null);
    }

    @Test
    public void Should_Return_EmptyArray_When_NoPattern_InInput() {
        dfa = new DeterministicFiniteAutomatonTextSearch(
                "ABCBX");

        int[] actuals = dfa.findAll("DABCBABCB");

        int[] expected = {};
        assertArrayEquals(expected, actuals);
    }

    @Test
    public void Should_Return_OneElemArrayOf_FirstIndexes_When_OnePattern_Exists() {
        dfa = new DeterministicFiniteAutomatonTextSearch(
                "CB");

        int[] actuals = dfa.findAll("DABCXBABCB");

        int[] expected = { 8 };
        assertArrayEquals(expected, actuals);
    }

    @Test
    public void Should_Return_ArrayOf_FirstIndexes_When_Pattern_Exists() {
        dfa = new DeterministicFiniteAutomatonTextSearch(
                "ABCB");

        int[] actuals = dfa.findAll("DABCBABCB");

        int[] expected = { 1, 5 };
        assertArrayEquals(expected, actuals);
    }

    @Test
    public void Should_Return_ArrayOf_FirstIndexes_When_Pattern_Exists_() {
        dfa = new DeterministicFiniteAutomatonTextSearch(
                "ABA");

        int[] actuals = dfa.findAll("ABABABABABA");

        int[] expected = { 0, 2, 4, 6, 8 };
        assertArrayEquals(expected, actuals);
    }

    @Test
    public void Should_Return_ArrayOf_FirstIndexes_When_Pattern_WithSpecialChar() {
        dfa = new DeterministicFiniteAutomatonTextSearch(
                "AB\nA");

        int[] actuals = dfa.findAll("AB\nABABABAB\nABAAB\nA");

        int[] expected = { 0, 9, 15 };
        assertArrayEquals(expected, actuals);
    }

    @Test
    public void Should_Return_ArrayOf_FirstIndexes_When_Pattern_Exists_AllCharTheSame() {
        dfa = new DeterministicFiniteAutomatonTextSearch(
                "AAA");

        int[] actuals = dfa.findAll("AAAAAA");

        int[] expected = { 0, 1, 2, 3 };
        assertArrayEquals(expected, actuals);
    }

    @Test
    public void Should_Return_EmptyArrayOf_FirstIndexes_When_EmptyInput() {
        dfa = new DeterministicFiniteAutomatonTextSearch(
                "");

        int[] actuals = dfa.findAll("AAA");

        int[] expected = {};
        assertArrayEquals(expected, actuals);
    }

    @Test
    public void Test() {
        dfa = new DeterministicFiniteAutomatonTextSearch(
                "BBB");

        int[] actuals = dfa.findAll("BBBBBB");

        int[] expected = {0,1,2,3};
        assertArrayEquals(expected, actuals);
    }
}
