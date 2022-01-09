package pl.edu.pw.ee;

import pl.edu.pw.ee.services.MinSpanningTree;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class KruskalAlgorithm implements MinSpanningTree {

    public String findMST(String path) {
        List<Node> graph = new FileReader().fileToGraph(path);
        List<Edge> mst = findMST(graph);
        return mst.stream().map(Edge->Edge.toString()).collect(Collectors.joining("|"));
    }

    public List<Edge> findMST(List<Node> graph) {
        Disjoint disjointNode = new Disjoint(graph);

        List<Edge> mst = graph.stream()
                .flatMap(n -> n.getEdges().stream())
                .sorted(Comparator.comparing(Edge->Edge.getWeight()))
                .filter(disjointNode::trySame)
                .collect(Collectors.toList());

        if (mst.size() != graph.size() - 1) {
            throw new IllegalStateException();
        }
        return mst;
    }

    private static class Disjoint {
        final List<NodeWithParent> disjointNode;

        private Disjoint(List<Node> graph) {
            disjointNode = graph.stream().map(NodeWithParent::new).collect(Collectors.toList());
        }

        private NodeWithParent find(List<NodeWithParent> disjointNode, Node node) {
            return disjointNode.stream()
                    .filter(nwp -> nwp.node == node)
                    .findFirst()
                    .orElseThrow(AssertionError::new);
        }

        boolean trySame(Edge edge) {
            NodeWithParent frstParent = find(disjointNode, edge.firstElem).highestParent();
            NodeWithParent secondParent = find(disjointNode, edge.secondElem).highestParent();
            if (frstParent == secondParent) {
                return false;
            }
            frstParent.parent = secondParent;
            return true;
        }

        static class NodeWithParent {
            final Node node;
            NodeWithParent parent = null;

            private NodeWithParent(Node node) {
                this.node = node;
            }

            NodeWithParent highestParent() {
                return parent == null ? this : parent.highestParent();
            }
        }
    }
    
}
