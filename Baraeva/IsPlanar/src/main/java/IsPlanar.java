import com.mathsystem.api.graph.model.*;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;


public class IsPlanar implements GraphProperty {
    int edges_count = 0;
    int vertexes_count = 0;
    ArrayList<ArrayList<Integer>> adjacency_matrix;
    List<Edge> edges;
    List<UUID> vertexes;

    @Override
    public boolean execute(Graph graph) {
        setValues(graph);
        if (vertexes_count <= 4)
            return true;
        boolean k5 = checkForK5();
        boolean k33 = checkForK33();
        return !(k5 || k33);
    }

    private void setValues(Graph graph) {
        vertexes_count = graph.getVertexCount();
        edges_count = graph.getEdgeCount();
        edges = graph.getEdges();
        vertexes = new ArrayList<>();
        adjacency_matrix = new ArrayList<>(vertexes_count);

        for (int i = 0; i < vertexes_count; i++)
            adjacency_matrix.add(new ArrayList<>());

        for (int i = 0; i < edges_count; i++) {
            Edge edge = edges.get(i);
            if (!vertexes.contains(edge.getFromV()))
                vertexes.add(edge.getFromV());
            if (!vertexes.contains(edge.getToV()))
                vertexes.add(edge.getToV());
            addEdge(vertexes.indexOf(graph.getEdges().get(i).getFromV()), vertexes.indexOf(graph.getEdges().get(i).getToV()));
        }
    }

    private void addEdge(int v, int w) {
        adjacency_matrix.get(v).add(w);
        adjacency_matrix.get(w).add(v);
    }

    private boolean checkForK5() {
        for (int x = 0; x < vertexes_count; x++)
            for (int y : adjacency_matrix.get(x))
                for (int z : adjacency_matrix.get(y))
                    if (adjacency_matrix.get(x).contains(z))
                        for (int w : adjacency_matrix.get(z))
                            if (adjacency_matrix.get(y).contains(w) && adjacency_matrix.get(x).contains(w))
                                for (int u : adjacency_matrix.get(w))
                                    if (adjacency_matrix.get(x).contains(u) && adjacency_matrix.get(y).contains(u) && adjacency_matrix.get(z).contains(u)) {
                                        Set<Integer> vertexSet = new HashSet<>();
                                        vertexSet.add(x);
                                        vertexSet.add(y);
                                        vertexSet.add(z);
                                        vertexSet.add(w);
                                        vertexSet.add(u);
                                        if (vertexSet.size() == 5)
                                            return true;
                                    }

        return false;
    }

    private boolean checkForK33() {
        for (int x = 0; x < vertexes_count; x++)
            for (int y : adjacency_matrix.get(x))
                for (int z : adjacency_matrix.get(y))
                    for (int w : adjacency_matrix.get(z))
                        if (adjacency_matrix.get(x).contains(w))
                            for (int u : adjacency_matrix.get(w))
                                if (adjacency_matrix.get(y).contains(u))
                                    for (int v : adjacency_matrix.get(u))
                                        if (adjacency_matrix.get(x).contains(v) && adjacency_matrix.get(z).contains(v)) {
                                            Set<Integer> vertexSet = new HashSet<>();
                                            vertexSet.add(x);
                                            vertexSet.add(y);
                                            vertexSet.add(z);
                                            vertexSet.add(w);
                                            vertexSet.add(u);
                                            vertexSet.add(v);
                                            if (vertexSet.size() == 6)
                                                return true;
                                        }

        return false;
    }
}
