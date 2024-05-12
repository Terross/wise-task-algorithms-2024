package ru.leti;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.util.*;

public class CountDeficit implements GraphCharacteristic {
    @Override
    public Integer execute(Graph graph) {
        Map<UUID, Vertex> vertices = graph.getVertices();
        List<Edge> edges = graph.getEdges();

        int maxMatchingSize = findMaxMatchingSize(vertices, edges);
        int numOfVertices = vertices.size();
        int deficit = numOfVertices - maxMatchingSize;

        return deficit;
    }

    private static int findMaxMatchingSize(Map<UUID, Vertex> vertices, List<Edge> edges) {
        Map<UUID, UUID> matching = new HashMap<>();
        boolean[] used = new boolean[vertices.size()];
        Map<UUID, Integer> vertexIndices = new HashMap<>();

        // Инициализация отображения индексов вершин
        int index = 0;
        for (UUID vertexId : vertices.keySet()) {
            vertexIndices.put(vertexId, index++);
        }

        for (UUID vertexId : vertices.keySet()) {
            matching.put(vertexId, null);
        }

        int matchingSize = 0;
        for (UUID vertexId : vertices.keySet()) {
            Arrays.fill(used, false);
            if (dfs(vertexId, edges, matching, used, vertexIndices)) {
                matchingSize++;
            }
        }

        return matchingSize*2;
    }

    private static boolean dfs(UUID v, List<Edge> edges, Map<UUID, UUID> matching, boolean[] used, Map<UUID, Integer> vertexIndices) {
        int index = vertexIndices.get(v);
        if (used[index]) return false;
        used[index] = true;

        for (Edge edge : edges) {
            if (edge.getFromV().equals(v)) { // Проверка начальной вершины ребра
                UUID to = edge.getToV();
                if (matching.get(to) == null || dfs(matching.get(to), edges, matching, used, vertexIndices)) {
                    matching.put(to, v);
                    return true;
                }
            }
        }
        return false;
    }
}
