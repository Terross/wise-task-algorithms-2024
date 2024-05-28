import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class SumDegVerGeqNumVer1 implements GraphProperty {
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
        Map<UUID, List<UUID>> adjacencyList = getAdjacencyList(graph);
        int n = adjacencyList.size();
        // Проверяем, есть ли хотя бы две вершины в графе
        if (n < 2)
            return false;

        // Проходим по всем парам вершин и находим сумму их степеней
        for (Map.Entry<UUID, List<UUID>> entry1 : adjacencyList.entrySet()) {
            List<UUID> neighbors1 = entry1.getValue();
            for (Map.Entry<UUID, List<UUID>> entry2 : adjacencyList.entrySet()) {
                List<UUID> neighbors2 = entry2.getValue();
                int degreeSum = neighbors1.size() + neighbors2.size(); // Сумма степеней двух вершин
                // Если сумма степеней пары вершин < n - 1, возвращаем false
                if (degreeSum < n - 1)
                    return false;
            }
        }
        return true;
    }
}