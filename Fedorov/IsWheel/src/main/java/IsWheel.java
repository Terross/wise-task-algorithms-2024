import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class IsWheel implements GraphProperty {
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
        if (graph.getEdgeCount() < 4) {
            return false;
        }
        Map<UUID, List<UUID>> adjacencyList = getAdjacencyList(graph);
        int n = adjacencyList.size();

        // Находим центр и его соседей
        UUID center = null;
        for (Map.Entry<UUID, List<UUID>> entry : adjacencyList.entrySet()) {
            UUID vertex = entry.getKey();
            List<UUID> neighbors = entry.getValue();
            if (neighbors.size() == n - 1) {
                center = vertex;
                break;
            }
        }
        // Если не найдена вершина, соединенная со всеми остальными, граф не является колесом
        if (center == null)
            return false;

        // Проверяем, что все остальные вершины соединены с центром и ещё с двумя другоими вершинами
        for (Map.Entry<UUID, List<UUID>> entry : adjacencyList.entrySet()) {
            UUID vertex = entry.getKey();
            List<UUID> neighbors = entry.getValue();
            if (!vertex.equals(center)) {
                if (neighbors.size() != 3 || !neighbors.contains(center))
                    return false;
            }
        }

        return true;
    }
}