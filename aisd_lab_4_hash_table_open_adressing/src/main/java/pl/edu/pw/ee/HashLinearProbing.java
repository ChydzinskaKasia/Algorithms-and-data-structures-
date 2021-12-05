package pl.edu.pw.ee;

public class HashLinearProbing<T extends Comparable<T>> extends HashOpenAdressing<T> {

    public HashLinearProbing() {
        super();
    }

    public HashLinearProbing(int size) {
        super(size);
    }

    @Override
    int hashFunc(int key,int i, int size) {
        /*
        int m = getSize();

        int hash = (key % m + i) % m;

        hash = hash < 0 ? -hash : hash;

        return hash;
        */
        return Math.abs((key + i) % size);
    }
}