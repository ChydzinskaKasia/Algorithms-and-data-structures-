import pl.edu.pw.ee.services.*;
//import pl.edu.pw.ee.InsertionSort;
//import pl.edu.pw.ee.QuickSort;
//import pl.edu.pw.ee.SelectionSort;
import pl.edu.pw.ee.HeapSort;

import java.util.Random;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.PrintWriter;
//import pl.edu.pw.ee.WriterIntoFile;

public class SortingAlgorithms {

    public static void main(String[] args) {

        // int[] lenElemInList = { 1, 500, 1000, 1500, 2000, 2500, 3000, 3500, 4000,
        // 4500, 5000, 5500, 6000, 6500, 7000,7500, 8000, 8500, 9000, 10000};
        int[] lenElemInList = { 1, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850,
                900, 950, 1000, 1050, 1100, 1150, 1200, 1250, 1300, 1350, 1400, 1450, 1500, 1550, 1600, 1650, 1700,
                1750, 1800, 1850, 1900, 1950, 2000, 2050, 2100, 2150, 2200, 2250, 2300, 2350, 2400, 2450, 2500, 2550,
                2600, 2650, 2700, 2750, 2800, 2850, 2900, 2950, 3000, 3050, 3100, 3150, 3200, 3250, 3300, 3350, 3400,
                3450, 3500, 3550, 3600, 3650, 3700, 3750, 3800, 3850, 3900, 3950, 4000, 4050, 4100, 4150, 4200, 4250,
                4300, 4350, 4400, 4450, 4500, 4550, 4600, 4650, 4700, 4750, 4800, 4850, 4900, 4950, 5000, 5050, 5100,
                5150, 5200, 5250, 5300, 5350, 5400, 5450, 5500, 5550, 5600, 5650, 5700, 5750, 5800, 5850, 5900, 5950,
                6000, 6050, 6100, 6150, 6200, 6250, 6300, 6350, 6400, 6450, 6500, 6550, 6600, 6650, 6700, 6750, 6800,
                6850, 6900, 6950, 7000, 7050, 7100, 7150, 7200, 7250, 7300, 7350, 7400, 7450, 7500, 7550, 7600, 7650,
                7700, 7750, 7800, 7850, 7900, 7950, 8000, 8050, 8100, 8150, 8200, 8250, 8300, 8350, 8400, 8450, 8500,
                8550, 8600, 8650, 8700, 8750, 8800, 8850, 8900, 8950, 9000, 9050, 9100, 9150, 9200, 9250, 9300, 9350,
                9400, 9450, 9500, 9550, 9600, 9650, 9700, 9750, 9800, 9850, 9900, 9950, 10000 };

        try {
            PrintWriter zapis = new PrintWriter("C:\\Users\\Kasia\\Desktop\\aisd_lab_1_sortowanie\\Heap_odwrocone.csv");

            for (int index = 0; index < lenElemInList.length; index++) {

                double x = 0.0;
                double y = 200.0;
                Random rand = new Random(15071998);
                double[] nums = new double[lenElemInList[index]];

                for (int i = 0; i < nums.length; i++) {
                    nums[i] = rand.nextDouble() * (y - x) + x;
                }

                double[] copiedNums = Arrays.copyOf(nums, nums.length);

                Arrays.sort(copiedNums);

                // odwracanie

                int n = copiedNums.length;

                double[] b = new double[n];
                int j = n;
                for (int i = 0; i < n; i++) {
                    b[j - 1] = copiedNums[i];
                    j = j - 1;
                }
                double[] reverse = Arrays.copyOf(b, n);

                // Sorting sorting = new InsertionSort();
                // Sorting sorting = new SelectionSort();
                // Sorting sorting = new QuickSort();
                Sorting sorting = new HeapSort();
                
                // tablica tych samych elementów
                // double[] nums = new double[lenElemInList[index]];
                // Arrays.fill(nums, 80.0);

                
                long sum = 0;
                for (int k = 0; k < 200; k++) {
                    long startTime = System.nanoTime();

                    sorting.sort(reverse); 
                    long endTime = System.nanoTime();
                    sum += endTime - startTime;

                }
                sum = sum / 200;
              
                // System.out.println(Arrays.toString(odwrocona));
                // System.out.println("czas sorotowania:" + (endTime - startTime));
                System.out.println("czas sorotowania:" + sum);

                String timeDouble = String.valueOf(sum);
                String numberDouble = String.valueOf(nums.length); 
                zapis.println(timeDouble + ";" + numberDouble);

            }
            zapis.close();
        } catch (FileNotFoundException e) {
            System.out.println("Plik nie istenieje");
        }

    }

}