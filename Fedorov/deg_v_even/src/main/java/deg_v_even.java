import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import com.mathsystem.domain.graph.repository.GraphType;

import java.util.*;
public class deg_v_even implements GraphProperty {
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
        for (UUID vertex:adjacencyList.keySet()){
            if (adjacencyList.get(vertex).size() %2 != 0){
                return false;
            }
        }
        return true;

    }
}