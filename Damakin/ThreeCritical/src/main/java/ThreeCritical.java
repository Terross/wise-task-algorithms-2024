
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
        // Проходим по всем вершинам
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
        // Проходим по всем ребрам
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

    public boolean isThreeChromatic(Graph graph){
        if (graph.getVertexCount() < 3)
            return false;
        List<Edge> edges = graph.getEdges();
        Map<UUID, Vertex> vertices = graph.getVertices();
        List<UUID> sorted_vertices = sort_vertices(vertices.keySet().stream().toList(), edges);

        Map<UUID, Integer> colors = new HashMap<>();
        for (UUID id : graph.getVertices().keySet()) {
            colors.put(id, -1);
        }
        int chromaticNum = 0;
        UUID current_v;
        // Берем текущую вершину, обновляем хроматическое число, создаем список для удаления вершин
        while (!sorted_vertices.isEmpty()) {
            chromaticNum += 1;
            if (chromaticNum > 3){
                return false;
            }
            current_v = sorted_vertices.remove(0);
            colors.put(current_v, chromaticNum);
            List<UUID> to_delete = new ArrayList<>();
            // Ищем не смежные вершины
            for (UUID v : sorted_vertices){
                boolean is_adjacent = false;
                for (Edge edge : edges) {
                    UUID from = edge.getFromV();
                    UUID to = edge.getToV();
                    if (from.compareTo(v) == 0 || to.compareTo(v) == 0) {
                        if (from.compareTo(v) == 0) {
                            if (to.compareTo(current_v) == 0){
                                is_adjacent = true;
                                break;
                            }
                        } else {
                            if (from.compareTo(current_v) == 0){
                                is_adjacent = true;
                                break;
                            }
                        }
                    }
                }
                // Если не смежная и можно покрасить
                if (!is_adjacent && colors.get(v) == -1) {
                    boolean can_fill = true;
                    for (Edge edge : edges) {
                        UUID from = edge.getFromV();
                        UUID to = edge.getToV();
                        if (from.compareTo(v) == 0 || to.compareTo(v) == 0) {
                            if (from.compareTo(v) == 0) {
                                if (colors.get(to) == chromaticNum) {
                                    can_fill = false;
                                    break;
                                }
                            } else {
                                if (colors.get(from) == chromaticNum) {
                                    can_fill = false;
                                    break;
                                }
                            }
                        }
                    }
                    // Если можно покрасить, красим и добавляем вв список для удаления
                    if (can_fill) {
                        colors.put(v, chromaticNum);
                        to_delete.add(v);
                    }
                }
            }
            for (UUID v1 : to_delete) {
                sorted_vertices.remove(v1);
            }
            sorted_vertices = update(sorted_vertices, edges);
        }
        return chromaticNum == 3;

    }

    public boolean isLessThreeChromatic(Graph graph){
        if (graph.getVertexCount() < 3)
            return false;
        List<Edge> edges = graph.getEdges();
        Map<UUID, Vertex> vertices = graph.getVertices();
        List<UUID> sorted_vertices = sort_vertices(vertices.keySet().stream().toList(), edges);

        Map<UUID, Integer> colors = new HashMap<>();
        for (UUID id : graph.getVertices().keySet()) {
            colors.put(id, -1);
        }
        int chromaticNum = 0;
        UUID current_v;
        // Берем текущую вершину, обновляем хроматическое число, создаем список для удаления вершин
        while (!sorted_vertices.isEmpty()) {
            chromaticNum += 1;
            if (chromaticNum > 3){
                return false;
            }
            current_v = sorted_vertices.remove(0);
            colors.put(current_v, chromaticNum);
            List<UUID> to_delete = new ArrayList<>();
            // Ищем не смежные вершины
            for (UUID v : sorted_vertices){
                boolean is_adjacent = false;
                for (Edge edge : edges) {
                    UUID from = edge.getFromV();
                    UUID to = edge.getToV();
                    if (from.compareTo(v) == 0 || to.compareTo(v) == 0) {
                        if (from.compareTo(v) == 0) {
                            if (to.compareTo(current_v) == 0){
                                is_adjacent = true;
                                break;
                            }
                        } else {
                            if (from.compareTo(current_v) == 0){
                                is_adjacent = true;
                                break;
                            }
                        }
                    }
                }
                // Если не смежная и можно покрасить
                if (!is_adjacent && colors.get(v) == -1) {
                    boolean can_fill = true;
                    for (Edge edge : edges) {
                        UUID from = edge.getFromV();
                        UUID to = edge.getToV();
                        if (from.compareTo(v) == 0 || to.compareTo(v) == 0) {
                            if (from.compareTo(v) == 0) {
                                if (colors.get(to) == chromaticNum) {
                                    can_fill = false;
                                    break;
                                }
                            } else {
                                if (colors.get(from) == chromaticNum) {
                                    can_fill = false;
                                    break;
                                }
                            }
                        }
                    }
                    // Если можно покрасить, красим и добавляем вв список для удаления
                    if (can_fill) {
                        colors.put(v, chromaticNum);
                        to_delete.add(v);
                    }
                }
            }
            for (UUID v1 : to_delete) {
                sorted_vertices.remove(v1);
            }
            sorted_vertices = update(sorted_vertices, edges);
        }
        return chromaticNum < 3;

    }

    public List<UUID> sort_vertices(List<UUID> vertices, List<Edge> edges) {
        List<UUID> sorted = new ArrayList<>(vertices);

        // Сортируем список UUID на основе значений степени
        sorted.sort((uuid1, uuid2) -> {
            int degreeDiff = degree(uuid2, edges) - degree(uuid1, edges);
            if (degreeDiff == 0){
                int v1_count = 0;
                int v2_count = 0;
                for (Edge edge : edges) {
                    UUID from = edge.getFromV();
                    UUID to = edge.getToV();
                    if (from.compareTo(uuid1) == 0 || to.compareTo(uuid1) == 0 ||
                            from.compareTo(uuid2) == 0 || to.compareTo(uuid2) == 0) {
                        if (from.compareTo(uuid1) == 0) {
                            v1_count += degree(to, edges);
                        } else {
                            v1_count += degree(from, edges);
                        }
                        if (from.compareTo(uuid2) == 0) {
                            v2_count += degree(to, edges);
                        } else {
                            v2_count += degree(from, edges);
                        }
                    }
                }
                degreeDiff = v2_count - v1_count;
            }
            return Integer.compare(degreeDiff, 0);
        });

        return sorted;
    }

    // Нахождение степени вершины
    public int degree(UUID id, List<Edge> edges){
        int degree = 0;
        for (Edge edge : edges){
            if (edge.getToV().compareTo(id) == 0 || edge.getFromV().compareTo(id) == 0){
                degree += 1;
            }
        }
        return degree;
    }

    // Обновление массива сортированных вершин
    public List<UUID> update(List<UUID> vertices, List<Edge> edges){
        List<Edge> copy_edges = new ArrayList<>();
        for (Edge edge : edges) {
            UUID from = edge.getFromV();
            UUID to = edge.getToV();
            if (vertices.contains(from) && vertices.contains(to)) {
                copy_edges.add(edge);
            }
        }
        edges.clear();
        edges.addAll(copy_edges);
        return sort_vertices(vertices, copy_edges);
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

