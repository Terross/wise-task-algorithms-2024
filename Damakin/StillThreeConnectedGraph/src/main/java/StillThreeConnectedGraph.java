import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class StillThreeConnectedGraph implements GraphProperty {

    @Override
    public boolean execute(Graph graph) {
        List<Edge> edges = graph.getEdges();
        if(!ThreeConnected(graph)){
            return false;
        } else {
            for (Edge edge : edges) {
                Graph graphtmp = removeEdge(graph, edge);
                if (ThreeConnected(graphtmp)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean ThreeConnected(Graph graph){
        if(graph.getVertexCount() <= 3){
            return false;
        }
        List<Vertex> vertices = new ArrayList<>(graph.getVertices().values());
        for (Vertex vertex1 : vertices) {
            for (Vertex vertex2 : vertices) {
                if (vertex1.equals(vertex2)) {
                    continue;
                } else {
                    // Создаем копию графа перед удалением вершин и связанных с ними рёбер
                    Graph newGraph = removeVertexAndEdges(graph, vertex1);
                    newGraph = removeVertexAndEdges(newGraph, vertex2);

                    // Проверяем, остается ли граф связным после удаления двух вершин
                    if (!isConnected(newGraph)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public Graph removeEdge(Graph graph, Edge edgeToRemove) {
        // Создаем новый граф, который будет копией исходного
        Graph newGraph = new Graph(graph.getDirectType(), graph.getVertexCount(), graph.getEdgeCount(),
                new HashMap<>(graph.getVertices()), new ArrayList<>(graph.getEdges()));

        // Удаляем указанное ребро из списка рёбер нового графа
        newGraph.getEdges().remove(edgeToRemove);

        return newGraph;
    }


    public Graph removeVertexAndEdges(Graph graph, Vertex vertexToRemove) {
        // Создаем копию графа
        Graph newGraph = new Graph(graph.getDirectType(), graph.getVertexCount(), graph.getEdgeCount(),
                new HashMap<>(graph.getVertices()), new ArrayList<>(graph.getEdges()));

        // Удаляем связанные рёбра
        removeEdgesConnectedToVertex(newGraph, vertexToRemove);

        // Удаляем саму вершину
        newGraph.getVertices().remove(vertexToRemove.getId());

        return newGraph;
    }

    private void removeEdgesConnectedToVertex(Graph graph, Vertex vertexToRemove) {
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