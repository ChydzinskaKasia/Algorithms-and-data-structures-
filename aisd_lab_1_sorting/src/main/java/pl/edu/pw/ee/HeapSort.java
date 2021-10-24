package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class HeapSort implements Sorting {
    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }
        
        Double[] numsObject = new Double[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsObject[i] = nums[i];
        }
        Heap<Double> sortingHeap = new Heap<>(numsObject);
        for (int i = numsObject.length - 1; i >= 0; i--) {
            nums[i] = sortingHeap.pop();
        }
    }

}
