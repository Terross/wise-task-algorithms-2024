import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

public class MaxClique implements GraphCharacteristic  {

    @Override
    public Integer execute(Graph graph) {
        try {
            String[][] ver = new String[graph.getEdges().size()][2];

            for (int i = 0; i < graph.getEdges().size(); i++) {
                ver[i][0] = graph.getEdges().get(i).getFromV().toString().split("-")[0];
                ver[i][1] = graph.getEdges().get(i).getToV().toString().split("-")[0];
            }
            // System.out.println(Arrays.deepToString(ver));

            Set<String> verticesSet = new HashSet<>();
            for (String[] edge : ver) {
                verticesSet.add(edge[0]);
                verticesSet.add(edge[1]);
            }
            List<String> verticesList = new ArrayList<>(verticesSet);

            // 2. Map vertices to indices
            Map<String, Integer> vertexIndexMap = new HashMap<>();
            for (int i = 0; i < verticesList.size(); i++) {
                vertexIndexMap.put(verticesList.get(i), i);
            }

            // 3. Initialize adjacency matrix
            int[][] adjacencyMatrix = new int[verticesList.size()][verticesList.size()];

            // 4. Fill the adjacency matrix
            for (String[] edge : ver) {
                String fromVertex = edge[0];
                String toVertex = edge[1];
                int fromIndex = vertexIndexMap.get(fromVertex);
                int toIndex = vertexIndexMap.get(toVertex);
                adjacencyMatrix[fromIndex][toIndex] = 1;
                adjacencyMatrix[toIndex][fromIndex] = 1;
            }
            return maxClique(verticesList.size(), adjacencyMatrix); //verticesList.size() - размерность матрицы
        } catch (Exception e) {
            return -1;
        }
    }

    static boolean isClique(int[][] graph, int[] subset, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = i + 1; j < k; j++) {
                if (graph[subset[i]][subset[j]] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    static int maxClique(int n, int[][] graph) {
        int maxSize = 0;
        for (int i = 0; i < (1 << n); i++) { //получаем множество от 0 до 2^n - 1, i играет роль маски всех возможных пар вершин
            int[] subset = new int[n];
            int subsetSize = 0;
            for (int j = 0; j < n; j++) { //проходим по всем вершинами и проверяем их
                if ((i & (1 << j)) > 0) { //проверяем
                    subset[subsetSize++] = j;
                }
            }
            if (isClique(graph, subset, subsetSize)) {
                maxSize = Math.max(maxSize, subsetSize);
            }
        }
        return maxSize;
    }

}
