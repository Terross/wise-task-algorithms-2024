import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class Chromatic3 implements GraphProperty {

    // Создаёт список смежности для графа
    private Map<UUID, List<UUID>> createAdjacencyList(Graph graph) {
        Map<UUID, List<UUID>> adjacencyList = new HashMap<>();
        Integer i = 1;
        for (UUID vertex : graph.getVertices().keySet()) {
            graph.getVertices().get(vertex).setLabel(i.toString());
            adjacencyList.put(vertex, new ArrayList<>());
            i++;
        }
        for (Edge edge : graph.getEdges()) {
            UUID fromVertex = edge.getFromV();
            UUID toVertex = edge.getToV();
            adjacencyList.get(fromVertex).add(toVertex);
            adjacencyList.get(toVertex).add(fromVertex);
        }
        return adjacencyList;
    }

    // Устанавливает всем вершинам графа указанный цвет
    private void setAllVerticesColor(Graph graph, Color color) {
        for (UUID vertex : graph.getVertices().keySet()) {
            graph.getVertices().get(vertex).setColor(color);
        }
    }

    // Вычисляет степени всех вершин
    private Map<UUID, Integer> calculateDegrees(Map<UUID, List<UUID>> adjacencyList) {
        Map<UUID, Integer> degrees = new HashMap<>();
        for (UUID vertex : adjacencyList.keySet()) {
            degrees.put(vertex, adjacencyList.get(vertex).size());
        }
        return degrees;
    }

    // Обновляет список смежности, удаляя вершину
    private Map<UUID, List<UUID>> updateAdjacencyList(Map<UUID, List<UUID>> adjacencyList, UUID removeVertex) {
        Map<UUID, List<UUID>> newAdjacencyList = new HashMap<>();
        for (UUID vertex : adjacencyList.keySet()) {
            if (!removeVertex.equals(vertex)) {
                newAdjacencyList.put(vertex, new ArrayList<>());
                for (UUID adjacent : adjacencyList.get(vertex)) {
                    if (!removeVertex.equals(adjacent)) {
                        newAdjacencyList.get(vertex).add(adjacent);
                    }
                }
            }
        }
        return newAdjacencyList;
    }

    @Override
    public boolean execute(Graph graph) {
        // Устанавливаем всем вершинам цвет, который при раскраске использовать не будем
        setAllVerticesColor(graph, Color.yellow);

        // Создание списка смежности и вычисление степеней для каждой вершины
        Map<UUID, List<UUID>> adjacencyList = createAdjacencyList(graph);
        Map<UUID, Integer> degrees = calculateDegrees(adjacencyList);

        // Получаем отсортированный список вершин по убыванию степеней
        List<UUID> sortedVertices = new ArrayList<>(degrees.keySet());
        sortedVertices.sort((v1, v2) -> degrees.get(v2) - degrees.get(v1));

        // Используем массив цветов для раскраски
        Color[] colors = {Color.red, Color.green, Color.blue, Color.pink, Color.gray};
        int colorIndex = 0;

        while (!sortedVertices.isEmpty()) {
            if (colorIndex >= colors.length) {
                return false; // Больше цветов нет
            }
            Color currentColor = colors[colorIndex];
            colorIndex++;

            List<UUID> toRemove = new ArrayList<>();
            UUID firstVertex = sortedVertices.remove(0);
            graph.getVertices().get(firstVertex).setColor(currentColor);
            toRemove.add(firstVertex);

            for (UUID vertex : new ArrayList<>(sortedVertices)) {
                boolean canColor = true;
                for (UUID neighbor : adjacencyList.get(vertex)) {
                    if (graph.getVertices().get(neighbor).getColor() == currentColor) {
                        canColor = false;
                        break;
                    }
                }
                if (canColor) {
                    graph.getVertices().get(vertex).setColor(currentColor);
                    toRemove.add(vertex);
                }
            }

            sortedVertices.removeAll(toRemove);

            // Обновление списка смежности и пересортировка оставшихся вершин
            for (UUID vertex : toRemove) {
                adjacencyList = updateAdjacencyList(adjacencyList, vertex);
            }

            Map<UUID, Integer> degrees_с = calculateDegrees(adjacencyList);
            sortedVertices.sort((v1, v2) -> degrees_с.get(v2) - degrees_с.get(v1));
        }

        return sortedVertices.isEmpty() && colorIndex == colors.length; // Все вершины раскрашены
    }
}
