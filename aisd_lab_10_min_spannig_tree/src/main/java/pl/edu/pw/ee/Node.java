package pl.edu.pw.ee;

import java.util.ArrayList;
import java.util.List;

class Node {
    public final String name;
    public final List<Edge> edges;

    Node(String name) {
        this.name = name;
        edges = new ArrayList<>();
    }

    void addEdge(Node to, int weight) {
        edges.add(new Edge(this, to, weight));
    }

    public String getName() {
        return name;
    }
}
