package pl.edu.pw.ee;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] words = readFile();

        for (int k = 1, multiplier = 1; k <= 7; k++, multiplier *= 2) {

            int size = multiplier * 4096;
            final HashListChaining<String> hashTable = new HashListChaining<String>(size);

            for (String word : words) {
                hashTable.add(word);

            }
            System.out.println(MessageFormat.format("Rozmair tablicy: {0}, średni czas wyszukiwania {1} microsec", size,
                    measureTime(hashTable, words)));

        }
    }

    private static String[] readFile() {
        String[] words = new String[100000];
        int i = 0;
        try {
            String path = new File("listaslow.txt").getAbsolutePath();
            File file = new File(path);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                words[i++] = fileScanner.nextLine();
            }
            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");

        }

        return words;
    }

    private static long measureTime(HashListChaining<String> hashTable, String[] words) {
        long[] timeResultMikro = new long[30];

        for (int t = 0; t < 30; t++) {
            long start = System.nanoTime();

            for (String word : words) {
                hashTable.get(word);

            }
            long stop = System.nanoTime();
            timeResultMikro[t] = stop - start;

        }
        Arrays.sort(timeResultMikro);
        long sum = 0;
        for (int i = 10; i < 20; i++) {
            sum = sum + timeResultMikro[i] / (10 * 1000);
        }
        return sum;

    }

}
