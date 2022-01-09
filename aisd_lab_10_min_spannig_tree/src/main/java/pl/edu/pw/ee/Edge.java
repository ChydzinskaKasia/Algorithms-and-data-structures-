package pl.edu.pw.ee;

public class Edge {
    public final Node firstElem;
    public final Node secondElem;
    public final int cost;

    public Edge(Node firstElem, Node target, int cost) {
        this.firstElem = firstElem;
        this.secondElem = target;
        this.cost = cost;
    }

    public int getWeight() {
        return cost;
    }

    @Override
    public String toString() {
        return firstElem.name.compareTo(secondElem.name) < 0
                ? firstElem.name + '_' + cost + '_' + secondElem.name
                : secondElem.name + '_' + cost + '_' + firstElem.name;
    }
}
