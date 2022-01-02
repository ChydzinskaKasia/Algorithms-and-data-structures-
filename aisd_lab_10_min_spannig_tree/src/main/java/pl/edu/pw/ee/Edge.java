package pl.edu.pw.ee;

public class Edge {
    private final Node firstElem;
    public final Node secondElem;
    public final int cost;

    public Edge(Node firstElem, Node target, int cost) {
        this.firstElem = firstElem;
        this.secondElem = target;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return firstElem.name + '_' + cost + '_' + secondElem.name;
    }
}
