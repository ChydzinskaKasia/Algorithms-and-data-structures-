package pl.edu.pw.ee;

import pl.edu.pw.ee.services.Sorting;

import java.util.ArrayList; // dodana biblioteka
import java.util.List; //dodana biblioteka

public class QuickSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        if (nums == null) { // zmienione
            throw new IllegalArgumentException("Nums array cannot be null");
        }

        quicksort(nums);

    }

    private void quicksort(double[] data) {
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();
        Integer left = 0;
        Integer right = data.length - 1;

        starts.add(left);
        ends.add(right);

        int n = 1;
        int pivot;

        if (left < right) {

            while (n > 0) {
                n--;
                left = starts.get(n);
                right = ends.get(n);

                starts.remove(n);
                ends.remove(n);

                pivot = splitData(data, left, right);
                // pivot = 1;

                if (pivot - 1 > left) {
                    starts.add(left);
                    ends.add(pivot - 1);
                    n++;
                }

                if (pivot + 1 < right) {
                    starts.add(pivot + 1);
                    ends.add(right);
                    n++;
                }
            }
        }
    }

    private int splitData(double[] data, int start, int end) {
        int left = start + 1;
        int right = end;

        while (left < right) {
            while (left < right && data[left] < data[start]) {
                left++;
            }

            while (left < right && data[right] >= data[start]) {
                right--;
            }

            swap(data, left, right);
        }

        if (data[left] >= data[start]) {
            left--;
        }

        swap(data, start, left);

        return left;
    }

    private void swap(double[] data, int firstId, int secondId) {
        if (firstId != secondId) {
            double firstValue = data[firstId];
            data[firstId] = data[secondId];
            data[secondId] = firstValue;
        }
    }

}
