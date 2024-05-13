//package ru.leti;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class FourConnectedGraph implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        // Проверяем, достаточно ли вершин в графе для 4-связности
        if (graph.getVertexCount() < 5) {
            return false;
        }

        // Перебираем все комбинации из 3 вершин, которые можно удалить
        Set<UUID> vertices = graph.getVertices().keySet();
        for (UUID v1 : vertices) {
            for (UUID v2 : vertices) {
                if (!v2.equals(v1)) {
                    for (UUID v3 : vertices) {
                        if (!v3.equals(v1) && !v3.equals(v2)) {
                            // Создаем копию графа без выбранных вершин
                            Graph copy = createGraphCopyWithoutVertices(graph, v1, v2, v3);
                            // Проверяем связность копии
                            if (!isGraphConnected(copy)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean isGraphConnected(Graph graph) {
        if (graph.getVertices().isEmpty()) {
            return true;
        }

        Set<UUID> visited = new HashSet<>();
        UUID startVertexId = graph.getVertices().keySet().iterator().next();
        dfs(graph, startVertexId, visited);

        return visited.size() == graph.getVertexCount();
    }

    private static void dfs(Graph graph, UUID currentVertexId, Set<UUID> visited) {
        visited.add(currentVertexId);
        for (Edge edge : graph.getEdges()) {
            UUID nextVertexId = edge.getToV().equals(currentVertexId) ? edge.getFromV() : edge.getToV();
            if (!visited.contains(nextVertexId)) {
                dfs(graph, nextVertexId, visited);
            }
        }
    }

    public static Graph createGraphCopyWithoutVertices(Graph original, UUID... verticesToRemove) {
        Set<UUID> verticesToExclude = new HashSet<>(Arrays.asList(verticesToRemove));
        Map<UUID, Vertex> newVertices = new HashMap<>();
        List<Edge> newEdges = new ArrayList<>();

        for (Map.Entry<UUID, Vertex> entry : original.getVertices().entrySet()) {
            if (!verticesToExclude.contains(entry.getKey())) {
                newVertices.put(entry.getKey(), entry.getValue());
            }
        }

        for (Edge edge : original.getEdges()) {
            if (!verticesToExclude.contains(edge.getFromV()) && !verticesToExclude.contains(edge.getToV())) {
                newEdges.add(edge);
            }
        }

        return new Graph(original.getDirectType(), newVertices.size(), newEdges.size(), newVertices, newEdges);
    }
}
