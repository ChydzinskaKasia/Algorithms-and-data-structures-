package pl.edu.pw.ee;

import pl.edu.pw.ee.services.HashTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Measure {
    private static final String[] words = readFile();
    private static final int[] sizes = {
            512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144
    };
    private static final OpenAdressingInterface[] table = tableMeasure();

    private static OpenAdressingInterface[] tableMeasure() {
        return new OpenAdressingInterface[] {
                new OpenAdressingInterface() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashLinearProbing<>(size);
                    }
                },
                new OpenAdressingInterface() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashDoubleHashing<>(size);
                    }
                },
                new OpenAdressingInterface() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 1, 2);
                    }
                },
                new OpenAdressingInterface() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 3, 4);
                    }
                },
                new OpenAdressingInterface() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 3, 7);
                    }
                },
                new OpenAdressingInterface() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 9, 12);
                    }
                },
                new OpenAdressingInterface() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 5, 15);
                    }
                },
                new OpenAdressingInterface() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 1, 6);
                    }
                },
                new OpenAdressingInterface() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 10, 21);
                    }
                },
                new OpenAdressingInterface() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 15, 7);
                    }
                },
                new OpenAdressingInterface() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 19, 98);
                    }
                },
                new OpenAdressingInterface() {
                    @Override
                    public HashTable<String> create(int size) {
                        return new HashQuadraticProbing<>(size, 100, 16);
                    }
                }
        };
    }

    public static void main(String[] args) {

        for (int size : sizes) {
            System.err.print(size + "\n");
            for (OpenAdressingInterface elem : table) {
                System.out.print(measurePutTime(elem, size) + " " + measureGetTime(elem, size) + "\n");
            }
            System.out.println();
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

    private static long measurePutTime(OpenAdressingInterface creator, int size) {
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

    private static long measureGetTime(OpenAdressingInterface creator, int size) {
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

    private interface OpenAdressingInterface {
        HashTable<String> create(int size);
    }
}
