import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

public class EdgeConnectivity implements GraphProperty {

    @Override
    public boolean execute(Graph graph) {
        var planarityTester = new PlanarityTester(graph.getVertices().keySet().stream().toList());

        for (Edge edge : graph.getEdges()) {
            planarityTester.addEdge(edge.getFromV(), edge.getToV());
        }

        // Graph must be non-planar initially
        if (planarityTester.isPlanar()) {
            return false;
        }

        for (var firstEdge : graph.getEdges()) {
            planarityTester.removeEdge(firstEdge.getFromV(), firstEdge.getToV());

            // Graph should remain non-planar after removing one edge
            // If that's the case then continue
            if (!planarityTester.isPlanar()) {
                for (var secondEdge : graph.getEdges()) {
                    if (edgesEquals(firstEdge, secondEdge)) {
                        continue;
                    }

                    planarityTester.removeEdge(secondEdge.getFromV(), secondEdge.getToV());

                    if (planarityTester.isPlanar()) {
                        System.out.println("Graph became planar:");
                        System.out.println("Removed edge #1: " + firstEdge.getFromV() + " -> " + firstEdge.getToV());
                        System.out.println("Removed edge #21: " + secondEdge.getFromV() + " -> " + secondEdge.getToV());
                        return true;
                    }

                    planarityTester.addEdge(secondEdge.getFromV(), secondEdge.getToV());
                }

            }

            planarityTester.addEdge(firstEdge.getFromV(), firstEdge.getToV());
        }

        return false;
    }

    private boolean edgesEquals(Edge a, Edge b) {
        var fromEquals = a.getFromV().equals(b.getFromV());
        var toEquals = a.getToV().equals(b.getToV());
        return fromEquals && toEquals;
    }

}
