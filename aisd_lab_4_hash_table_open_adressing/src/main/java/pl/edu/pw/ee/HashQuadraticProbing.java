package pl.edu.pw.ee;

public class HashQuadraticProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {
    public HashQuadraticProbing() {
        super();
    }

    public HashQuadraticProbing(int size) {
        super(size);
    }

    @Override
    int hashFunc(int key, int i) {
        int m = getSize();
        int a = 1;
        int b = 2;
        int hash = (key % m + a * i + b * i * i) % m;

        hash = hash < 0 ? -hash : hash;

        return hash;
    }
}
