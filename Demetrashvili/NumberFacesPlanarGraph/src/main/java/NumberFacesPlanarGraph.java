import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.util.*;

public class NumberFacesPlanarGraph implements GraphCharacteristic {

    private int vertexesCount = 0;
    private ArrayList<ArrayList<Integer>> adjacencyMatrix;

    @Override
    public Integer execute(Graph graph) {
        if (!isPlanar(graph)) {
            throw new IllegalArgumentException("Граф должен быть планарным");
        }

        if (graph.getVertices() == null || graph.getEdges() == null) {
            throw new IllegalArgumentException("Граф не содержит вершин или ребер");
        }

        int edgeCount = graph.getEdgeCount();
        int vertexCount = graph.getVertexCount();

        if (!isConnectedGraph(graph)) {
            throw new IllegalArgumentException("Граф не является связным");
        }

        // Подсчет числа граней по формуле Эйлера(Формула Эйлера: F = E - V + 2, где F - число граней,
        // E - число ребер, V - число вершин.)
        return edgeCount - vertexCount + 2;
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
     * Проверяет, является ли граф планарным.
     *
     * Использьзуется теорема Понтрягина — Куратовского.  граф планарен тогда и только тогда, когда он не содержит
     * полного графа с пятью вершинами (K5) и полного двудольного графа с тремя вершинами в каждой доле (K3,3).
     *
     * @return true, если граф планарный, иначе false
     */
    private boolean isPlanar(Graph graph) {

        setValues(graph);

        if (vertexesCount <= 4) {
            return true;
        }

        boolean k5 = checkForK5();
        boolean k33 = checkForK33();
        return !(k5 || k33);
    }

    private void setValues(Graph graph) {
        vertexesCount = graph.getVertexCount();
        int edges_count = graph.getEdgeCount();
        List<Edge> edges = graph.getEdges();
        List<UUID> vertexes = new ArrayList<>();
        adjacencyMatrix = new ArrayList<>(vertexesCount);

        for (int i = 0; i < vertexesCount; i++)
            adjacencyMatrix.add(new ArrayList<>());

        for (int i = 0; i < edges_count; i++) {
            Edge edge = edges.get(i);
            if (!vertexes.contains(edge.getFromV()))
                vertexes.add(edge.getFromV());
            if (!vertexes.contains(edge.getToV()))
                vertexes.add(edge.getToV());
            addEdge(vertexes.indexOf(graph.getEdges().get(i).getFromV()), vertexes.indexOf(graph.getEdges().get(i).getToV()));
        }
    }

    private void addEdge(int v, int w) {
        adjacencyMatrix.get(v).add(w);
        adjacencyMatrix.get(w).add(v);
    }

    private boolean checkForK5() {
        for (int x = 0; x < vertexesCount; x++)
            for (int y : adjacencyMatrix.get(x))
                for (int z : adjacencyMatrix.get(y))
                    if (adjacencyMatrix.get(x).contains(z))
                        for (int w : adjacencyMatrix.get(z))
                            if (adjacencyMatrix.get(y).contains(w) && adjacencyMatrix.get(x).contains(w))
                                for (int u : adjacencyMatrix.get(w))
                                    if (adjacencyMatrix.get(x).contains(u) && adjacencyMatrix.get(y).contains(u) && adjacencyMatrix.get(z).contains(u)) {
                                        Set<Integer> vertexSet = new HashSet<>();
                                        vertexSet.add(x);
                                        vertexSet.add(y);
                                        vertexSet.add(z);
                                        vertexSet.add(w);
                                        vertexSet.add(u);
                                        if (vertexSet.size() == 5)
                                            return true;
                                    }

        return false;
    }

    private boolean checkForK33() {
        for (int x = 0; x < vertexesCount; x++)
            for (int y : adjacencyMatrix.get(x))
                for (int z : adjacencyMatrix.get(y))
                    for (int w : adjacencyMatrix.get(z))
                        if (adjacencyMatrix.get(x).contains(w))
                            for (int u : adjacencyMatrix.get(w))
                                if (adjacencyMatrix.get(y).contains(u))
                                    for (int v : adjacencyMatrix.get(u))
                                        if (adjacencyMatrix.get(x).contains(v) && adjacencyMatrix.get(z).contains(v)) {
                                            Set<Integer> vertexSet = new HashSet<>();
                                            vertexSet.add(x);
                                            vertexSet.add(y);
                                            vertexSet.add(z);
                                            vertexSet.add(w);
                                            vertexSet.add(u);
                                            vertexSet.add(v);
                                            if (vertexSet.size() == 6)
                                                return true;
                                        }

        return false;
    }
}

