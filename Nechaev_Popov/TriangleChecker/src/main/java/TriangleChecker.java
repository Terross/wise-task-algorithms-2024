import java.util.HashSet;
import java.util.Set;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;


public class TriangleChecker implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        for (Edge edge : graph.getEdges()) {
            Vertex vertex1 = graph.getVertices().get(edge.getFromV());
            Vertex vertex2 = graph.getVertices().get(edge.getToV());

            Set<Vertex> commonNeighbors = findCommonNeighbors(graph, vertex1, vertex2);

            for (Vertex commonNeighbor : commonNeighbors) {
                int res_count = 0;
                if (degreeIsAtLeastTwo(graph, vertex1)) res_count++;
                if (degreeIsAtLeastTwo(graph, vertex2)) res_count++;
                if (degreeIsAtLeastTwo(graph, commonNeighbor)) res_count++;
                if (res_count < 2) {
                    return false;
                }
            }
        }

        return true;
    }

    private Set<Vertex> findCommonNeighbors(Graph graph, Vertex vertex1, Vertex vertex2) {
        Set<Vertex> commonNeighbors = new HashSet<>();
        Set<Vertex> neighborsOfVertex1 = getNeighbors(graph, vertex1);
        Set<Vertex> neighborsOfVertex2 = getNeighbors(graph, vertex2);

        for (Vertex neighbor : neighborsOfVertex1) {
            if (neighborsOfVertex2.contains(neighbor)) {
                commonNeighbors.add(neighbor);
            }
        }

        return commonNeighbors;
    }

    private Set<Vertex> getNeighbors(Graph graph, Vertex vertex) {
        Set<Vertex> neighbors = new HashSet<>();
        for (Edge edge : graph.getEdges()) {
            if (edge.getFromV().equals(vertex.getId())) {
                neighbors.add(graph.getVertices().get(edge.getToV()));
            } else if (edge.getToV().equals(vertex.getId())) {
                neighbors.add(graph.getVertices().get(edge.getFromV()));
            }
        }
        return neighbors;
    }

    private boolean degreeIsAtLeastTwo(Graph graph, Vertex vertex) {
        int degree = 0;
        for (Edge edge : graph.getEdges()) {
            if (edge.getFromV().equals(vertex.getId()) || edge.getToV().equals(vertex.getId())) {
                degree++;
            }
        }
        return degree == 2;
    }
}