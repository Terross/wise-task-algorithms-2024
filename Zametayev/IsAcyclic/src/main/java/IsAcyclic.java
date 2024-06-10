import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class IsAcyclic implements GraphProperty {
    public boolean execute(Graph graph) {
        int vertexCount = graph.getVertexCount();
        boolean[] visited = new boolean[vertexCount];
        boolean[] recStack = new boolean[vertexCount];

        int[][] adjMatrix = graphToAdjacencyMatrix(graph);

        for (int i = 0; i < vertexCount; i++) {
            if (isCyclic(i, visited, recStack, adjMatrix)) {
                return false;
            }
        }
        return true;
    }

    private boolean isCyclic(int v, boolean[] visited, boolean[] recStack, int[][] adjMatrix) {
        if (recStack[v]) {
            return true;
        }
        if (visited[v]) {
            return false;
        }
        visited[v] = true;
        recStack[v] = true;

        for (int i = 0; i < adjMatrix.length; i++) {
            if (adjMatrix[v][i] != 0 && isCyclic(i, visited, recStack, adjMatrix)) {
                return true;
            }
        }

        recStack[v] = false;
        return false;
    }

    private List<UUID> getListVertices(Graph graph) {
        List<UUID> vertices = new ArrayList<>();
        Map<UUID, Vertex> vertexMap = graph.getVertices();
        for (var entry : vertexMap.entrySet()) {
            vertices.add(entry.getKey());
        }
        return vertices;
    }

    private int[][] graphToAdjacencyMatrix(Graph graph) {
        int vertexCount = graph.getVertexCount();
        GraphType type = graph.getDirectType();
        List<UUID> vertices = getListVertices(graph);
        int[][] adjMatrix = new int[vertexCount][vertexCount];

        List<Edge> edges = graph.getEdges();
        for (Edge edge : edges) {
            int fromIndex = vertices.indexOf(edge.getFromV());
            int toIndex = vertices.indexOf(edge.getToV());
            adjMatrix[fromIndex][toIndex] = 1;
            if (type == GraphType.UNDIRECTED) {
                adjMatrix[toIndex][fromIndex] = 1;
            }
        }
        return adjMatrix;
    }
}
