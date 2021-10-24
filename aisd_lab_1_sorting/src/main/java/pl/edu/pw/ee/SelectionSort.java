package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class SelectionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }

        for (int i = 0; i < nums.length - 1; i++) {
            int MIN_ID = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[MIN_ID]) {
                    MIN_ID = j;
                }

            }

            double MIN_VAL = nums[i];
            nums[i] = nums[MIN_ID];
            nums[MIN_ID] = MIN_VAL;
        }
    }
    
}