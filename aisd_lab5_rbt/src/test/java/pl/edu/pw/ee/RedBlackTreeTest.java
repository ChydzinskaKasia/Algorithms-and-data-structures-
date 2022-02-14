package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class RedBlackTreeTest {

    private RedBlackTree<Integer, String> redBlackTree;

    @Before
    public void setUp() {
        redBlackTree = new RedBlackTree<>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutNullKey() {

        redBlackTree.put(null, "K");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNullKey() {

        redBlackTree.get(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutNullValue() {

        redBlackTree.put(10, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutNullKeyAndValue() {

        redBlackTree.put(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteMax() {

        redBlackTree.deleteMax();
    }

    @Test
    public void Should_Add_OneElement_ToTree_When_OneElement_Input() {
        String expectedString = "linux";

        redBlackTree.put(2, "linux");
        String strValue = redBlackTree.get(2);

        assertEquals(expectedString, strValue);
    }

    @Test
    public void Should_Add_Few_Element_ToTree_When_Few_Element_Input() {
        String expectedString = "linux";

        redBlackTree.put(2, "linux1");
        redBlackTree.put(3, "linux2");
        redBlackTree.put(4, "linux");
        redBlackTree.put(7, "linux3");
        redBlackTree.put(5, "linux4");
        String strValue = redBlackTree.get(4);

        assertEquals(expectedString, strValue);
    }

    @Test
    public void testGetValue() {
        String expectedString = "linux";

        redBlackTree.put(8, "slowo");
        redBlackTree.put(12, "linux");
        redBlackTree.put(3, "slowo3");

        String strValue = redBlackTree.get(12);

        assertEquals(expectedString, strValue);
    }

    @Test
    public void testNonExistingKey() {
        String expectedString = null;

        redBlackTree.put(1, "linux");
        redBlackTree.put(3, "linux");
        redBlackTree.put(8, "linux");
        redBlackTree.put(9, "linux");

        String strValue = redBlackTree.get(100);

        assertEquals(expectedString, strValue);
    }

    @Test
    public void Should_Return_String_PreOrder() {
        String expectedString = "8:H 4:D 2:B 1:A 3:C 6:F 5:E 7:G 12:L 10:J 9:I 11:K 13:M";
        redBlackTree.put(1, "A");
        redBlackTree.put(2, "B");
        redBlackTree.put(3, "C");
        redBlackTree.put(4, "D");
        redBlackTree.put(5, "E");
        redBlackTree.put(6, "F");
        redBlackTree.put(7, "G");
        redBlackTree.put(8, "H");
        redBlackTree.put(9, "I");
        redBlackTree.put(10, "J");
        redBlackTree.put(11, "K");
        redBlackTree.put(12, "L");
        redBlackTree.put(13, "M");

        String preOrderString = redBlackTree.getPreOrder();

        assertEquals(expectedString, preOrderString);
    }

    @Test
    public void Should_Return_String_InOrde() {
        String expectedString = "1:A 2:B 3:C 4:D 5:E 6:F 7:G 8:H 9:I 10:J 11:K 12:L 13:M";
        redBlackTree.put(1, "A");
        redBlackTree.put(2, "B");
        redBlackTree.put(3, "C");
        redBlackTree.put(4, "D");
        redBlackTree.put(5, "E");
        redBlackTree.put(6, "F");
        redBlackTree.put(7, "G");
        redBlackTree.put(8, "H");
        redBlackTree.put(9, "I");
        redBlackTree.put(10, "J");
        redBlackTree.put(11, "K");
        redBlackTree.put(12, "L");
        redBlackTree.put(13, "M");

        String inOrderString = redBlackTree.getInOrder();

        assertEquals(expectedString, inOrderString);
    }

    
    @Test
    public void Should_Return_String_InOrde_Empty() {
        String expectedString = "";
        //redBlackTree.put(1, "");

        String inOrderString = redBlackTree.getInOrder();

        assertEquals(expectedString, inOrderString);
    }

    @Test
    public void Should_Return_String_PostOrde() {
        String expectedString = "1:A 3:C 2:B 5:E 7:G 6:F 4:D 9:I 11:K 10:J 13:M 12:L 8:H";
        redBlackTree.put(1, "A");
        redBlackTree.put(2, "B");
        redBlackTree.put(3, "C");
        redBlackTree.put(4, "D");
        redBlackTree.put(5, "E");
        redBlackTree.put(6, "F");
        redBlackTree.put(7, "G");
        redBlackTree.put(8, "H");
        redBlackTree.put(9, "I");
        redBlackTree.put(10, "J");
        redBlackTree.put(11, "K");
        redBlackTree.put(12, "L");
        redBlackTree.put(13, "M");

        String postOrderString = redBlackTree.getPostOrder();

        assertEquals(expectedString, postOrderString);
    }

    @Test
    public void Should_deleteMax_InTree() {
        String expectedString = "1:A 2:B 3:C 4:D 5:E 6:F 7:G 8:H 9:I 10:J 11:K 12:L";
        redBlackTree.put(1, "A");
        redBlackTree.put(2, "B");
        redBlackTree.put(3, "C");
        redBlackTree.put(4, "D");
        redBlackTree.put(5, "E");
        redBlackTree.put(6, "F");
        redBlackTree.put(7, "G");
        redBlackTree.put(8, "H");
        redBlackTree.put(9, "I");
        redBlackTree.put(10, "J");
        redBlackTree.put(11, "K");
        redBlackTree.put(12, "L");
        redBlackTree.put(13, "M"); 

        redBlackTree.deleteMax();
        String postOrderString = redBlackTree.getInOrder();

        assertEquals(expectedString, postOrderString);
    }

    @Test
    public void Should_return_treeDepth() {
        Integer expectedValue = 4;

        redBlackTree.put(1, "A");
        redBlackTree.put(2, "B");
        redBlackTree.put(3, "C");
        redBlackTree.put(4, "D");
        redBlackTree.put(5, "E");
        redBlackTree.put(6, "F");
        redBlackTree.put(7, "G");
        redBlackTree.put(8, "H");
        redBlackTree.put(9, "I");
        redBlackTree.put(10, "J");
        redBlackTree.put(11, "K");
        redBlackTree.put(12, "L");
        redBlackTree.put(13, "M");

        Integer treeDepth = redBlackTree.maxDepth();
        assertEquals(expectedValue, treeDepth);
    }

}
