package pl.edu.pw.ee;

import pl.edu.pw.ee.services.MinSpanningTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PrimAlgorithm implements MinSpanningTree {

    public String findMST(String path) {
        List<Node> graph = new FileReader().fileToGraph(path);
        List<Edge> mst = findMST(graph);
        return mst.stream().map(Edge::toString).collect(Collectors.joining("|"));
    }
    
    static List<Edge> findMST(List<Node> graph) {
        List<NodeWithDist> remainingNodes = graph.stream().map(NodeWithDist::new).collect(Collectors.toList());
        List<Edge> mst = new ArrayList<>();

        NodeWithDist root = remainingNodes.get(0);
        root.dist = 0;
        remainingNodes.remove(root);
        updateDistances(remainingNodes, root);

        while (!remainingNodes.isEmpty()) {
            remainingNodes.sort(Comparator.comparing(NodeWithDist::getDist));
            NodeWithDist first = remainingNodes.get(0);
            if (first.dist == Integer.MAX_VALUE) {
                throw new IllegalStateException();
            }
            remainingNodes.remove(first);
            updateDistances(remainingNodes, first);

            Node[] nodes = {first.parent.node, first.node};
            Arrays.sort(nodes, Comparator.comparing(Node::getName));

            mst.add(new Edge(nodes[0], nodes[1], first.dist - first.parent.dist));
        }
        return mst;
    }

    private static void updateDistances(List<NodeWithDist> remainingNodes, NodeWithDist first) {
        for (Edge e : first.node.edges) {
            remainingNodes.stream()
                    .filter(nwd -> nwd.node.name.equals(e.secondElem.name))
                    .findFirst()
                    .ifPresent(nwd -> {
                        if (nwd.dist > first.dist + e.cost) {
                            nwd.dist = first.dist + e.cost;
                            nwd.parent = first;
                        }
                    });
        }
    }

    private static class NodeWithDist {
        final Node node;
        NodeWithDist parent;
        int dist;

        private NodeWithDist(Node node) {
            this.node = node;
            dist = Integer.MAX_VALUE;
        }

        private int getDist() {
            return dist;
        }
    }

}


