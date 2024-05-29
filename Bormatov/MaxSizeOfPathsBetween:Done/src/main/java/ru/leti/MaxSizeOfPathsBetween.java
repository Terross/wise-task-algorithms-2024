package ru.leti;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.security.spec.ECGenParameterSpec;
import java.util.*;

public class MaxSizeOfPathsBetween implements GraphCharacteristic {
    private HashMap<UUID, List<Vertex>> creaste_table(Graph graph){
        Map<UUID, Vertex> vertix = graph.getVertices();
        HashMap<UUID, List<Vertex>> table = new HashMap<>();
        List<Edge> edges = graph.getEdges();

        for (var ver: vertix.values()){
            table.put(ver.getId(), new ArrayList<>());
        }

        for (var edge: edges){
            table.get(edge.getFromV()).add(vertix.get(edge.getToV()));
        }

        return table;
    }
    private int maxFlow(Graph graph, UUID sourceId, UUID sinkId, List<Edge> edges) {
        int maxFlow = 0;
        int pathFlow;

        HashMap<UUID, List<Vertex>> table = creaste_table(graph);

        // Пока существует увеличивающий путь в остаточной сети
        while ((pathFlow = augmentingPath(graph, table , sourceId, sinkId, edges)) > 0) {
            // Увеличиваем максимальный поток на значение потока увеличивающего пути
            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    private Edge getEdge(UUID current, UUID neighbour, List<Edge> edges){
        Edge curr_edge = new Edge();
        for (Edge edge: edges){
            if (current.equals(edge.getFromV()) && neighbour.equals(edge.getToV())){
                curr_edge = edge;
                break;
            }
        }
        return curr_edge;
    }

    private int augmentingPath(Graph graph, HashMap<UUID, List<Vertex>> table, UUID sourceId, UUID sinkId, List<Edge> edges) {
        // Используем алгоритм поиска в ширину (BFS) для поиска увеличивающего пути в графе
        Queue<UUID> queue = new LinkedList<>();
        Map<UUID, UUID> parentMap = new HashMap<>();
        Set<UUID> visited = new HashSet<>();

        queue.add(sourceId);
        visited.add(sourceId);
        boolean foundAugmentingPath = false;

        // Находим увеличивающий путь
        while (!queue.isEmpty()) {
            UUID current = queue.poll();
            visited.add(current);
            // Проверяем каждое ребро, ищем непосещенные вершины с ненулевым весом
            for (Vertex neighbour : table.get(current)) {
                Edge curr_edge = getEdge(current, neighbour.getId(), edges);

                if (!visited.contains(neighbour.getId()) && curr_edge.getWeight()> 0) {
                    queue.add(neighbour.getId());
                    parentMap.put(neighbour.getId(), current);

                    // Если достигли стока, нашли увеличивающий путь
                    if (neighbour.getId().equals(sinkId)) {
                        foundAugmentingPath = true;
                        break;
                    }
                }
            }

            if (foundAugmentingPath) {
                break;
            }
        }

        // Если увеличивающий путь найден, находим поток пути и обновляем рёбра
        if (foundAugmentingPath) {
            UUID current = sinkId;
            int pathFlow = Integer.MAX_VALUE;

            // Находим минимальный вес на пути
            while (!current.equals(sourceId)) {
                UUID parent = parentMap.get(current);
                Edge edge = getEdge(parent, current, edges);
                pathFlow = Math.min(pathFlow, edge.getWeight());
                current = parent;
            }

            // Обновляем веса рёбер на пути
            current = sinkId;
            while (!current.equals(sourceId)) {
                UUID parent = parentMap.get(current);
                Edge edge = getEdge(parent, current, edges);
                edge.setWeight(edge.getWeight() - pathFlow); // Уменьшаем вес ребра
                current = parent;
            }

            return pathFlow; // Возвращаем поток увеличивающего пути
        } else {
            return 0; // Увеличивающий путь не найден
        }
    }

    @Override
    public Integer execute(Graph graph) {
        Vertex source = null;
        Vertex sink = null;

        // Находим начальную и конечную вершины
        for (Vertex vertex : graph.getVertices().values()) {
            if (vertex.getColor() == Color.green) {
                source = vertex;
            } else if (vertex.getColor() == Color.red) {
                sink = vertex;
            }
        }

        if (source == null || sink == null) {
            // Если не удалось найти начальную или конечную вершину, возвращаем -1
            return -1;
        }
        List<Edge> edges = graph.getEdges();
        for (Edge edge: edges){
            edge.setWeight(1);
        }
        // Находим максимальный поток в графе
        return maxFlow(graph, source.getId(), sink.getId(), edges);
    }
}