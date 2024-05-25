import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.util.*;


public class CalculateEdgeConnectivity implements GraphCharacteristic {

    @Override
    public Integer execute(Graph graph) {
        if (!isConnected(graph) || graph.getEdgeCount() == 0) {
            return 0;
        }
        return edgeConnectivity(graph);
    }

    public int edgeConnectivity(Graph graph) {
        List<Edge> edges = new ArrayList<>(graph.getEdges());
        int n = edges.size();

        if (n == 0) {
            return Integer.MAX_VALUE;
        }

        int minCut = n;

        for (int k = 1; k <= n; k++) {
            List<List<Edge>> combinations = generateCombinations(edges, k);
            for (List<Edge> combination : combinations) {
                Graph newGraph = removeEdges(graph, combination);
                if (!isConnected(newGraph)) {
                    return k;
                }
            }
        }

        return minCut;
    }

    private List<List<Edge>> generateCombinations(List<Edge> edges, int k) {
        List<List<Edge>> combinations = new ArrayList<>();
        generateCombinationsHelper(edges, k, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    private void generateCombinationsHelper(List<Edge> edges, int k, int start, List<Edge> currentCombination, List<List<Edge>> combinations) {
        if (currentCombination.size() == k) {
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }

        for (int i = start; i < edges.size(); i++) {
            currentCombination.add(edges.get(i));
            generateCombinationsHelper(edges, k, i + 1, currentCombination, combinations);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    private Graph removeEdges(Graph graph, List<Edge> edgesToRemove) {
        Graph newGraph = new Graph(graph.getDirectType(), graph.getVertexCount(), graph.getEdgeCount(),
                new HashMap<>(graph.getVertices()), new ArrayList<>(graph.getEdges()));
        newGraph.getEdges().removeAll(edgesToRemove);
        return newGraph;
    }

    private boolean isConnected(Graph graph) {
        if (graph.getVertices().isEmpty()) {
            return true;
        }

        Set<UUID> visited = new HashSet<>();
        Vertex startVertex = graph.getVertices().values().iterator().next();
        dfs(graph, startVertex, visited);

        return visited.size() == graph.getVertices().size();
    }

    private void dfs(Graph graph, Vertex currentVertex, Set<UUID> visited) {
        visited.add(currentVertex.getId());
        for (Edge edge : graph.getEdges()) {
            if (edge.getFromV().equals(currentVertex.getId()) || edge.getToV().equals(currentVertex.getId())) {
                UUID nextVertexId = edge.getFromV().equals(currentVertex.getId()) ? edge.getToV() : edge.getFromV();
                Vertex nextVertex = graph.getVertices().get(nextVertexId);
                if (!visited.contains(nextVertex.getId())) {
                    dfs(graph, nextVertex, visited);
                }
            }
        }
    }
}