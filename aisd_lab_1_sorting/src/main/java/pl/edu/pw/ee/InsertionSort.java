package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Nums array cannot be null");
        }

        int i, j;

        for (i = 1; i < nums.length; i++) {
            double currentElement = nums[i];
            j = i - 1;

            while (j >= 0) {
                double predecessor = nums[j];
                if (predecessor > currentElement) {
                    nums[j + 1] = predecessor;
                    j--;
                } else {
                    break;
                }

            }
            nums[j + 1] = currentElement;

        }
    
    }
}
