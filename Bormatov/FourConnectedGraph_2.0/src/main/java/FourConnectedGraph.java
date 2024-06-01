//package ru.leti;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class FourConnectedGraph implements GraphProperty {
    @Override
    // false- граф не является 4-связным, true - является
    public boolean execute(Graph graph) {
        // Проверяем, достаточно ли вершин в графе для 4-связности
        if (graph.getVertexCount() < 5) { // если меньше 5, то граф сразу не подходит
            return false;
        }

        // Перебираем все комбинации из 3 вершин, которые можно удалить
        Set<UUID> vertices = graph.getVertices().keySet();
        for (UUID v1 : vertices) {
            for (UUID v2 : vertices) {
                for (UUID v3 : vertices) {
                    // Создаем копию графа без выбранных вершин
                    Graph copy = createGraphCopyWithoutVertices(graph, v1, v2, v3);
                    // Проверяем связность копии
                    if (!isGraphConnected(copy)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // проверка графа на связность с помощью поиска в глубину
    public static boolean isGraphConnected(Graph graph) {
        if (graph.getVertices().isEmpty()) { //проверка является ли граф пустым
            return true;
        }

        // создаем мнржество посещенных вершин
        Set<UUID> visited = new HashSet<>();
        //старт вершина
        UUID startVertexId = graph.getVertices().keySet().iterator().next();
        // запускаем поиск в глубину
        dfs(graph, startVertexId, visited);

        return visited.size() == graph.getVertexCount(); // совпадает ли размер множества посещенных вершин с размером графа
    }

    // добавляем ID текущей вершины currentVertexId в множество visited, обозначая, что эта вершина была посещена.
    private static void dfs(Graph graph, UUID currentVertexId, Set<UUID> visited) {
        visited.add(currentVertexId);
        //  цикл перебирает все ребра в графе
        for (Edge edge : graph.getEdges()) {
            UUID nextVertexId = edge.getFromV().equals(currentVertexId) ? edge.getToV() : edge.getToV().equals(currentVertexId) ? edge.getFromV() : null;
            if (nextVertexId != null && !visited.contains(nextVertexId)) {
                dfs(graph, nextVertexId, visited);
            }
        }
    }

    // создание копии без выбранных вершин
    public static Graph createGraphCopyWithoutVertices(Graph original, UUID... verticesToRemove) {
        // вершины, которые должны быть исключены
        Set<UUID> verticesToExclude = new HashSet<>(Arrays.asList(verticesToRemove));
        // список новый вершин и ребер
        Map<UUID, Vertex> newVertices = new HashMap<>();
        List<Edge> newEdges = new ArrayList<>();

        // Если текущий идентификатор вершины не находится в наборе verticesToExclude, то эта вершина копируется в новый контейнер newVertices.
        for (Map.Entry<UUID, Vertex> entry : original.getVertices().entrySet()) {
            if (!verticesToExclude.contains(entry.getKey())) {
                newVertices.put(entry.getKey(), entry.getValue());
            }
        }

        // Если ни начальная, ни конечная вершины ребра не находятся в наборе verticesToExclude, то это ребро копируется в новый контейнер newEdges.
        for (Edge edge : original.getEdges()) {
            if (!verticesToExclude.contains(edge.getFromV()) && !verticesToExclude.contains(edge.getToV())) {
                newEdges.add(edge);
            }
        }

        // возвращаем новый граф
        return new Graph(original.getDirectType(), newVertices.size(), newEdges.size(), newVertices, newEdges);
    }
}
