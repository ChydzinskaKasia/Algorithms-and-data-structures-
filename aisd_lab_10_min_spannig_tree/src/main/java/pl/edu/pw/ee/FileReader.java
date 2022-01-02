package pl.edu.pw.ee;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FileReader {
    List<Node> fileToGraph(String path) {
        Pattern pattern = Pattern.compile("^([a-zA-Z]+) ([a-zA-Z]+) (\\d+)$");

        List<Node> list = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Matcher m = pattern.matcher(line);
                if (!m.matches()) {
                    throw new IllegalArgumentException("Niepoprawne dane: " + line);
                }

                Node node1 = addIfAbsent(list, m.group(1));
                Node node2 = addIfAbsent(list, m.group(2));

                int weight = Integer.parseInt(m.group(3));
                node1.addEdge(node2, weight);
                node2.addEdge(node1, weight);
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    private Node addIfAbsent(List<Node> list, String name) {
        for (Node n : list) {
            if (n.name.equals(name)) {
                return n;
            }
        }
        Node n = new Node(name);
        list.add(n);
        return n;
    }
}
