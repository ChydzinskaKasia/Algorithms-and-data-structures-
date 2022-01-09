package pl.edu.pw.ee;

import pl.edu.pw.ee.services.MinSpanningTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class KruskalAlgorithm implements MinSpanningTree {

    public String findMST(String path) {
        List<Node> graph = new FileReader().fileToGraph(path);
        List<Edge> mst = findMST(graph);
        return mst.stream().map(Edge::toString).collect(Collectors.joining("|"));
    }

    public List<Edge> findMST(List<Node> graph) {
        DisjointSet disjointNodeSet = new DisjointSet(graph);

        List<Edge> mst = graph.stream()
                .flatMap(n -> n.getEdges().stream())
                .sorted(Comparator.comparing(Edge::getWeight))
                .filter(disjointNodeSet::tryUnion)
                .collect(Collectors.toList());

        if (mst.size() != graph.size() - 1) {
            throw new IllegalStateException();
        }
        return mst;
    }


    private static class DisjointSet {
        final List<NodeWithParent> disjointNodeSet;

        private DisjointSet(List<Node> graph) {
            disjointNodeSet = graph.stream().map(NodeWithParent::new).collect(Collectors.toList());
        }

        private NodeWithParent find(List<NodeWithParent> disjointNodeSet, Node node) {
            return disjointNodeSet.stream()
                    .filter(nwp -> nwp.node == node)
                    .findFirst()
                    .orElseThrow(AssertionError::new);
        }

        boolean tryUnion(Edge edge) {
            NodeWithParent parent1 = find(disjointNodeSet, edge.from).findHighestParent();
            NodeWithParent parent2 = find(disjointNodeSet, edge.to).findHighestParent();
            if (parent1 == parent2) {
                return false;
            }
            parent1.parent = parent2;
            return true;
        }

        static class NodeWithParent {
            final Node node;
            NodeWithParent parent = null;

            private NodeWithParent(Node node) {
                this.node = node;
            }

            NodeWithParent findHighestParent() {
                return parent == null ? this : parent.findHighestParent();
            }
        }
    }
    
}
