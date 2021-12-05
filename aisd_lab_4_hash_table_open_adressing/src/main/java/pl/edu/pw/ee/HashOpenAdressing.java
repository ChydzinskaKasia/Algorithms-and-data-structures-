package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

import java.util.NoSuchElementException;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private static final double correctLoadFactor = 0.75;

    private int size;
    private int nElems;
    private T[] hashElems;
    private State[] state;

    HashOpenAdressing() {
        this(2039);
    }

    @SuppressWarnings("unchecked")
    HashOpenAdressing(int size) {
        validateHashInitSize(size);

        this.size = size;
        this.hashElems = (T[]) new Comparable[this.size];
        this.state = new State[this.size];
        for (int i = 0; i < size; ++i) {
            state[i] = State.EMPTY;
        }
    }

    @Override
    public void put(T newElem) {
        validateInputElem(newElem);
        resizeIfNeeded();

        while (add(newElem, hashElems, state, size)) {
            resize(size + 3);
        }
    }

    private boolean add(T newElem, T[] hashElems, State[] state, int size) {
        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i, size);
        int counter = 0;

        while (state[hashId] == State.OCCUPIED && !newElem.equals(hashElems[hashId])) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i, size);
            counter++;
            if (counter > size) {
                return true;
            }
        }

        if (state[hashId] == State.EMPTY) {
            nElems++;
        }
        hashElems[hashId] = newElem;
        state[hashId] = State.OCCUPIED;
        return false;
    }

    @Override
    public T get(T elem) {
        validateInputElem(elem);
        int key = elem.hashCode();
        int i = -1;

        while (true) {
            i++;
            int hashId = hashFunc(key, i, size);
            if (state[hashId] == State.EMPTY) {
                return null;
            }
            if (elem.equals(hashElems[hashId])) {
                return hashElems[hashId];
            }
        }
    }

    @Override
    public void delete(T elem) {
        validateInputElem(elem);
        int key = elem.hashCode();
        int i = -1;

        while (true) {
            i++;
            int hashId = hashFunc(key, i, size);
            if (state[hashId] == State.EMPTY) {
                throw new NoSuchElementException();
            }
            if (elem.equals(hashElems[hashId])) {
                hashElems[hashId] = nil;
                state[hashId] = State.DELETED;
                return;
            }
        }
    }

    private void validateHashInitSize(int initialSize) {
        if (initialSize < 1) {
            throw new IllegalArgumentException("Initial size of hash table cannot be lower than 1!");
        }
    }

    private void validateInputElem(T newElem) {
        if (newElem == null) {
            throw new IllegalArgumentException("Input elem cannot be null!");
        }
    }

    abstract int hashFunc(int key, int i, int size);

    int getSize() {
        return size;
    }

    private void resizeIfNeeded() {
        double loadFactor = countLoadFactor();

        if (loadFactor >= correctLoadFactor) {
            resize(size * 2);
        }
    }

    private double countLoadFactor() {
        return (double) nElems / size;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        int newElems = 0;
        T[] newHashElems = (T[]) new Comparable[newSize];
        State[] newState = new State[newSize];
        for (int i = 0; i < newSize; ++i) {
            newState[i] = State.EMPTY;
        }

        for (int i = 0; i < size; ++i) {
            if (hashElems[i] != nil) {
                add(hashElems[i], newHashElems, newState, newSize);
                newElems++;
            }
        }
        hashElems = newHashElems;
        state = newState;
        size = newSize;
        nElems = newElems;
    }

}