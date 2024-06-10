import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

import static java.lang.Math.abs;

public class IsPancyclic implements GraphProperty {
    public boolean execute(Graph graph) {
        int v_count = graph.getVertexCount();
        if (v_count < 3) {
            return false;
        }
        int[][] adj = graph_to_adjacency_matrix(graph);
        List<UUID> verts = getListVertices(graph);
        int[] used = new int[v_count];
        int[] found = new int[v_count - 2];
        boolean[] visited = new boolean[v_count];
        for (int i = 0; i < v_count; i++) {
            if (visited[i]) continue;
            var vert = verts.get(i);
            used[verts.indexOf(vert)] = 1;
            dfs(verts, adj, vert, used, found, visited);
        }
        for (int j : found) {
            if (j != 1) {
                return false;
            }
        }
        return true;
    }

    private List<UUID> getListVertices(Graph graph) {
        List<UUID> vertices = new ArrayList<UUID>();
        Map<UUID, Vertex> map_v = graph.getVertices();
        for (var elem: map_v.entrySet()) {
            vertices.add(elem.getKey());
        }
        return vertices;
    }

    private int[][] graph_to_adjacency_matrix(Graph graph) {
        int v_count = graph.getVertexCount();
        GraphType type = graph.getDirectType();
        List<UUID> vertices = getListVertices(graph);
        int[][] adj_matrix = new int [v_count][v_count];
        List<Edge> edges = graph.getEdges();
        for (Edge elem: edges) {
            int index_s = vertices.indexOf(elem.getFromV());
            int index_e = vertices.indexOf(elem.getToV());
            adj_matrix[index_s][index_e] = 1;
            if (type == GraphType.UNDIRECTED) {
                adj_matrix[index_e][index_s] = 1;
            }
        }
        return adj_matrix;
    }

    private void dfs(List<UUID> verts, int[][] adj, UUID current, int[] used, int[] found, boolean[] visited) {
        int index = verts.indexOf(current);
        for (int j = 0; j < adj.length; j++) {
            if (visited[j]) continue;
            if (adj[index][j] == 1) {
                if (used[j] == 0) {
                    used[j] = used[index] + 1;
                    dfs(verts, adj, verts.get(j), used, found, visited);
                }
                if (used[j] != 0) {
                    if (abs(used[index] - used[j]) + 1 < 3) {
                        continue;
                    }
                    found[abs(used[index] - used[j]) + 1 - 3] = 1;
                }
            }
        }
        visited[index] = true;
    }
}
