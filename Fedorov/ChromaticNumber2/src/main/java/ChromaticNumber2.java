import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import com.mathsystem.domain.graph.repository.Color;

import java.util.*;

public class ChromaticNumber2 implements GraphProperty {
    public Map<UUID, List<UUID>> getAdjacencyList(Graph graph) {
        Map<UUID, List<UUID>> adjacencyList = new HashMap<>();
        graph.getVertices().keySet().forEach(key -> adjacencyList.put(key, new ArrayList<>()));

        for (Edge edge : graph.getEdges()) {
            UUID key = edge.getFromV();
            UUID value = edge.getToV();
            adjacencyList.get(key).add(value);
            if (graph.getDirectType().equals(GraphType.UNDIRECTED))
                adjacencyList.get(value).add(key);
        }

        return adjacencyList;
    }

    @Override
    public boolean execute(Graph graph) {
        // Граф без рёбер имеет хроматическое число 1
        if (graph.getEdgeCount().equals(0)) {
            return false;
        }

        Color defaultColor = Color.gray;
        Color firstColor = Color.pink;
        Color secondColor = Color.brown;

        // Словарь раскраски вершин (эта раскраска не коррелирует с исходной раскраской вершин)
        Map<UUID, Color> colorMap = new HashMap<>();

        // Покрасим все вершины в один цвет перед началом работы алгоритма
        graph.getVertices().keySet().forEach(key -> colorMap.put(key, defaultColor));

        Map<UUID, List<UUID>> adjacencyList = getAdjacencyList(graph);

        for (var entry : colorMap.entrySet()) {
            if (!entry.getValue().equals(defaultColor))
                continue;

            Queue<UUID> queue = new LinkedList<>();
            colorMap.put(entry.getKey(), firstColor);
            queue.add(entry.getKey());

            while (!queue.isEmpty()) {
                UUID current = queue.poll();
                List<UUID> adjacentVertices = adjacencyList.get(current);
                Color currentVertexColor = colorMap.get(current);
                Color colorToPaint = currentVertexColor == firstColor ? secondColor : firstColor;

                for (UUID vertex : adjacentVertices) {
                    if (colorMap.get(vertex).equals(defaultColor)) {
                        colorMap.put(vertex, colorToPaint);
                        queue.add(vertex);
                    } else if (colorMap.get(vertex).equals(currentVertexColor))
                        return false;
                }
            }
        }
        return true;
    }
}