import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;

import java.util.*;

public class isGraphMinimal {
    //проходит по всем ребрам графа, временно удаляет каждое из них и проверяет, остается ли граф связным после удаления
    public boolean execute(Graph graph) {
        List<Edge> edges = graph.getEdges();
        for (Edge edge : edges) {
            Graph graphcopy = removeEdge(graph, edge);
            if(isConnected(graphcopy)){
                return false;
            }
        }
        return true;
    }

    //cоздает копию исходного графа, удаляет из копии указанное ребро и возвращает измененную копию графа.
    private Graph removeEdge(Graph graph, Edge edgeToRemove) {
        Graph newGraph = new Graph(graph.getDirectType(), graph.getVertexCount(), graph.getEdgeCount(),
                new HashMap<>(graph.getVertices()), new ArrayList<>(graph.getEdges()));

        newGraph.getEdges().remove(edgeToRemove);

        return newGraph;
    }

    //проверяет, является ли граф связным
    public static boolean isConnected(Graph graph) {
        UUID key = graph.getVertices().keySet().iterator().next();

        var dfs = new DFS();

        Set<UUID> visited = dfs.depthFirstSearch(graph, key);

        for (UUID v : graph.getVertices().keySet()) {
            if (!visited.contains(v)) {
                return false;
            }
        }

        return true;
    }

}


