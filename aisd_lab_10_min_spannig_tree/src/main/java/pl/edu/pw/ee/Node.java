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

    void addEdges(Node secondElem, int weight) {
        edges.add(new Edge(this, secondElem, weight));
    }

    public String getName() {
        return name;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}