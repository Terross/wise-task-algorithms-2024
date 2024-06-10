import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.util.*;

public class MinNodeDegree implements GraphCharacteristic {
    @Override
    public Integer execute(Graph graph) {
        // check if graph has no vertices
        if (graph.getVertexCount() == 0)
            return -1;
        var adjacencyList = getAdjacencyList(graph);
        return getMinDegree(adjacencyList);
    }

    private int getMinDegree(HashMap<UUID, List<Edge>> adjacencyList) {
        return Collections.min(adjacencyList.values(), Comparator.comparingInt(List::size)).size();
    }

    private HashMap<UUID, List<Edge>> getAdjacencyList(Graph graph) {
        var adjacencyList = new HashMap<UUID, List<Edge>>();
        for (Vertex vertex : graph.getVertices().values()) {
            adjacencyList.put(vertex.getId(), new ArrayList<>());
        }
        for (Edge edge : graph.getEdges()) {
            UUID source = edge.getFromV(), target = edge.getToV();
            adjacencyList.get(source).add(edge);
            adjacencyList.get(target).add(edge);
        }
        return adjacencyList;
    }
}
