package pl.edu.pw.ee;

public class HashDoubleHashing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    public HashDoubleHashing() {
        super();
    }

    public HashDoubleHashing(int size) {
        super(size);
    }

    private int g(int key, int size) {
        return 1 + key % (size - 3);
    }

    @Override
    int hashFunc(int key, int i, int size) {
        return Math.abs((key + i * g(key, size)) % size);
    }

}
