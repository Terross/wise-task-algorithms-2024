import java.util.HashSet;
import java.util.Set;

import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;


public class Odd_component implements GraphCharacteristic {
    @Override
    public Integer execute(Graph graph) {
        Set<Vertex> visited = new HashSet<>();
        int oddComponentCount = 0;

        for (Vertex vertex : graph.getVertices().values()) {
            if (!visited.contains(vertex)) {
                Set<Vertex> component = new HashSet<>();
                dfs(vertex, visited, component, graph);
                if (component.size() % 2 != 0) {
                    oddComponentCount++;
                }
            }
        }

        return oddComponentCount;
    }

    private void dfs(Vertex vertex, Set<Vertex> visited, Set<Vertex> component, Graph graph) {
        visited.add(vertex);
        component.add(vertex);

        for (Edge edge : graph.getEdges()) {
            if (edge.getFromV().equals(vertex.getId())) {
                Vertex neighbor = graph.getVertices().get(edge.getToV());
                if (visited.contains(neighbor)) {
                } else {
                    dfs(neighbor, visited, component, graph);
                }
            } else if (edge.getToV().equals(vertex.getId())) {
                Vertex neighbor = graph.getVertices().get(edge.getFromV());
                if (!visited.contains(neighbor)) {
                    dfs(neighbor, visited, component, graph);
                }
            }
        }
    }
}