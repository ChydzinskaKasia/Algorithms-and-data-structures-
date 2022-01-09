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
                Matcher matcher = pattern.matcher(line);
                if (!matcher.matches()) {
                    throw new IllegalArgumentException("Invalid data: " + line);
                }

                Node frstNode = addNode(list, matcher.group(1));
                Node secNode = addNode(list, matcher.group(2));

                if (frstNode.name.equals(secNode.name)) {
                    throw new AssertionError(); 
                }

                int weight = Integer.parseInt(matcher.group(3));
                frstNode.addEdges(secNode, weight);
                secNode.addEdges(frstNode, weight);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    private Node addNode(List<Node> list, String name) {
        for (Node node : list) {
            if (node.name.equals(name)) {
                return node;
            }
        }
        Node node = new Node(name);
        list.add(node);
        return node;
    }
}