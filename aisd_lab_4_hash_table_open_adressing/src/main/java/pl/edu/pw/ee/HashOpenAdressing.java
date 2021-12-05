package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

import java.util.NoSuchElementException;

public abstract class HashOpenAdressing<T extends Comparable<T>> implements HashTable<T> {

    private final T nil = null;
    private static final double correctLoadFactor = 0.75;

    private int size;
    private int nElems;
    private T[] hashElems;
    private State[] fieldStates;

    HashOpenAdressing() {
        this(2039); // initial size as random prime number
    }

    @SuppressWarnings("unchecked")
    HashOpenAdressing(int size) {
        validateHashInitSize(size);

        this.size = size;
        this.hashElems = (T[]) new Comparable[this.size];
        this.fieldStates = new State[this.size];
        for (int i = 0; i < size; ++i) {
            fieldStates[i] = State.EMPTY;
        }
    }

    @Override
    public void put(T newElem) {
        validateInputElem(newElem);
        resizeIfNeeded();

        while (addTo(newElem, hashElems, fieldStates, size)) {
            resize(size + 3); // podwajanie rozmiaru nie zawsze rozwiązuje problem
        }
    }

    @Override
    public T get(T elem) {
        validateInputElem(elem);
        int key = elem.hashCode();
        int i = -1;

        while (true) {
            i++;
            int hashId = hashFunc(key, i, size);
            if (fieldStates[hashId] == State.EMPTY) {
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
            if (fieldStates[hashId] == State.EMPTY) {
                throw new NoSuchElementException();
            }
            if (elem.equals(hashElems[hashId])) {
                hashElems[hashId] = nil;
                fieldStates[hashId] = State.DELETED;
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
        int newNElems = 0;
        T[] newHashElems = (T[]) new Comparable[newSize];
        State[] newFieldStates = new State[newSize];
        for (int i = 0; i < newSize; ++i) {
            newFieldStates[i] = State.EMPTY;
        }

        for (int i = 0; i < size; ++i) {
            if (hashElems[i] != nil) {
                addTo(hashElems[i], newHashElems, newFieldStates, newSize);
                newNElems++;
            }
        }
        hashElems = newHashElems;
        fieldStates = newFieldStates;
        size = newSize;
        nElems = newNElems;
    }

    private boolean addTo(T newElem, T[] hashElems, State[] fieldStates, int size) {
        int key = newElem.hashCode();
        int i = 0;
        int hashId = hashFunc(key, i, size);
        int counter = 0;

        while (fieldStates[hashId] == State.OCCUPIED && !newElem.equals(hashElems[hashId])) {
            i = (i + 1) % size;
            hashId = hashFunc(key, i, size);
            counter++;
            if (counter > size) {
                return true; // czasami nie da się znaleźć odpowiedniego miejsca mimo niskiej zajętości tablicy
            }
        }

        if (fieldStates[hashId] == State.EMPTY) {
            nElems++;
        }
        hashElems[hashId] = newElem;
        fieldStates[hashId] = State.OCCUPIED;
        return false;
    }
}