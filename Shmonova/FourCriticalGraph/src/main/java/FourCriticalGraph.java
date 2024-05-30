import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import java.util.stream.Collectors;

import java.util.*;

public class FourCriticalGraph implements GraphProperty {
    private Integer getChromaticNumber(Graph graph) {
        Map<UUID, List<UUID>> adjacencyList;
        adjacencyList = new HashMap<>();
        for (var vertex : graph.getVertices().keySet()) {
            adjacencyList.put(vertex, new ArrayList<>());
        }
        for (Edge edge : graph.getEdges()) {
            UUID from = edge.getFromV();
            UUID to = edge.getToV();
            adjacencyList.get(from).add(to);
            adjacencyList.get(to).add(from);
        }
        for (var vertex : graph.getVertices().values()) {
            vertex.setWeight(0);
        }
        Queue<UUID> queue = new LinkedList<>();
        int chromatic_number = 0;

        UUID startVertex = graph.getVertices().keySet().iterator().next();
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            UUID currentVertex = queue.poll();
            boolean[] colorsUsedByNeighbors = new boolean[chromatic_number + 2]; // +2 для учёта нулевого цвета и потенциального нового максимального

            for (UUID neighbor : adjacencyList.get(currentVertex)) {
                int color = graph.getVertices().get(neighbor).getWeight();
                if (color != 0) {
                    colorsUsedByNeighbors[color] = true;
                }
            }

            int color = 1;
            while (colorsUsedByNeighbors[color]) {
                color++;
            }

            graph.getVertices().get(currentVertex).setWeight(color);
            if (color > chromatic_number) {
                chromatic_number = color;
            }

            for (UUID neighbor : adjacencyList.get(currentVertex)) {
                if (graph.getVertices().get(neighbor).getWeight() == 0) {
                    queue.add(neighbor);
                }
            }
        }
        return chromatic_number;
    }

    public static Graph removeVertexAndEdges(Graph graph, UUID vertexIdToRemove) {
        Graph newGraph = new Graph(graph.getDirectType(), graph.getVertexCount(), graph.getEdgeCount(),
                new HashMap<>(graph.getVertices()), new ArrayList<>(graph.getEdges())); // создаем копию графа
        List<Edge> edges = newGraph.getEdges().stream()
                .filter(edge ->!edge.getFromV().equals(vertexIdToRemove) &&!edge.getToV().equals(vertexIdToRemove))
                .collect(Collectors.toList());
        newGraph.setEdges(edges);
        newGraph.getVertices().remove(vertexIdToRemove);
        return newGraph;
    }

    public static Graph removeEdge(Graph graph, Edge edgeToRemove) {
        // Создаем новый граф с тем же типом, количеством вершин и ребер, но без указанного ребра.
        Graph newGraph = new Graph(
                graph.getDirectType(),
                graph.getVertexCount(),
                graph.getEdgeCount() - 1, // Уменьшаем количество ребер на 1, так как одно будет удалено
                new HashMap<>(graph.getVertices()), // Копируем все вершины
                new ArrayList<>(graph.getEdges()).stream() // Копируем все ребра
                        .filter(e ->!e.equals(edgeToRemove)) // Исключаем удаляемое ребро
                        .collect(Collectors.toList()) // Собираем результат в список
        );
        return newGraph;
    }


    @Override
    public boolean execute(Graph graph) {
        if (getChromaticNumber(graph) == 4) {
            // Проверка при удалении каждой вершины
            for (UUID vertexId : graph.getVertices().keySet()) {
                Graph modifiedGraph = removeVertexAndEdges(graph, vertexId);
                if (getChromaticNumber(modifiedGraph)>=4) {
                    return false; // Если после удаления вершины хроматическое число не <4
                }
            }

            // Проверка при удалении каждого ребра
            for (Edge edge : graph.getEdges()) {
                Graph modifiedGraph = removeEdge(graph, edge);
                if (getChromaticNumber(modifiedGraph)>=4) {
                    return false; // Если после удаления ребра хроматическое число не <4
                }
            }
        } else {
            return false; // Если исходное хроматическое число не равно 4, граф не может быть 4-критическим
        }
        return true; // Граф является 4-критическим
    }
}
