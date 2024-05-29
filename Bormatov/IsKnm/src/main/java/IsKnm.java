import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class IsKnm implements GraphProperty {

    @Override
    public boolean execute(Graph graph) {
        HashSet<UUID> redVertices = new HashSet<>(),
                blueVertices = new HashSet<>();
        for (Vertex vertex : graph.getVertices().values()) {
            if (vertex.getColor() == Color.red) redVertices.add(vertex.getId());
            else if (vertex.getColor() == Color.blue) blueVertices.add(vertex.getId());
        }
        if (!checkNumberOfEdges(graph, redVertices.size(), blueVertices.size()))
            return false;

        Map<UUID, Set<UUID>> connectedVerticesMap = createConnectedVerticesMap(graph);
        boolean areRedVerticesConnected = areAllVerticesConnected(connectedVerticesMap, redVertices, blueVertices),
                areBlueVerticesConnected = areAllVerticesConnected(connectedVerticesMap, blueVertices, redVertices);

        return areRedVerticesConnected && areBlueVerticesConnected;
    }

    private boolean checkNumberOfEdges(Graph graph, int n, int m) {
        return graph.getEdgeCount() == m * n;
    }

    private Map<UUID, Set<UUID>> createConnectedVerticesMap(Graph graph) {
        Map<UUID, Set<UUID>> connectedVerticesMap = new HashMap<>();
        for (Edge edge : graph.getEdges()) {
            connectedVerticesMap.computeIfAbsent(edge.getToV(), k -> new HashSet<>()).add(edge.getFromV());
            connectedVerticesMap.computeIfAbsent(edge.getFromV(), k -> new HashSet<>()).add(edge.getToV());
        }
        return connectedVerticesMap;
    }

    private boolean areAllVerticesConnected(Map<UUID, Set<UUID>> connectedVerticesMap, HashSet<UUID> partToCheck, HashSet<UUID> partConnected) {
        if (partConnected.isEmpty())
            return false;
        for (UUID vertex_id : partToCheck) {
            if (!Objects.equals(connectedVerticesMap.get(vertex_id), partConnected))
                return false;
        }
        return true;
    }

}