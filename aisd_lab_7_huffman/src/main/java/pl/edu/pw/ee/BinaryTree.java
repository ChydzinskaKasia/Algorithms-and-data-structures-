package pl.edu.pw.ee;

import java.io.Serializable;
import java.util.Comparator;

public class BinaryTree implements Serializable {

    private Node root;

    public BinaryTree(int freq, char key) {
        root = new Node(freq, key);
    }

    public BinaryTree() {
    }

    public Node getRoot() throws NullPointerException {
        if (root == null)
        {
            throw new NullPointerException("Root is not initialized");
        }
        return this.root;

    }

    private Node addRecursive(Node current, int value, char key) {
        if (current == null) {
            return new Node(value, key);
        }

        if (value < current.getFreq()) {
            current.left = addRecursive(current.left, value, key);
        } else if (value > current.getFreq()) {
            current.right = addRecursive(current.right, value, key);
        } else {
            return current;
        }

        return current;
    }

    public void add(char key, int value) {
        root = addRecursive(root, value, key);
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof BinaryTree)) {
            return false;
        }

        BinaryTree c = (BinaryTree) o;

        return root.getFreq() == c.getRoot().getFreq()
                && root.getKey() == c.getRoot().getKey();
    }
}

class RootComparator implements Comparator<BinaryTree> {
    @Override
    public int compare(BinaryTree a, BinaryTree b) {
        if (a.getRoot().getFreq() < b.getRoot().getFreq()) {
            return -1;
        } else if (a.getRoot().getFreq() == b.getRoot().getFreq()) {
            return 0;
        } else
            return 1;
    }

}