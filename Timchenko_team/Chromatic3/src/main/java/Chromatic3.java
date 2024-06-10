import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class Chromatic3 implements GraphProperty {

    // Создаёт список смежности для графа
    private Map<UUID, List<UUID>> createAdjacencyList(Graph graph) {
        Map<UUID, List<UUID>> adjacencyList = new HashMap<>();
        for (UUID vertex : graph.getVertices().keySet()) {
            adjacencyList.put(vertex, new ArrayList<>());
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

    @Override
    public boolean execute(Graph graph) {
        // Устанавливаем всем вершинам цвет, который при раскраске использовать не будем
        setAllVerticesColor(graph, Color.yellow);

        // Создание списка смежности и вычисление степеней для каждой вершины
        Map<UUID, List<UUID>> adjacencyList = createAdjacencyList(graph);
        Map<UUID, Integer> degrees = calculateDegrees(adjacencyList);

        // Получаем отсортированный список вершин по невозрастанию степеней
        List<UUID> sortedVertices = new ArrayList<>(degrees.keySet());
        sortedVertices.sort((v1, v2) -> degrees.get(v2) - degrees.get(v1));

        // Используем массив из 5 цветов для раскраски
        Color[] colors = {Color.red, Color.green, Color.blue};
        Set<Color> usedColors = new HashSet<>();

        // Раскраска вершин
        for (UUID vertex : sortedVertices) {
            Set<Color> neighborColors = new HashSet<>();
            for (UUID neighbor : adjacencyList.get(vertex)) {
                neighborColors.add(graph.getVertices().get(neighbor).getColor());
            }
            Color available_color = Color.yellow;
            // Находим первый доступный цвет для текущей вершины
            for (Color color : colors) {
                if (!neighborColors.contains(color)) {
                    available_color = color;
                    break;
                }
            }

            // Если вершина осталась с начальным цветом, значит не удалось раскрасить граф
            if (available_color == Color.yellow) {
                return false;
            }
            graph.getVertices().get(vertex).setColor(available_color);
            usedColors.add(graph.getVertices().get(vertex).getColor());
        }

        // Проверяем, использованы ли все цвета
        return usedColors.size() == 3;
    }
}
