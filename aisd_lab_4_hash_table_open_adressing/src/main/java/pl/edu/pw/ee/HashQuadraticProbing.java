package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {
    private final int a;
    private final int b;

    public HashQuadraticProbing() {
        super();
        a = 2;
        b = 4;
    }

    public HashQuadraticProbing(int size, int a, int b) {
        super(size);
        this.a = a;
        this.b = b;
    }

    @Override
    int hashFunc(int key, int i, int size) {
        return Math.abs((key + a * i + b * i * i) % size);
    }
}
