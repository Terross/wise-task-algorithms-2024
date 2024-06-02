
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import java.util.*;

public class ThreeCritical implements GraphProperty{
    @Override
    public boolean execute(Graph graph) {
        if(isThreeCritical(graph)) {
                return true;
            }

        return false;
    }
    private boolean isThreeCritical(Graph graph){
        List<Vertex> vertices = new ArrayList<>(graph.getVertices().values());
        List<Edge> edges = graph.getEdges();
        for (Vertex vertex : vertices) {
            if (isThreeChromatic(graph)) {
                Graph newGraph = removeVertexAndEdges(graph, vertex);
                boolean check_less_chromatic = isLessThreeChromatic(newGraph);
                if (check_less_chromatic) {
                    return true;
                }


            } else {
                return false;
            }
        }
        for (Edge edge : edges) {
            if (isThreeChromatic(graph)) {
                Graph newGraph = removeEdge(graph, edge);
                boolean check_less_chromatic = isLessThreeChromatic(newGraph);
                if (check_less_chromatic) {
                    return true;
                }


            } else {
                return false;
            }
        }

        return false;
    }

    public Graph removeEdge(Graph graph, Edge edgeToRemove) {
        // Создаем новый граф, который будет копией исходного
        Graph newGraph = new Graph(graph.getDirectType(), graph.getVertexCount(), graph.getEdgeCount(),
                new HashMap<>(graph.getVertices()), new ArrayList<>(graph.getEdges()));

        // Удаляем указанное ребро из списка рёбер нового графа
        newGraph.getEdges().remove(edgeToRemove);

        return newGraph;
    }
    public boolean isThreeChromatic(Graph graph) {
        // Получаем хроматическое число графа
        int chromaticNumber = calculateChromaticNumber(graph);

        // Проверяем, равно ли хроматическое число 3
        return chromaticNumber == 3;
    }

    private int calculateChromaticNumber(Graph graph) {
        Map<UUID, Integer> colorAssignment = new HashMap<>();
        Set<Integer> usedColors = new HashSet<>();

        for (Vertex vertex : graph.getVertices().values()) {
            Set<Integer> adjacentColors = new HashSet<>();
            for (Edge edge : graph.getEdges()) {
                if (edge.getFromV().equals(vertex.getId())) {
                    Vertex adjacentVertex = graph.getVertices().get(edge.getToV());
                    if (colorAssignment.containsKey(adjacentVertex.getId())) {
                        adjacentColors.add(colorAssignment.get(adjacentVertex.getId()));
                    }
                } else if (edge.getToV().equals(vertex.getId())) {
                    Vertex adjacentVertex = graph.getVertices().get(edge.getFromV());
                    if (colorAssignment.containsKey(adjacentVertex.getId())) {
                        adjacentColors.add(colorAssignment.get(adjacentVertex.getId()));
                    }
                }
            }

            int assignedColor = 1;
            while (adjacentColors.contains(assignedColor)) {
                assignedColor++;
            }

            colorAssignment.put(vertex.getId(), assignedColor);
            usedColors.add(assignedColor);
        }

        return usedColors.size();
    }
    public boolean isLessThreeChromatic(Graph graph) {
        // Получаем хроматическое число графа
        int chromaticNumber = calculateChromaticNumber(graph);

        // Проверяем, равно ли хроматическое число 3
        return chromaticNumber < 3;
    }
    public static Graph removeVertexAndEdges(Graph graph, Vertex vertexToRemove) {
        // Создаем копию графа
        Graph newGraph = new Graph(graph.getDirectType(), graph.getVertexCount(), graph.getEdgeCount(),
                new HashMap<>(graph.getVertices()), new ArrayList<>(graph.getEdges()));

        // Удаляем связанные рёбра
        removeEdgesConnectedToVertex(newGraph, vertexToRemove);

        // Удаляем саму вершину
        newGraph.getVertices().remove(vertexToRemove.getId());

        return newGraph;
    }

    private static void removeEdgesConnectedToVertex(Graph graph, Vertex vertexToRemove) {
        // Получаем список рёбер графа
        List<Edge> edges = graph.getEdges();

        // Создаем итератор для безопасного удаления элементов из списка
        Iterator<Edge> iterator = edges.iterator();

        // Проходим по всем рёбрам и удаляем те, которые связаны с удаляемой вершиной
        while (iterator.hasNext()) {
            Edge edge = iterator.next();
            if (edge.getFromV().equals(vertexToRemove.getId()) || edge.getToV().equals(vertexToRemove.getId())) {
                iterator.remove(); // Удаляем ребро из списка
            }
        }
    }
}
