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
        /*
        int m = getSize();

        int hfun = (key % m + i) % m;
        int gfun = 1 + key % (m - 3);

        int hash = (hfun + i * gfun) % m;

        hash = hash < 0 ? -hash : hash;

        return hash;
        */
        return Math.abs((key + i * g(key, size)) % size);
    }

}
