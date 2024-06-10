package ru.leti;

import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import com.mathsystem.api.graph.model.Edge;

import java.util.*;

public class TriangleDegreeThreeChecker implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {

        Map<UUID, Integer> vertexDegrees = calculateVertexDegrees(graph);

        List<Set<UUID>> triangles = findTriangles(graph);

        for (Set<UUID> triangle : triangles) {
            int degreeThreeCount = 0;
            for (UUID vertex : triangle) {
                if (vertexDegrees.get(vertex) >= 3) {
                    degreeThreeCount++;
                }
            }
            if (degreeThreeCount < 2) {
                return false;
            }
        }
        return true;
    }

    private Map<UUID, Integer> calculateVertexDegrees(Graph graph) {
        Map<UUID, Integer> vertexDegrees = new HashMap<>();
        for (Edge edge : graph.getEdges()) {
            UUID fromV = edge.getFromV();
            UUID toV = edge.getToV();
            vertexDegrees.put(fromV, vertexDegrees.getOrDefault(fromV, 0) + 1);
            vertexDegrees.put(toV, vertexDegrees.getOrDefault(toV, 0) + 1);
        }
        return vertexDegrees;
    }

    private List<Set<UUID>> findTriangles(Graph graph) {
        List<Set<UUID>> triangles = new ArrayList<>();
        Map<UUID, Set<UUID>> adjacencyList = new HashMap<>();
        for (Edge edge : graph.getEdges()) {
            UUID fromV = edge.getFromV();
            UUID toV = edge.getToV();
            adjacencyList.putIfAbsent(fromV, new HashSet<>());
            adjacencyList.putIfAbsent(toV, new HashSet<>());
            adjacencyList.get(fromV).add(toV);
            adjacencyList.get(toV).add(fromV);
        }

        for (UUID vertex : adjacencyList.keySet()) {
            Set<UUID> neighbors = adjacencyList.get(vertex);
            for (UUID neighbor1 : neighbors) {
                for (UUID neighbor2 : neighbors) {
                    if (neighbor1.equals(neighbor2)) continue; // Пропускаем, если это одна и та же вершина
                    if (adjacencyList.get(neighbor1).contains(neighbor2)) {
                        Set<UUID> triangle = new HashSet<>();
                        triangle.add(vertex);
                        triangle.add(neighbor1);
                        triangle.add(neighbor2);
                        // Проверка на уникальность треугольника
                        if (!triangles.contains(triangle)) {
                            triangles.add(triangle);
                        }
                    }
                }
            }
        }
        return triangles;
    }
}