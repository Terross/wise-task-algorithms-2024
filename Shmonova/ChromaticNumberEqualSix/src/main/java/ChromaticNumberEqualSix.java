import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class ChromaticNumberEqualSix implements GraphProperty {
    private Map<UUID, List<UUID>> adjacencyList;

    public ChromaticNumberEqualSix() {
        this.adjacencyList = new HashMap<>();
    }

    private void buildAdjacencyList(Graph graph) {
        adjacencyList.clear();
        for (var vertex : graph.getVertices().keySet()) {
            adjacencyList.put(vertex, new ArrayList<>());
        }
        for (Edge edge : graph.getEdges()) {
            UUID from = edge.getFromV();
            UUID to = edge.getToV();
            adjacencyList.get(from).add(to);
            adjacencyList.get(to).add(from);
        }
    }

    private void resetVertexWeights(Graph graph) {
        for (var vertex : graph.getVertices().values()) {
            vertex.setWeight(0);
        }
    }

    @Override
    public boolean execute(Graph graph) {
        buildAdjacencyList(graph);
        resetVertexWeights(graph);

        Queue<UUID> queue = new LinkedList<>();
        int chromatic_number = 0;

        // Начинаем с первой вершины
        UUID startVertex = graph.getVertices().keySet().iterator().next();
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            UUID currentVertex = queue.poll();
            boolean[] colorsUsedByNeighbors = new boolean[chromatic_number + 2]; // +2 для учёта нулевого цвета и потенциального нового максимального

            // Проверяем цвета соседей
            for (UUID neighbor : adjacencyList.get(currentVertex)) {
                int color = graph.getVertices().get(neighbor).getWeight();
                if (color!= 0) { // Игнорируем вершины без цвета
                    colorsUsedByNeighbors[color] = true;
                }
            }

            // Находим первый свободный цвет
            int color = 1;
            while (colorsUsedByNeighbors[color]) {
                color++;
            }

            // Применяем найденный цвет к текущей вершине
            graph.getVertices().get(currentVertex).setWeight(color);
            if (color > chromatic_number) {
                chromatic_number = color;
            }

            // Добавляем непосещённых соседей в очередь
            for (UUID neighbor : adjacencyList.get(currentVertex)) {
                if (graph.getVertices().get(neighbor).getWeight() == 0) {
                    queue.add(neighbor);
                }
            }
        }
        return chromatic_number == 6;
    }
}
