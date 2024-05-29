import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.util.*;

public class MaxVertexDegree implements GraphCharacteristic {
    private HashMap<UUID, List<Vertex>> createAdjList(Graph graph) {
        Map<UUID, Vertex> vertices = graph.getVertices();
        List<Edge> edges = graph.getEdges();
        HashMap<UUID, List<Vertex>> adjList = new HashMap<>();
        for (var vertex : vertices.values()) {
            adjList.put(vertex.getId(), new ArrayList<>());
        }

        for (var edge : edges) {
            adjList.get(edge.getFromV()).add(vertices.get(edge.getToV()));
            adjList.get(edge.getToV()).add(vertices.get(edge.getFromV()));
        }
        return adjList;
    }


    @Override
    public Integer execute(Graph graph) {
        Map<UUID, List<Vertex>> adjList = createAdjList(graph);
        int maxDegree = 0;
        for (List<Vertex> neighbours : adjList.values()) {
            maxDegree = Math.max(maxDegree, neighbours.size());
        }
        return maxDegree;
    }
}

