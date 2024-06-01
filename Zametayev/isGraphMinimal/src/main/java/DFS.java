import com.mathsystem.api.graph.model.*;

import java.util.*;

public class DFS {
    //инициирует обход графа в глубину с указанной стартовой вершиной
    public Set<UUID> depthFirstSearch(Graph graph, UUID startVertexId) {
        Set<UUID> visitedSet = new HashSet<>();
        depthFirstSearchUtil(graph, startVertexId, visitedSet);
        return visitedSet;
    }
    //вспомогательный рекурсивный метод для обхода графа в глубину.Он добавляет текущую вершину
    //в множество посещенных и рекурсивно вызывает сам себя для всех смежных вершин, которые еще не были посещены.
    private void depthFirstSearchUtil(Graph graph, UUID vertexId, Set<UUID> visitedSet) {
        visitedSet.add(vertexId);

        for (Edge edge : graph.getEdges()) {
            if (edge.getFromV().equals(vertexId) && !visitedSet.contains(edge.getToV())) {
                depthFirstSearchUtil(graph, edge.getToV(), visitedSet);
            }
            if (edge.getToV().equals(vertexId) && !visitedSet.contains(edge.getFromV())) {
                depthFirstSearchUtil(graph, edge.getFromV(), visitedSet);
            }
        }
    }
}