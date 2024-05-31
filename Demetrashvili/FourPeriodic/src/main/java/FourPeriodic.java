
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class FourPeriodic implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        Map<UUID, Vertex> vertices = graph.getVertices();
        for (Vertex startVertex : vertices.values()) {
            if (!isFourPeriodicFromVertex(startVertex, graph)) {
                return false;
            }
        }
        return true;
    }

    private boolean isFourPeriodicFromVertex(Vertex startVertex, Graph graph) {
        Map<Color, Integer> colorStepMap = new HashMap<>();
        Queue<Vertex> queue = new LinkedList<>();
        Map<Vertex, Integer> visitedSteps = new HashMap<>();

        queue.add(startVertex);
        visitedSteps.put(startVertex, 0);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            int currentStep = visitedSteps.get(current);

            if (colorStepMap.containsKey(current.getColor())) {
                int firstAppearanceStep = colorStepMap.get(current.getColor());
                if ((currentStep - firstAppearanceStep) % 4 != 0) {
                    return false;
                }
            } else {
                colorStepMap.put(current.getColor(), currentStep);
            }

            for (Vertex neighbor : getNeighbors(current, graph)) {
                if (!visitedSteps.containsKey(neighbor)) {
                    visitedSteps.put(neighbor, currentStep + 1);
                    queue.add(neighbor);
                }
            }
        }
        return true;
    }

    private List<Vertex> getNeighbors(Vertex vertex, Graph graph) {
        List<Vertex> neighbors = new ArrayList<>();
        for (Edge edge : graph.getEdges()) {
            if (edge.getFromV().equals(vertex.getId())) {
                neighbors.add(graph.getVertices().get(edge.getToV()));
            } else if (edge.getToV().equals(vertex.getId())) {
                neighbors.add(graph.getVertices().get(edge.getFromV()));
            }
        }
        return neighbors;
    }
}