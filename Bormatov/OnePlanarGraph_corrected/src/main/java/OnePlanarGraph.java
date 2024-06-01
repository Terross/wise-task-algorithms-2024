import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

// Класс для проверки, является ли граф однородно 1-планарным
public class OnePlanarGraph implements GraphProperty {

    @Override
    public boolean execute(Graph graph) {
        // Проверяем базовые условия для однородно 1-планарного графа
        if(graph.getVertexCount() <= 4){
            return true; // Граф с 3 или менее вершинами считается однородно 1-планарным
        }

        // Основное условие для однородно 1-планарного графа
        if (graph.getEdgeCount() > 4 * graph.getVertexCount() - 8) {
            return false; // Если количество ребер превышает определенное значение, граф не является однородно 1-планарным
        }

        // Подготавливаем данные графа для дальнейшей обработки
        buildAdjacencyList(graph);
        resetVertexWeights(graph);

        // Используем очередь для обхода вершин графа
        Queue<UUID> queue = new LinkedList<>();
        int chromatic_number = 0; // Хранит максимальное значение цвета, присвоенное вершинам

        // Начинаем обход с первой вершины графа
        UUID startVertex = graph.getVertices().keySet().iterator().next();
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            UUID currentVertex = queue.poll(); // Берем следующую вершину из очереди

            // Создаем массив для отслеживания использованных цветов соседями текущей вершины
            boolean[] colorsUsedByNeighbors = new boolean[chromatic_number + 2];

            // Проходим по всем соседям текущей вершины
            for (UUID neighbor : adjacencyList.get(currentVertex)) {
                int color = graph.getVertices().get(neighbor).getWeight();
                if (color!= 0) { // Игнорируем вершины без цвета
                    colorsUsedByNeighbors[color] = true;
                }
            }

            // Находим первый свободный цвет среди соседей
            int color = 1;
            while (colorsUsedByNeighbors[color]) {
                color++;
            }

            // Присваиваем найденный цвет текущей вершине
            graph.getVertices().get(currentVertex).setWeight(color);
            if (color > chromatic_number) {
                chromatic_number = color; // Обновляем максимальное значение цвета, если оно больше текущего
            }

            // Добавляем непосещенные соседей в очередь для последующего обхода
            for (UUID neighbor : adjacencyList.get(currentVertex)) {
                if (graph.getVertices().get(neighbor).getWeight() == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Возвращаем результат проверки: если максимальное значение цвета не превышает 6, граф является однородно 1-планарным
        return chromatic_number <= 6;
    }

    private Map<UUID, List<UUID>> adjacencyList; // Список смежности для быстрого доступа к соседям вершин

    public OnePlanarGraph() {
        this.adjacencyList = new HashMap<>(); // Инициализация списка смежности
    }

    private void buildAdjacencyList(Graph graph) {
        // Строим список смежности для всех вершин графа
        adjacencyList.clear();
        for (var vertex : graph.getVertices().keySet()) {
            adjacencyList.put(vertex, new ArrayList<>());
        }
        // Заполняем список смежности данными о ребрах графа
        for (Edge edge : graph.getEdges()) {
            UUID from = edge.getFromV();
            UUID to = edge.getToV();
            adjacencyList.get(from).add(to);
            adjacencyList.get(to).add(from);
        }
    }

    private void resetVertexWeights(Graph graph) {
        // Сбрасываем веса вершин графа до нуля для корректного начала процесса
        for (var vertex : graph.getVertices().values()) {
            vertex.setWeight(0);
        }
    }
}
