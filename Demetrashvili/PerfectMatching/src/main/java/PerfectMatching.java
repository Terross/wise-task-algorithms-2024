import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;

import java.util.*;

import java.util.ArrayList;
import java.util.List;

public class PerfectMatching implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        List<Edge> edgesToCheck = getEdgesToCheck(graph);
        if (isPerfectMatching(graph, edgesToCheck)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPerfectMatching(Graph graph, List<Edge> edgesToCheck) {
        for (UUID vertexId : graph.getVertices().keySet()) {
            boolean incidentToSingleEdge = false;
            for (Edge edge : edgesToCheck) {
                if (edge.getFromV().equals(vertexId) || edge.getToV().equals(vertexId)) {
                    if (incidentToSingleEdge) {
                        return false;
                    }
                    incidentToSingleEdge = true;
                }
            }
            if (!incidentToSingleEdge) {
                return false;
            }
        }

        return true;
    }

    public static List<Edge> getEdgesToCheck(Graph graph) {
        List<Edge> edgesToCheck = new ArrayList<>();
        for (Edge edge : graph.getEdges()) {
            if (edge.getColor() != Color.gray) {
                edgesToCheck.add(edge);
            }
        }
        return edgesToCheck;
    }
}