package ru.leti;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import ru.leti.FourConnectedGraph;

import java.util.*;

public class OnePlanarGraph implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        if (graph.getEdgeCount() > 4 * graph.getVertexCount() - 8) {
            return false;
        }

        Map<UUID, Vertex> vertexMap = new HashMap<>(graph.getVertices());

        Map<Edge, Integer> edgeIntersectionCounts = new HashMap<>();
        for (Edge edge : graph.getEdges()) {
            edgeIntersectionCounts.put(edge, 0);
        }

        List<Edge> edges = new ArrayList<>(graph.getEdges());
        for (int i = 0; i < edges.size(); i++) {
            for (int j = i + 1; j < edges.size(); j++) {
                Edge edge1 = edges.get(i);
                Edge edge2 = edges.get(j);

                if (edgesIntersect(edge1, edge2, vertexMap)) {
                    edgeIntersectionCounts.put(edge1, edgeIntersectionCounts.get(edge1) + 1);
                    edgeIntersectionCounts.put(edge2, edgeIntersectionCounts.get(edge2) + 1);
                }
            }
        }


        for (int count : edgeIntersectionCounts.values()) {
            if (count > 1) {
                return false;
            }
        }

        return true;
    }


    private boolean edgesIntersect(Edge e1, Edge e2, Map<UUID, Vertex> vertexMap) {
        Vertex v1 = vertexMap.get(e1.getFromV());
        Vertex v2 = vertexMap.get(e1.getToV());
        Vertex v3 = vertexMap.get(e2.getFromV());
        Vertex v4 = vertexMap.get(e2.getToV());

        // Проверяем, не имеют ли рёбра общую вершину
        if (v1.equals(v3) || v1.equals(v4) || v2.equals(v3) || v2.equals(v4)) {
            return false; // Рёбра с общей вершиной не считаются пересекающимися
        }

        // Вычисляем ориентацию трёх точек
        int orient1 = orientation(v1, v2, v3);
        int orient2 = orientation(v1, v2, v4);
        int orient3 = orientation(v3, v4, v1);
        int orient4 = orientation(v3, v4, v2);

        // Проверка общего случая пересечения рёбер

        // Если ориентации различны, значит рёбра пересекаются
        if (orient1 != orient2 && orient3 != orient4){
            if (orient1 == 0 || orient2 == 0 || orient3 == 0 || orient4 == 0){
                if (orient1 == orient4 || orient1 == orient3 || orient2 == orient3 || orient2 == orient4) return false;
            }
            else return true;
        }

        


        return false; // Рёбра не пересекаются
    }

    // Метод для проверки, находится ли точка r на отрезке pq
    private boolean isOnSegment(Vertex p, Vertex q, Vertex r) {
        return Math.min(p.getXCoordinate(), q.getXCoordinate()) <= r.getXCoordinate() &&
                r.getXCoordinate() <= Math.max(p.getXCoordinate(), q.getXCoordinate()) &&
                Math.min(p.getYCoordinate(), q.getYCoordinate()) <= r.getYCoordinate() &&
                r.getYCoordinate() <= Math.max(p.getYCoordinate(), q.getYCoordinate());
    }

    // Метод для определения ориентации трёх точек
    private int orientation(Vertex p, Vertex q, Vertex r) {
        int val = (q.getYCoordinate() - p.getYCoordinate()) * (r.getXCoordinate() - q.getXCoordinate()) -
                (q.getXCoordinate() - p.getXCoordinate()) * (r.getYCoordinate() - q.getYCoordinate());

        if (val == 0) return 0;  // коллинеарны
        return (val > 0) ? 1 : -1; // часовая или против часовой стрелки
    }
    public static boolean isGraphConnected(Graph graph) {
        // Используем метод из предыдущего класса для проверки связности
        return FourConnectedGraph.isGraphConnected(graph);
    }
}
