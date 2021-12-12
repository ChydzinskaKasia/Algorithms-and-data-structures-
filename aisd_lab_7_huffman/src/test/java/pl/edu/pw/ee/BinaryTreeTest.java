package pl.edu.pw.ee;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BinaryTreeTest {
    private BinaryTree binaryTree;

    @org.junit.Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        binaryTree = new BinaryTree();
    }

    @Test
    public void Should_ThrowNullPointerException_When_NullGetRoot() throws Exception {

        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Root is not initialized");
        binaryTree.getRoot();
    }
}
