import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;


public class IsPlanarWithoutSomeEdge implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        PlanarityTester planarityTester = new PlanarityTester(new ArrayList<>(graph.getVertices().keySet()));

        for (Edge edge : graph.getEdges()) {
            planarityTester.addEdge(edge.getFromV(), edge.getToV());
        }

        // graph has to be not planar initially
        if (planarityTester.isPlanar()) {
            return false;
        }

        for (Edge edge : graph.getEdges()) {
            planarityTester.removeEdge(edge.getFromV(), edge.getToV());

            // check if graph is planar by deleting edge
            if (planarityTester.isPlanar()) {
                return true;
            }

            planarityTester.addEdge(edge.getFromV(), edge.getToV());
        }

        return false;
    }
}