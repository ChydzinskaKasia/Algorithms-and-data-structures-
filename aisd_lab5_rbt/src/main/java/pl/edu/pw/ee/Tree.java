package pl.edu.pw.ee;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Tree {

    private static List<String> readFile() {
        List<String> words = new ArrayList<String>();
        try {
            String path = new File("daneRBTree.txt").getAbsolutePath();
            File file = new File(path);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                words.add(fileScanner.nextLine());
            }
            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");

        }

        return words;
    }

    private static int[] putCounter(int step, List<String> words) {
        int[] result = new int[words.size() / step + 1];
        int key = 1;
        int index = 0;
        RedBlackTree<String, Integer> redBlackTree = new RedBlackTree<>();

        for (String word : words) {
            redBlackTree.counter = 0;
            redBlackTree.put(word, key);
            if ((key - 1) % step == 0) {
                result[index] = redBlackTree.counter;
                index++;
            }
            key++;
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> words = readFile();
        List<String> wordsReverse = new ArrayList<String>(words);
        List<String> wordsRandom = new ArrayList<String>(words);
        Collections.reverse(wordsReverse);
        Collections.shuffle(wordsRandom);

        int[] score = putCounter(1000, wordsRandom);

        String[] converted = new String[score.length];

        for (int i = 0; i < score.length; i++) {
            converted[i] = String.valueOf(score[i]);
        }

        try {
            PrintWriter writer = new PrintWriter("C:\\Users\\Kasia\\Desktop\\pomiar_drzewo\\rand.csv");

            String csv = String.join("\n", converted);
            writer.write(csv);

            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Plik nie istnieje");
        }
    }

}
