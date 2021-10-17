import pl.edu.pw.ee.services.*;
import pl.edu.pw.ee.InsertionSort;
import pl.edu.pw.ee.QuickSort;
import pl.edu.pw.ee.SelectionSort;
//import pl.edu.pw.ee.SelectionSort;

public class SortingAlgorithms {
    public static void main(String[] args) {
        double[] nums = { 4.0, 1.4, 2.3, 0.6, 2.0, 1.0 };
        // Sorting sorting = new InsertionSort();
        Sorting sorting = new SelectionSort();
        //Sorting sorting = new QuickSort();

        sorting.sort(nums);
        for (double n : nums) {
            System.out.println(n);
        }
    }
}
