import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class MarkedVertexTheGraphBarrier implements GraphProperty {

    @Override
    public boolean execute(Graph graph) {
        if (graph.getVertices() == null || graph.getEdges() == null) {
            throw new IllegalArgumentException("Граф не содержит вершин или ребер");
        }

        return isBarrier(graph);
    }

    private boolean isBarrier(Graph graph) {
        int num_secelcted_vertex = 0;
        for (Vertex i : graph.getVertices().values())
        {
            if (i.getColor() != Color.gray)
                num_secelcted_vertex++;
        }
        return (countOddComponents(graph) - num_secelcted_vertex) == NumOfFreeVertex(graph);
    }

    /**
     * Подсчет количества несвязанных вершин
     */
    private int NumOfFreeVertex(Graph graph) {
        // Множество для хранения идентификаторов вершин, у которых есть инцидентные ребра
        Set<UUID> verticesWithEdges = new HashSet<>();

        // Проверка всех ребер и добавление инцидентных вершин в множество
        List<Edge> edges = graph.getEdges();
        for (Edge edge : edges) {
            verticesWithEdges.add(edge.getFromV());
            verticesWithEdges.add(edge.getToV());
        }
        // Подсчет вершин без пар (без инцидентных ребер)
        int isolatedVerticesCount = 0;
        Map<UUID, Vertex> vertices = graph.getVertices();
        for (UUID vertexId : vertices.keySet()) {
            if (!verticesWithEdges.contains(vertexId)) {
                isolatedVerticesCount++;
            }
        }
        return isolatedVerticesCount;
    }

    private static int countOddComponents(Graph graph) {
        // Проверка на наличие вершин в графе
        Map<UUID, Vertex> temp_vertex = graph.getVertices();

        Map<UUID, Vertex> selected_vertexes = new HashMap<>();
        /** Находим не выбранные вершины (Хранятся вершины не из множества S) odd(G-S)*/
        for (UUID i : temp_vertex.keySet()) {
            if (temp_vertex.get(i).getColor() == Color.gray)
            {
                selected_vertexes.put(i, temp_vertex.get(i));
            }
        }

        List<Edge> unselected_edges = new ArrayList<>();
        /** Находим смежные с вершинами ребра */
        for (Edge edge : graph.getEdges()) {
            if (edge.getColor() == Color.gray)
            {
                unselected_edges.add(edge);
            }
        }

        // Инициализация необходимых структур данных
        Map<UUID, List<UUID>> adjacencyList = buildAdjacencyList(unselected_edges);
        Set<UUID> visited = new HashSet<>();
        int oddComponentCount = 0;

        // Поиск компонент связности и подсчет тех, что имеют нечетное число вершин
        for (UUID vertexId : selected_vertexes.keySet()) {
            if (!visited.contains(vertexId)) {
                int componentSize = bfsComponentSize(vertexId, adjacencyList, visited);
                if (componentSize % 2 != 0) {
                    oddComponentCount++;
                }
            }
        }

        return oddComponentCount;
    }

    private static Map<UUID, List<UUID>> buildAdjacencyList(List<Edge> edges) {
        Map<UUID, List<UUID>> adjacencyList = new HashMap<>();
        for (Edge edge : edges) {
            adjacencyList.computeIfAbsent(edge.getFromV(), k -> new ArrayList<>()).add(edge.getToV());
            adjacencyList.computeIfAbsent(edge.getToV(), k -> new ArrayList<>()).add(edge.getFromV());
        }
        return adjacencyList;
    }


    private static int bfsComponentSize(UUID startVertex, Map<UUID, List<UUID>> adjacencyList, Set<UUID> visited) {
        Queue<UUID> queue = new LinkedList<>();
        queue.add(startVertex);
        visited.add(startVertex);
        int size = 0;

        while (!queue.isEmpty()) {
            UUID vertex = queue.poll();
            size++;
            for (UUID neighbor : adjacencyList.getOrDefault(vertex, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return size;
    }


}