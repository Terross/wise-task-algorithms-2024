package ru.leti;

import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;
import java.util.*;

public class clawNumber implements GraphCharacteristic {

    @Override
    public Integer execute(Graph graph) {
        // Создание списка смежности для заданного графа
        HashMap<UUID, List<Vertex>> adjList = createAdjList(graph);

        int clawCount = 0;

        // Перебираем все вершины графа
        for (Vertex vertex : graph.getVertices().values()) {
            // Проверяем, является ли текущая вершина центром лапы
            boolean isCenter = true;
            int neighborCount = 0;
            for (Vertex neighbor : adjList.get(vertex.getId())) {
                neighborCount++;
                // Проверяем, что нет рёбер между соседними вершинами
                for (Vertex otherNeighbor : adjList.get(vertex.getId())) {
                    if (adjList.get(neighbor.getId()).contains(otherNeighbor)) {
                        isCenter = false;
                        break;
                    }
                }
                if (!isCenter) {
                    break;
                }
            }
            // Если вершина является центром лапы и имеет 3 соседа, увеличиваем счётчик лап
            if (isCenter && neighborCount == 3) {
                clawCount++;
            }
        }

        return clawCount;
    }

    // Создание списка смежности для заданного графа
    private HashMap<UUID, List<Vertex>> createAdjList(Graph graph) {
        Map<UUID, Vertex> vertices = graph.getVertices();
        List<Edge> edges = graph.getEdges();
        HashMap<UUID, List<Vertex>> adjList = new HashMap<>();
        for (Vertex vertex : vertices.values()) {
            adjList.put(vertex.getId(), new ArrayList<>());
        }

        for (Edge edge : edges) {
            adjList.get(edge.getFromV()).add(vertices.get(edge.getToV()));
            adjList.get(edge.getToV()).add(vertices.get(edge.getFromV()));
        }
        return adjList;
    }
}
