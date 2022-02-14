package pl.edu.pw.ee;

import static pl.edu.pw.ee.Color.BLACK;
import static pl.edu.pw.ee.Color.RED;

public class RedBlackTree<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private StringBuilder result;
    public Integer counter = 0;

    public V get(K key) {
        validateKey(key);
        Node<K, V> node = root;

        V result = null;

        while (node != null) {

            if (shouldCheckOnTheLeft(key, node)) {
                node = node.getLeft();

            } else if (shouldCheckOnTheRight(key, node)) {
                node = node.getRight();

            } else {
                result = node.getValue();
                break;
            }
        }
        return result;
    }

    public void put(K key, V value) {
        validateParams(key, value);
        root = put(root, key, value);
        root.setColor(BLACK);
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        counter++;
        if (node == null) {
            return new Node(key, value);
        }

        if (isKeyBiggerThanNode(key, node)) {
            putOnTheRight(node, key, value);

        } else if (isKeySmallerThanNode(key, node)) {
            putOnTheLeft(node, key, value);

        } else {
            node.setValue(value);
        }

        node = reorganizeTree(node);

        return node;
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null.");
        }
    }

    private boolean shouldCheckOnTheLeft(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private boolean shouldCheckOnTheRight(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void validateParams(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Input params (key, value) cannot be null.");
        }
    }

    private boolean isKeyBiggerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) > 0;
    }

    private void putOnTheRight(Node<K, V> node, K key, V value) {
        Node<K, V> rightChild = put(node.getRight(), key, value);
        node.setRight(rightChild);
    }

    private boolean isKeySmallerThanNode(K key, Node<K, V> node) {
        return key.compareTo(node.getKey()) < 0;
    }

    private void putOnTheLeft(Node<K, V> node, K key, V value) {
        Node<K, V> leftChild = put(node.getLeft(), key, value);
        node.setLeft(leftChild);
    }

    private Node<K, V> reorganizeTree(Node<K, V> node) {
        node = rotateLeftIfNeeded(node);
        node = rotateRightIfNeeded(node);
        changeColorsIfNeeded(node);

        return node;
    }

    private Node<K, V> rotateLeftIfNeeded(Node<K, V> node) {
        if (isBlack(node.getLeft()) && isRed(node.getRight())) {
            node = rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> head = node.getRight();
        node.setRight(head.getLeft());
        head.setLeft(node);
        head.setColor(node.getColor());
        node.setColor(RED);

        return head;
    }

    private Node<K, V> rotateRightIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
            node = rotateRight(node);
        }
        return node;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {

        Node<K, V> head = node.getLeft();
        node.setLeft(head.getRight());
        head.setRight(node);
        head.setColor(node.getColor());
        node.setColor(RED);
        return head;
    }

    private void changeColorsIfNeeded(Node<K, V> node) {
        if (isRed(node.getLeft()) && isRed(node.getRight())) {
            changeColors(node);
        }
    }

    private void changeColors(Node<K, V> node) {
        node.setColor(RED);
        node.getLeft().setColor(BLACK);
        node.getRight().setColor(BLACK);
    }

    private void changeColorsIntoRed(Node<K, V> node) {
        node.setColor(BLACK);
        node.getLeft().setColor(RED);
        node.getRight().setColor(RED);
    }

    private boolean isBlack(Node<K, V> node) {
        return !isRed(node);
    }

    private boolean isRed(Node<K, V> node) {
        return node == null ? false : node.isRed();
    }

    public void deleteMax() {
        if (root == null) { 
            throw new IllegalArgumentException("Root cannot be null.");
        }
        root = deleteMax(root);
    }

    private Node<K, V> deleteMax(Node<K, V> node) {
        if (isLeftChildRed(node)) { 
            node = rotateRight(node); 
        }                             
        if (node.getRight() == null) { 
            return null;               
        }                              
        if (!isLeftChildRed(node.getRight()) && !isRightChildRed(node)) {
            changeColorsIntoRed(node);
            if (isLeftChildRed(node.getLeft())) {
                node = rotateRight(node);
                changeColorsIntoRed(node);
            }
        }
        node.setRight(deleteMax(node.getRight()));
        return reorganizeTree(node);
    }

    private boolean isLeftChildRed(Node<K, V> node) {
        return node.getLeft() != null && node.getLeft().getColor() == RED;
    }

    private boolean isRightChildRed(Node<K, V> node) {
        return node.getRight() != null && node.getRight().getColor() == RED;
    }

    public String getPreOrder() {
        result = new StringBuilder();
        getPreOrder(this.root);
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public void getPreOrder(Node<K, V> node) {
        if (node != null) {
            result.append(node.getKey().toString() + ":" + node.getValue().toString() + " ");
            getPreOrder(node.getLeft());
            getPreOrder(node.getRight());
        }
    }

    public String getInOrder() {
        result = new StringBuilder();
        getInOrder(this.root);
        //result.deleteCharAt(result.length() - 1);
        return result.toString().trim();
    }

    public void getInOrder(Node<K, V> node) {
        if (node != null) {
            getInOrder(node.getLeft());
            result.append(node.getKey().toString() + ":" + node.getValue().toString() + " ");
            getInOrder(node.getRight());
        }
    }

    public String getPostOrder() {
        result = new StringBuilder();
        getPostOrder(this.root);
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public void getPostOrder(Node<K, V> node) {
        if (node != null) {
            getPostOrder(node.getLeft());
            getPostOrder(node.getRight());
            result.append(node.getKey().toString() + ":" + node.getValue().toString() + " ");
        }
    }

    public Node<K, V> getRoot() {
        return this.root;

    }

    public int maxDepth() {
        return maxDepthTree(root);
    }

    private int maxDepthTree(Node<K, V> root) {

        if (root == null) {
            return 0;
        } else {
            int left = maxDepthTree(root.getLeft());
            int right = maxDepthTree(root.getRight());

            if (left > right)
                return (left + 1);
            else
                return (right + 1);
        }

    }
}
