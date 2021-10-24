package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HeapInterface;

import java.util.ArrayList;
import java.util.Arrays;

public class Heap<T extends Comparable<T>> implements HeapInterface<T> {

    private ArrayList<T> heapList;

    public Heap(T[] array) {
        heapList = new ArrayList<>();
        if (array != null) {
            heapList.addAll(Arrays.asList(array));
        }
        createHeap();
    }

    public void heapify(int heapSize, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < heapSize && heapList.get(left).compareTo(heapList.get(largest)) == 1) {
            largest = left;
        }

        if (right < heapSize && heapList.get(right).compareTo(heapList.get(largest)) == 1) {
            largest = right;
        }

        if (largest != i) {
            T swapVariable = heapList.get(i);
            heapList.set(i, heapList.get(largest));
            heapList.set(largest, swapVariable);
            heapify(heapSize, largest);
        }
    }

    public void createHeap() {
        int heapSize = heapList.size();
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            heapify(heapSize, i);
        }
    }

    @Override
    public void put(T item) {
        if (item != null) {
            heapList.add(item);
            createHeap();
        }
    }

    @Override
    public T pop() {
        if (heapList.isEmpty()) {
            return null;
        }
        T swapVariable = heapList.get(0);
        heapList.set(0, heapList.get(heapList.size() - 1));
        heapList.set(heapList.size() - 1, swapVariable);
        heapList.remove(heapList.size() - 1);
        heapify(heapList.size(), 0);
        return swapVariable;
    }

    public ArrayList<T> getHeapList() {
        return heapList;
    }
}
