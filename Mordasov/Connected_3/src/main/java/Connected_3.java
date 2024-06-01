import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

public class Connected_3 implements GraphProperty {

    public static int V; // Количество вершин
    public static boolean[][] adj; // Матрица смежности

    @Override
    public boolean execute(Graph graph) {
        
        V = graph.getVertexCount();
        String[][] ver  = new String[graph.getEdges().size()][2];
        for(int i = 0; i < graph.getEdges().size(); i++) {
            ver[i][0] = graph.getEdges().get(i).getFromV().toString().split("-")[0];
            ver[i][1] = graph.getEdges().get(i).getToV().toString().split("-")[0];
        }
        Set<String> verticesSet = new HashSet<>();
        for (String[] edge : ver) {
            verticesSet.add(edge[0]);
            verticesSet.add(edge[1]);
        }
        List<String> verticesList = new ArrayList<>(verticesSet);    
        Map<String, Integer> vertexIndexMap = new HashMap<>();
        for (int i = 0; i < verticesList.size(); i++) {
            vertexIndexMap.put(verticesList.get(i), i);
        }
        adj = new boolean[verticesList.size()][verticesList.size()];
        for (String[] edge : ver) {
            String fromVertex = edge[0];
            String toVertex = edge[1];
            int fromIndex = vertexIndexMap.get(fromVertex);
            int toIndex = vertexIndexMap.get(toVertex);
            adj[fromIndex][toIndex] = true; 
            adj[toIndex][fromIndex] = true;
        }
        return isThreeConnected();
    }

    public boolean isThreeConnected() {
        // Шаг 1: Проверка минимальной степени вершин
        for (int i = 0; i < V; i++) {
            int degree = 0;
            for (int j = 0; j < V; j++) {
                if (adj[i][j]) {
                    degree++;
                }
            }
            if (degree < 3) {
                return false;
            }
        }
        // Шаг 2: Проверка двухсвязности графа
        if (!isTwoConnected()) {
            return false;
        }
        // Шаг 3: Удаление пар вершин и проверка двухсвязности оставшегося графа
        for (int u = 0; u < V; u++) {
            for (int v = u + 1; v < V; v++) {
                if (!checkTwoConnectedAfterRemoval(u, v)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isTwoConnected() {
        boolean[] visited = new boolean[V];
        int[] disc = new int[V];
        int[] low = new int[V];
        int[] parent = new int[V];
        Arrays.fill(disc, -1);
        Arrays.fill(low, -1);
        Arrays.fill(parent, -1);

        for (int i = 0; i < V; i++) {
            if (disc[i] == -1) {
                if (apDFS(i, visited, disc, low, parent)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean apDFS(int u, boolean[] visited, int[] disc, int[] low, int[] parent) {
        boolean isArticulationPoint = false;
        int children = 0;
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (int v = 0; v < V; v++) {
            if (adj[u][v]) {
                if (!visited[v]) {
                    children++;
                    parent[v] = u;
                    if (apDFS(v, visited, disc, low, parent)) {
                        return true;
                    }

                    low[u] = Math.min(low[u], low[v]);

                    if (parent[u] == -1 && children > 1) {
                        isArticulationPoint = true;
                    }

                    if (parent[u] != -1 && low[v] >= disc[u]) {
                        isArticulationPoint = true;
                    }
                } else if (v != parent[u]) {
                    low[u] = Math.min(low[u], disc[v]);
                }
            }
        }

        return isArticulationPoint;
    }

    private boolean checkTwoConnectedAfterRemoval(int u, int v) {
        boolean[] visited = new boolean[V];
        boolean started = false;

        for (int i = 0; i < V; i++) {
            if (i != u && i != v && !visited[i]) {
                dfs(i, u, v, visited);
                started = true;
                break;
            }
        }

        if (!started) return true; // Все вершины удалены

        for (int i = 0; i < V; i++) {
            if (i != u && i != v && !visited[i]) {
                return false;
            }
        }

        return true;
    }

    private void dfs(int u, int exclude1, int exclude2, boolean[] visited) {
        visited[u] = true;

        for (int v = 0; v < V; v++) {
            if (adj[u][v] && v != exclude1 && v != exclude2 && !visited[v]) {
                dfs(v, exclude1, exclude2, visited);
            }
        }
    }
    private int time = 0; // Время обхода
}
