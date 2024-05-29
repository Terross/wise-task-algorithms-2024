import com.mathsystem.api.graph.model.*;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import com.mathsystem.domain.graph.repository.Color;

import java.util.*;


public class IsNormal implements GraphProperty {
    int edges_count;
    int vertexes_count;
    boolean flag;
    boolean[] is_visited;
    ArrayList<ArrayList<Integer>> adjacency_matrix;
    List<Edge> edges;
    List<UUID> vertexes;

    @Override
    public boolean execute(Graph graph) {
        setValues(graph);
        for (int i = 0; i < vertexes_count; i ++) {
            if (!flag)
                break;
            dfs(i, graph);
        }
        return flag;
    }

    private void setValues(Graph graph) {
        flag = true;
        vertexes_count = graph.getVertexCount();
        edges_count = graph.getEdgeCount();
        edges = graph.getEdges();
        vertexes = new ArrayList<>();
        adjacency_matrix = new ArrayList<>(vertexes_count);
        is_visited = new boolean[vertexes_count];

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

    private void dfs(int current, Graph graph) {
        is_visited[current] = true;
        for (int target : adjacency_matrix.get(current)) {
            Color current_color = graph.getVertices().get(vertexes.get(current)).getColor();
            Color target_color = graph.getVertices().get(vertexes.get(target)).getColor();
            if (current_color == target_color && current_color != Color.gray) {
                flag = false;
                break;
            }
            if (!is_visited[target] && flag)
                dfs(target, graph);
        }
    }
}
