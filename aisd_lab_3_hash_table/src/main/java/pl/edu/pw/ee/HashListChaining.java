package pl.edu.pw.ee;

import java.lang.reflect.Array;

import pl.edu.pw.ee.services.HashTable;

public class HashListChaining<T extends Comparable<T>> implements HashTable<T> {

    private final Elem nil = null;
    private Elem[] hashElems;
    private int nElem;

    private class Elem {
        private T value; // Object na T
        private Elem next;

        Elem(T value, Elem nextElem) { // Object na T
            this.value = value;
            this.next = nextElem;
        }
    }

    @SuppressWarnings("unchecked")
    public HashListChaining(int size) {
        hashElems = (Elem[]) Array.newInstance(Elem.class, size); // new Elem[size];
        initializeHash();
    }

    @Override
    public void add(T value) { // Object na T
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem oldElem = hashElems[hashId];
        while (oldElem != nil && oldElem.value.compareTo(value) != 0) { // !oldElem.equals(value)
            oldElem = oldElem.next;
        }
        if (oldElem != nil) {
            oldElem.value = value;
        } else {
            hashElems[hashId] = new Elem(value, hashElems[hashId]);
            nElem++;
        }
    }

    @Override
    public T get(T value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elem = hashElems[hashId];

        while (elem != nil && elem.value.compareTo(value) != 0) { // !elem.value.equals(value)
            elem = elem.next;
        }

        return elem != nil ? elem.value : null; // null zamiast nil
    }

    @Override
    public void delete(T value) {
        int hashCode = value.hashCode();
        int hashId = countHashId(hashCode);

        Elem elem = hashElems[hashId];
        if (elem == nil) {
            return;
        }
        if (elem.value.compareTo(value) == 0) {
            hashElems[hashId] = hashElems[hashId].next;
            nElem = nElem--;
            return;
        }
        while (elem.next != nil && elem.next.value.compareTo(value) != 0) {
            elem = elem.next;
        }
        if (elem.next != nil) {
            elem.next = elem.next.next;
            nElem = nElem--;
        }
    }

    public double countLoadFactor() {
        double size = hashElems.length;
        return nElem / size;
    }

    private void initializeHash() {
        int n = hashElems.length;

        for (int i = 0; i < n; i++) {
            hashElems[i] = nil;
        }
    }

    private int countHashId(int hashCode) {
        int n = hashElems.length;
        return Math.abs(hashCode) % n;
    }
}