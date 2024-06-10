import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class IsGraph3ConnectedAfterEdgeContraction implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        if (graph.getVertexCount() < 3) return false;
        else if (graph.getVertexCount() == 3) {
            return true;
        }
        if(is3Connected(graph)){
            for (Edge e : graph.getEdges()) {
                if (is3Connected(edgeContraction(graph, e))) {
                    return true;
                }
            }
        }
        return false;
    }

    private Graph edgeContraction(Graph graph, Edge edge) {
        UUID curVertex = edge.getFromV();
        UUID exVertex = edge.getToV();
        List<Edge> newEdges = new ArrayList<>();
        for (Edge e : graph.getEdges()) {
            if (e.getFromV().equals(exVertex) && !e.getToV().equals(curVertex)) {
                newEdges.add(new Edge(curVertex, e.getToV(), e.getColor(), e.getWeight(), e.getLabel()));
            } else if (e.getToV().equals(exVertex) && !e.getFromV().equals(curVertex)) {
                newEdges.add(new Edge(e.getFromV(), curVertex, e.getColor(), e.getWeight(), e.getLabel()));
            } else {
                newEdges.add(e);
            }

        }
        Map<UUID, Vertex> newVertex = new HashMap<>(graph.getVertices());
        newVertex.remove(exVertex);
        return new Graph(graph.getDirectType(), newVertex.size(), newEdges.size(), newVertex, newEdges);

    }

    public boolean is3Connected(Graph graph) {
        for(Vertex v: graph.getVertices().values()){
            for(Vertex w: graph.getVertices().values()){
                if(!v.equals(w)){
                    List<Edge> tmp_edges = new ArrayList<>(graph.getEdges());
                    Map<UUID, Vertex> tmp_vertex = new HashMap<>(graph.getVertices());
                    for(Edge e: graph.getEdges()){
                        if(v.getId().equals(e.getFromV()) || v.getId().equals(e.getToV()) || w.getId().equals(e.getFromV()) || w.getId().equals(e.getToV())){
                            tmp_edges.remove(e);
                        }
                    }
                    tmp_vertex.remove(v.getId());
                    tmp_vertex.remove(w.getId());
                    Graph graph1 = new Graph(graph.getDirectType(), tmp_vertex.size(), tmp_edges.size(), tmp_vertex, tmp_edges);
                    if(!isGraphConnected(graph1)){

                        return false;
                    }
                }
            }

        }
        return true;
    }

    public boolean isGraphConnected(Graph graph) {
        UUID key = graph.getVertices().keySet().iterator().next();
        Set<UUID> visited = DFS(graph, key);
        for (UUID v : graph.getVertices().keySet()) {
            if (!visited.contains(v)) {
                return false;
            }
        }
        return true;
    }

    private void DFSUtil(Graph graph, UUID vertexId, Set<UUID> visitedSet) {
        visitedSet.add(vertexId);
        for (Edge edge : graph.getEdges()) {
            if (edge.getFromV().equals(vertexId) && !visitedSet.contains(edge.getToV())) {
                DFSUtil(graph, edge.getToV(), visitedSet);
            }
            if (edge.getToV().equals(vertexId) && !visitedSet.contains(edge.getFromV())) {
                DFSUtil(graph, edge.getFromV(), visitedSet);
            }
        }
    }

    private Set<UUID> DFS(Graph graph, UUID startVertexId) {
        Set<UUID> visitedSet = new HashSet<>();
        DFSUtil(graph, startVertexId, visitedSet);
        return visitedSet;
    }


}

