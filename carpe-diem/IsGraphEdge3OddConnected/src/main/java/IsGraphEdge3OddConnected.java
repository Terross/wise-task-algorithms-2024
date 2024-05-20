import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class IsGraphEdge3OddConnected implements  GraphProperty{

    @Override
    public boolean execute(Graph graph) {
        if (graph.getEdgeCount() == 0) return false;
        if (graph.getVertexCount() % 2 != 0) return false;
        return isEdge3Connected(graph);
    }

    public boolean isEdge3Connected(Graph graph) {
        for(Edge e1: graph.getEdges()){
            for (Edge e2: graph.getEdges()) {
                List<Edge> tmp_edges = new ArrayList<>(graph.getEdges());
                tmp_edges.remove(e1);
                if (!e1.equals(e2)) {
                    tmp_edges.remove(e2);
                }
                Graph graph1 = new Graph(graph.getDirectType(), graph.getVertexCount(), tmp_edges.size(), graph.getVertices(), tmp_edges);
                if (!isGraphConnected(graph1)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isGraphConnected(Graph graph) {
        UUID i = graph.getVertices().keySet().iterator().next();
        Set<UUID> visited = DFS(graph, i);
        for (UUID v : graph.getVertices().keySet()) {
            if (!visited.contains(v)) {
                return false;
            }
        }
        return true;
    }

    private void DFSFunction(Graph graph, UUID vertex, Set<UUID> visitedSet) {
        visitedSet.add(vertex);
        for (Edge e : graph.getEdges()) {
            if (e.getFromV().equals(vertex) && !visitedSet.contains(e.getToV())) {
                DFSFunction(graph, e.getToV(), visitedSet);
            }
            if (e.getToV().equals(vertex) && !visitedSet.contains(e.getFromV())) {
                DFSFunction(graph, e.getFromV(), visitedSet);
            }
        }
    }

    private Set<UUID> DFS(Graph graph, UUID startVertexId) {
        Set<UUID> visitedSet = new HashSet<>();
        DFSFunction(graph, startVertexId, visitedSet);
        return visitedSet;
    }


}

