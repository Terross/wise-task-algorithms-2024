import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import com.mathsystem.domain.graph.repository.Color;

import java.util.*;

public class ChromaticNumber2 implements GraphProperty {
    public Map<UUID, List<UUID>> getAdjacencyList(Graph graph) {
        Map<UUID, List<UUID>> adjacencyList = new HashMap<>();
        graph.getVertices().keySet().forEach(key -> {
            adjacencyList.put(key, new ArrayList<>());
        });

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

        // Покрасим все вершины в один цвет перед началом работы алгоритма
        graph.getVertices().values().forEach(value -> {
            value.setColor(defaultColor);
        });

        Map<UUID, List<UUID>> adjacencyList = getAdjacencyList(graph);

        for (var entry : graph.getVertices().entrySet()) {
            if (!entry.getValue().getColor().equals(defaultColor))
                continue;

            Queue<UUID> queue = new LinkedList<>();
            graph.getVertices().get(entry.getKey()).setColor(firstColor);
            queue.add(entry.getKey());

            while (!queue.isEmpty()) {
                UUID current = queue.poll();
                List<UUID> adjacentVertices = adjacencyList.get(current);
                Color currentVertexColor = graph.getVertices().get(current).getColor();
                Color colorToPaint = graph.getVertices().get(current).getColor() == firstColor ? secondColor : firstColor;

                for (UUID vertex : adjacentVertices) {
                    if (graph.getVertices().get(vertex).getColor() == defaultColor) {
                        graph.getVertices().get(vertex).setColor(colorToPaint);
                        queue.add(vertex);
                    } else if (graph.getVertices().get(vertex).getColor() == currentVertexColor)
                        return false;
                }
            }
        }
        return true;
    }
}