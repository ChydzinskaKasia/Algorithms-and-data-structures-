package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Measure {
    private static final String[] words = readFile();
    private static final int[] sizes = {
            512,
            512 << 1,
            512 << 2,
            512 << 3,
            512 << 4,
            512 << 5,
            512 << 6,
            512 << 7,
            512 << 8,
            512 << 9
    };
    private static final Creator[] creators = prepareCreators();

    private static Creator[] prepareCreators() {
        return new Creator[] {
                new Creator() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashLinearProbing<>(size);
                    }
                },
                new Creator() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashDoubleHashing<>(size);
                    }
                },
                new Creator() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 3, 5);
                    }
                },
                new Creator() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 5, 7);
                    }
                },
                new Creator() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 7, 13);
                    }
                },
                new Creator() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 5, 3);
                    }
                },
                new Creator() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 7, 5);
                    }
                },
                new Creator() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 13, 7);
                    }
                },
                new Creator() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 15, 41);
                    }
                },
                new Creator() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 97, 97);
                    }
                },
                new Creator() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 123, 17);
                    }
                },
                new Creator() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 1234, 5);
                    }
                }
        };
    }

    public static void main(String[] args) {

        for (int size : sizes) {
            System.err.print(size + "\n");
            for (Creator c : creators) {
                System.err.print(measurePutTime(c, size) + "\n");
                System.err.print(measureGetTime(c, size) + "\n");
            }
            System.err.println();
        }

    }

    private static String[] readFile() {
        String[] words = new String[100000];
        int t = 0;
        try {
            String path = new File("listaslow.txt").getAbsolutePath();
            File file = new File(path);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                words[t++] = fileScanner.nextLine();
            }
            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");

        }
        return words;
    }

    private static long measurePutTime(Creator creator, int size) {
        long[] timeResultMikro = new long[30];
        for (int t = 0; t < 30; t++) {
            long start = System.nanoTime();
            HashTable<String> hashTable = creator.create(size);
            for (int k = 0; k < 100000; k++) {
                hashTable.put(words[k]);
            }
            long stop = System.nanoTime();
            timeResultMikro[t] = stop - start;
        }
        Arrays.sort(timeResultMikro);
        long sum = 0;
        for (int k = 10; k < 20; k++) {
            sum = sum + timeResultMikro[k] / (10 * 1000);
        }
        return sum;
    }

    private static long measureGetTime(Creator creator, int size) {
        HashTable<String> hashTable = creator.create(size);
        for (int k = 0; k < 100000; k++) {
            hashTable.put(words[k]);
        }

        long[] timeResultMikro = new long[30];
        for (int t = 0; t < 30; t++) {
            long start = System.nanoTime();
            for (int k = 0; k < 100000; k++) {
                hashTable.get(words[k]);
            }
            long stop = System.nanoTime();
            timeResultMikro[t] = stop - start;
        }
        Arrays.sort(timeResultMikro);
        long sum = 0;
        for (int k = 10; k < 20; k++) {
            sum = sum + timeResultMikro[k] / (10 * 1000);
        }
        return sum;
    }

    private interface Creator {
        HashTable<String> create(int size);
    }
}
