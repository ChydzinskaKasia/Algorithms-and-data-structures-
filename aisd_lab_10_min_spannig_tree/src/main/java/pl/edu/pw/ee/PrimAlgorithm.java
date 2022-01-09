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
        return mst.stream().map(Edge-> Edge.toString()).collect(Collectors.joining("|"));
    }

    static List<Edge> findMST(List<Node> graph) {
        List<DisconnectedGraphs> remainingNodes = graph.stream().map(DisconnectedGraphs::new)
                .collect(Collectors.toList());
        List<Edge> mst = new ArrayList<>();

        DisconnectedGraphs root = remainingNodes.get(0);
        root.fetch = 0;
        remainingNodes.remove(root);
        updateFetches(remainingNodes, root);

        while (!remainingNodes.isEmpty()) {
            remainingNodes.sort(Comparator.comparing(DisconnectedGraphs->DisconnectedGraphs.getFetch()));
            DisconnectedGraphs first = remainingNodes.get(0);
            if (first.fetch == Integer.MAX_VALUE) {
                throw new IllegalStateException();
            }
            remainingNodes.remove(first);
            updateFetches(remainingNodes, first);

            Node[] nodes = { first.parent.node, first.node };
            Arrays.sort(nodes, Comparator.comparing(Node->Node.getName()));

            mst.add(new Edge(nodes[0], nodes[1], first.fetch - first.parent.fetch));
        }
        return mst;
    }

    private static void updateFetches(List<DisconnectedGraphs> remainingNodes, DisconnectedGraphs first) {
        for (Edge edge : first.node.edges) {
            remainingNodes.stream()
                    .filter(nwd -> nwd.node.name.equals(edge.secondElem.name))
                    .findFirst()
                    .ifPresent(nwd -> {
                        if (nwd.fetch > first.fetch + edge.cost) {
                            nwd.fetch = first.fetch + edge.cost;
                            nwd.parent = first;
                        }
                    });
        }
    }

    private static class DisconnectedGraphs {
        final Node node;
        DisconnectedGraphs parent;
        int fetch;

        private DisconnectedGraphs(Node node) {
            this.node = node;
            fetch = Integer.MAX_VALUE;
        }

        private int getFetch() {
            return fetch;
        }
    }

}