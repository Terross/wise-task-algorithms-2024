import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.util.*;

public class VertexColoring2Periodic implements  GraphProperty {
    private HashMap<UUID, List<Vertex>> createAdjList(Graph graph)
    {
        Map<UUID, Vertex> vertices = graph.getVertices();
        List<Edge> edges = graph.getEdges();
        HashMap<UUID, List<Vertex>> adjList = new HashMap<>();
        for (var vertex : vertices.values())
        {
            adjList.put(vertex.getId(), new ArrayList<>());
        }

        for (var edge : edges)
        {
            adjList.get(edge.getFromV()).add(vertices.get(edge.getToV()));
        }
        return adjList;
    }

    @Override
    public boolean execute(Graph graph) {
        Map<UUID, List<Vertex>> adjList = createAdjList(graph);

        // Проходим по всем вершинам графа
        for (Vertex vertex : graph.getVertices().values()) {
            // Создаем множество для хранения цветов соседей текущей вершины
            Set<Color> colors = new HashSet<>();
            // Проходим по соседям вершины
            for (Vertex neighbor : adjList.get(vertex.getId())) {
                // Добавляем цвет соседней вершины в множество
                colors.add(neighbor.getColor());
                // Если в множестве более двух цветов, то раскраска не является 2-периодической
                if (colors.size() > 2) {
                    return false;
                }
            }
        }
        // Если все вершины прошли проверку, то раскраска является 2-периодической
        return true;
    }
}

