import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class TwoOddCon implements GraphProperty {
    public Map<Vertex, List<Edge>> getVerticesEdges(Graph graph) {
        Map<Vertex, List<Edge>> res = new HashMap<>();
        for (Vertex vertex : graph.getVertices().values()) {
            res.put(vertex, new ArrayList<>());
            for (Edge edge : graph.getEdges()) {
                if (edge.getFromV().equals(vertex.getId()) || edge.getToV().equals(vertex.getId())) {
                    res.get(vertex).add(edge);
                }
            }
        }
        return res;
    }

    public List<UUID> getListEdges(Map<Vertex, List<Edge>> map, Vertex vertex) {
        List<UUID> local_list = new ArrayList<>();
        for (Edge edge : map.get(vertex)) {
            if (!local_list.contains(edge.getToV()) && !edge.getToV().equals(vertex.getId())) {
                local_list.add(edge.getToV());
            }
            if (!local_list.contains(edge.getFromV()) && !edge.getFromV().equals(vertex.getId())) {
                local_list.add(edge.getFromV());
            }
        }
        return local_list;
    }

    public Map<Vertex, List<Edge>> delete(Graph graph, Map<Vertex, List<Edge>> map, Vertex vertex) {
        List<Edge> edges = map.get(vertex);                     // edges must be deleted
        map.remove(vertex);                                     // delete vertex
        for (Vertex id : map.keySet()) {                        // delete needed edges
            for (int i = 0; i < map.get(id).size(); i++) {
                for (Edge edge : edges) {
                    if (i >= map.get(id).size()) { break; }
                    if (edge.equals(map.get(id).get(i))) {
                        map.get(id).remove(i);
                    }
                }
            }
        }
        return map;
    }

    public List<Vertex> neighbors(Graph graph, HashMap<Vertex, List<Edge>> map, Vertex vertex) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        Map<UUID, Vertex> vertices = graph.getVertices();       // map of UUID of all vertices
        for (UUID id : getListEdges(map, vertex)) {
            neighbors.add(vertices.get(id));
        }
        return neighbors;
    }

    public HashMap<Vertex, Double> BellmanFord(Graph graph, HashMap<Vertex, List<Edge>> map, Vertex start) {
        // Initialization
        List<Vertex> listVertices = new ArrayList<Vertex>(map.keySet());
        HashMap<Vertex, Double> D = new HashMap<Vertex, Double>();
        for (Vertex u : listVertices) {
            D.put(u, Double.POSITIVE_INFINITY);
        }
        D.put(start, (double)0);
        List<Vertex> listNeighbors = neighbors(graph, map, start);
        for (Vertex u : listNeighbors) {
            D.put(u, (double)1);
        }
        // Process
        for (int i = 0; i < listVertices.size() - 2; i++) {
            for (Vertex u : listVertices) {
                listNeighbors = neighbors(graph, map, u);
                for (Vertex w : listNeighbors) {
                    Double tmp = Math.min(D.get(u), D.get(w) + 1);
                    D.put(u, tmp);
                }
            }
        }
        return D;
    }

    @Override
    public boolean execute(Graph graph) {
        for (Vertex v1 : graph.getVertices().values()) {        // delete v1
            HashMap<Vertex, List<Edge>> map = new HashMap<Vertex, List<Edge>>(getVerticesEdges(graph));
            delete(graph, map, v1);
            for (Vertex v : map.keySet()) {                     // BellmanFord
                HashMap<Vertex, Double> ways = BellmanFord(graph, map, v);
                for (Vertex u : ways.keySet()) {
                    if (ways.get(u).equals(Double.POSITIVE_INFINITY)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}