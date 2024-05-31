import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.util.*;

public class GraphMinimalTermsContraction implements GraphCharacteristic {


    @Override
    public Integer execute(Graph graph) {

        if (graph.getDirectType() != GraphType.UNDIRECTED) {
            throw new IllegalArgumentException("Граф должен быть неориентированный");
        }

        if (!isWeighted(graph)) {
            throw new IllegalArgumentException("Граф не взвешенный");
        }

        if (!isNonNegative(graph)) {
            throw new IllegalArgumentException("Граф не должен быть отрицательным");
        }

        if (graph.getVertices() == null || graph.getEdges() == null) {
            throw new IllegalArgumentException("Граф не содержит вершин или ребер");
        }

        if (!isConnectedGraph(graph)) {
            throw new IllegalArgumentException("Граф не связный");
        }

        return isMinimallyContractible(graph);
    }

    private Integer isMinimallyContractible(Graph graph) {

        if (graph.getVertexCount() == 0) {
            return 0;
        }

        int vertexCount = graph.getVertexCount();
        List<Edge> edges = graph.getEdges();
        Map<UUID, Vertex> vertices = graph.getVertices();

        // Приоритетная очередь для выбора ребра с наименьшим весом
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        // Начинаем с первой вершины
        UUID startVertex = vertices.keySet().iterator().next();
        Set<UUID> inMST = new HashSet<>();
        inMST.add(startVertex);

        for (Edge edge : edges) {
            // Добавляем в приоритетную очередь все ребра, инцидентные начальной вершине
            if (edge.getFromV().equals(startVertex) || edge.getToV().equals(startVertex)) {
                pq.add(edge);
            }
        }

        int mstWeight = 0;

        while (!pq.isEmpty() && inMST.size() < vertexCount) {
            // Извлекаем из приоритетной очереди ребро с наименьшим весом тк pq отсортирован по весу
            Edge edge = pq.poll();

            UUID fromV = edge.getFromV();
            UUID toV = edge.getToV();

            // Проверяем образует ли ребро цикл
            if (inMST.contains(fromV) && inMST.contains(toV)) {
                continue;
            }

            mstWeight += edge.getWeight();

            // Определяем какую из вершин ребра нужно добавить в MST поскольку одна из них уже присутствует
            UUID newVertex = inMST.contains(fromV) ? toV : fromV;
            inMST.add(newVertex);

            // Добавляем все ребра новой вершины в очередь приоритетов
            for (Edge nextEdge : edges) {
                // добавляем в приоритетную очередь все ребра, инцидентные новой вершине, включенной в MST
                if (nextEdge.getFromV().equals(newVertex) || nextEdge.getToV().equals(newVertex)) {
                    pq.add(nextEdge);
                }
            }
        }

        return mstWeight;
    }

    /**
     * Проверяет, является ли граф связным.
     * Граф является связным, если все вершины графа доступны, начиная с любой вершины.
     *
     * @return true, если граф связный, иначе false
     */
    private boolean isConnectedGraph(Graph graph) {
        Map<UUID, Boolean> visited = new HashMap<>();
        for (Vertex vertex : graph.getVertices().values()) {
            visited.put(vertex.getId(), false);
        }

        dfs(graph, graph.getVertices().values().iterator().next().getId(), visited);

        return !visited.containsValue(false);
    }

    // Обход в глубину
    private void dfs(Graph graph, UUID currentVertexId, Map<UUID, Boolean> visited) {
        visited.put(currentVertexId, true);
        for (Edge edge : graph.getEdges()) {
            if (edge.getFromV().equals(currentVertexId) && !visited.get(edge.getToV())) {
                dfs(graph, edge.getToV(), visited);
            }
            if (edge.getToV().equals(currentVertexId) && !visited.get(edge.getFromV())) {
                dfs(graph, edge.getFromV(), visited);
            }
        }
    }

    /**
     * Проверяет, является ли граф взвешенным.
     * Граф считается взвешенным, если хотя бы одно ребро имеет ненулевой вес.
     *
     * @return true, если граф взвешенный, иначе false
     */
    private boolean isWeighted(Graph graph) {
        for (Edge edge : graph.getEdges()) {
            try {
                if (edge.getWeight() == null && edge.getWeight() == 0) {
                    return false;
                }
            } catch (NullPointerException e) {
                return false;
            }
        }
        return true;
    }

    /**
     * Проверяет, является ли граф неотрицательным.
     * Граф считается неотрицательным, если все вершины и ребра имеют неотрицательные веса.
     *
     * @return true, если граф неотрицательный, иначе false
     */
    private boolean isNonNegative(Graph graph) {
        for (Vertex vertex : graph.getVertices().values()) {
            if (vertex.getWeight() != null && vertex.getWeight() < 0) {
                return false;
            }
        }

        for (Edge edge : graph.getEdges()) {
            if (edge.getWeight() != null && edge.getWeight() < 0) {
                return false;
            }
        }

        return true;
    }
}
