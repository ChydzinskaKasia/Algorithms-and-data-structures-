package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class RbtMapTest {

    private RbtMap<Integer, Integer> testIntRbtMap;
    private RbtMap<String, String> testStrRbtMap;

    @Before
    public void setUp() {
        testIntRbtMap = new RbtMap<>();
        testStrRbtMap = new RbtMap<>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetValueNullInteger() {

        testIntRbtMap.setValue(10, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetKeyNullInteger() {

        testIntRbtMap.setValue(null, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetKeyNullString() {

        testStrRbtMap.setValue(null, "10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetValueNullString() {

        testStrRbtMap.setValue("8", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBothValueNull() {

        testStrRbtMap.setValue(null, null);
    }

    @Test
    public void testSetValueOneInteger() {
        Integer expectedValue = 3;

        testIntRbtMap.setValue(2, 3);
        Integer key = 2;

        assertEquals(testIntRbtMap.getValue(key), expectedValue);

    }

    @Test
    public void testSetValueOneString() {
        String expectedString = "3";

        testStrRbtMap.setValue("2", "3");
        String key = "2";

        assertEquals(testStrRbtMap.getValue(key), expectedString);
    }

    @Test
    public void testSetValueFewInteger() {
        Integer expectedValue = 43;

        testIntRbtMap.setValue(2, 13);
        testIntRbtMap.setValue(4, 23);
        testIntRbtMap.setValue(7, 33);
        testIntRbtMap.setValue(1, 43);
        testIntRbtMap.setValue(6, 3);
        Integer key = 1;

        assertEquals(testIntRbtMap.getValue(key), expectedValue);

    }

    @Test
    public void testGetValueNull() {
        String expectedString = null;

        testStrRbtMap.setValue("J", "o");
        testStrRbtMap.setValue("A", "l");
        testStrRbtMap.setValue("V", "e");
        testStrRbtMap.setValue("A", "k");

        String strValue = testStrRbtMap.getValue("K");

        assertEquals(expectedString, strValue);
    }

    @Test
    public void testGetValueString() {
        String expectedString = "k";

        testStrRbtMap.setValue("J", "o");
        testStrRbtMap.setValue("A", "l");
        testStrRbtMap.setValue("V", "e");
        testStrRbtMap.setValue("A", "k");

        String strValue = testStrRbtMap.getValue("A");

        assertEquals(expectedString, strValue);
    }
}
