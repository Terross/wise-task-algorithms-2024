import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class FourPeriodic implements GraphProperty {

    @Override
    public boolean execute(Graph graph) {
        Map<UUID, Vertex> vertices = graph.getVertices();
        Map<UUID, List<UUID>> adjacencyList = new HashMap<>();

        for (Edge edge : graph.getEdges()) {
            adjacencyList.computeIfAbsent(edge.getFromV(), k -> new ArrayList<>()).add(edge.getToV());
            adjacencyList.computeIfAbsent(edge.getToV(), k -> new ArrayList<>()).add(edge.getFromV());
        }

        for (UUID startVertex : vertices.keySet()) {
            if (!dfsCheck(vertices, adjacencyList, startVertex)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfsCheck(Map<UUID, Vertex> vertices, Map<UUID, List<UUID>> adjacencyList, UUID startVertex) {
        Stack<List<UUID>> stack = new Stack<>();
        stack.push(Collections.singletonList(startVertex));
        while (!stack.isEmpty()) {
            List<UUID> path = stack.pop();
            if (path.size() == 5) {
                UUID start = path.get(0);
                UUID end = path.get(4);
                if (!vertices.get(start).getColor().equals(vertices.get(end).getColor())) {
                    return false;
                }
            } else {
                UUID lastVertex = path.get(path.size() - 1);
                for (UUID neighbor : adjacencyList.getOrDefault(lastVertex, new ArrayList<>())) {
                    if (!path.contains(neighbor)) {
                        List<UUID> newPath = new ArrayList<>(path);
                        newPath.add(neighbor);
                        stack.push(newPath);
                    }
                }
            }
        }
        return true;
    }
}
