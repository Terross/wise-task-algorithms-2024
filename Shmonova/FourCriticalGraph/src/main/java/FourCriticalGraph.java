import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import java.util.stream.Collectors;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class FourCriticalGraph implements GraphProperty {
    // Метод для сортировки вершин графа по их степени
    public static List<UUID> SortVertices(List<UUID> vertices, List<Edge> edges) {
        // Создаем копию списка вершин для сортировки
        List<UUID> sorted = new ArrayList<>(vertices);
        // Сортируем список вершин на основе их степеней и количества связанных степеней
        sorted.sort((uuid1, uuid2) -> {
            // Вычисляем разницу в степенях между двумя вершинами
            int degreeDiff = getDegree(uuid2, edges) - getDegree(uuid1, edges);
            // Если степени различаются, используем эту разницу как ключ сортировки
            if (degreeDiff != 0) {
                return degreeDiff;
            }
            // В случае равных степеней, вычисляем количество связанных степеней для каждой вершины
            int v1Count = countConnectedDegrees(uuid1, edges);
            int v2Count = countConnectedDegrees(uuid2, edges);
            // Используем разницу в количестве связанных степеней как ключ сортировки
            degreeDiff = v2Count - v1Count;
            return degreeDiff;
        });
        return sorted;
    }

    // Метод для получения степени вершины, учитывая все ребра, соединяющие ее с другими вершинами
    private static int getDegree(UUID id, List<Edge> edges) {
        return (int) edges.stream()
                .filter(edge -> edge.getFromV().equals(id) || edge.getToV().equals(id))
                .count();
    }

    // Метод для подсчета общего количества связанных степеней для заданной вершины
    private static int countConnectedDegrees(UUID id, List<Edge> edges) {
        // Аналогично предыдущему методу, но вместо подсчета прямых степеней, мы суммируем степени всех соседних вершин
        return edges.stream()
                .filter(edge -> edge.getFromV().equals(id) || edge.getToV().equals(id))
                .mapToInt(edge -> getDegree(edge.getFromV().equals(id) ? edge.getToV() : edge.getFromV(), edges))
                .sum();
    }

    // Метод для обновления списка ребер и сортировки вершин
    public List<UUID> update(List<UUID> vertices, List<Edge> edges) {
        List<Edge> copy_edges = new ArrayList<>();
        for (Edge edge : edges) {
            UUID from = edge.getFromV();
            UUID to = edge.getToV();
            // Добавляем ребро в новый список, если обе его вершины присутствуют в списке вершин
            if (vertices.contains(from) && vertices.contains(to)) {
                copy_edges.add(edge);
            }
        }
        edges.clear(); // Очищаем исходный список ребер
        edges.addAll(copy_edges); // Заполняем его обновленными ребрами
        return SortVertices(vertices, copy_edges); // Возвращаем отсортированный список вершин
    }

    private Integer getChromaticNumber(Graph graph) {
        // Получаем все ребра и вершины графа, после чего сортируем из
        List<Edge> edges = graph.getEdges();
        Map<UUID, Vertex> vertices = graph.getVertices();
        List<UUID> sorted_vertices = SortVertices(vertices.keySet().stream().toList(), edges);

        Map<UUID, Integer> colors = new HashMap<>();
        // Присваиваем каждой вершине начальный цвет (-1)
        for (UUID id : graph.getVertices().keySet()) {
            colors.put(id, -1);
        }
        int chromaticNum = 0;
        UUID current_v;
        while (!sorted_vertices.isEmpty()) {
            chromaticNum += 1;
            current_v = sorted_vertices.remove(0); // Извлекаем первую вершину из отсортированного списка
            colors.put(current_v, chromaticNum); // Присваиваем ей текущий цвет
            List<UUID> to_delete = new ArrayList<>(); // Список вершин для удаления
            for (UUID v : sorted_vertices) { // Проходим по оставшимся вершинам, не смежным с текущей
                boolean is_adjacent = false;
                for (Edge edge : edges) { // Проверяем каждое ребро
                    UUID from = edge.getFromV();
                    UUID to = edge.getToV();
                    if (from.compareTo(v) == 0 || to.compareTo(v) == 0) { // Если вершина является одной из конечных точек ребра
                        if (from.compareTo(v) == 0) {
                            if (to.compareTo(current_v) == 0) { // Если вершина смежна с текущей
                                is_adjacent = true;
                                break;
                            }
                        } else {
                            if (from.compareTo(current_v) == 0) { // То же самое для второй конечной точки ребра
                                is_adjacent = true;
                                break;
                            }
                        }
                    }
                }
                // Если вершина не смежна с текущей и еще не окрашена, проверяем возможность окраски
                if (!is_adjacent && colors.get(v) == -1) {
                    boolean can_fill = true;    // Флаг возможности окраски
                    for (Edge edge : edges) {
                        UUID from = edge.getFromV();
                        UUID to = edge.getToV();
                        if (from.compareTo(v) == 0 || to.compareTo(v) == 0) {
                            if (from.compareTo(v) == 0) {
                                if (colors.get(to) == chromaticNum) { // Если смежная вершина уже окрашена в текущий цвет
                                    can_fill = false;
                                    break;
                                }
                            } else {
                                if (colors.get(from) == chromaticNum) { // То же самое для второй конечной точки ребра
                                    can_fill = false;
                                    break;
                                }
                            }
                        }
                    }
                    // Если возможно окрасить вершину, окрашиваем ее и добавляем в список для удаления
                    if (can_fill) {
                        colors.put(v, chromaticNum);
                        to_delete.add(v);
                    }
                }
            }
            // Удаляем окрашенные вершины из списка
            for (UUID v1 : to_delete) {
                sorted_vertices.remove(v1);
            }
            // Обновляем список вершин после удаления окрашенных
            sorted_vertices = update(sorted_vertices, edges);
        }
        return chromaticNum;
    }

    public Graph removeEdge(Graph graph, Edge edgeToRemove) {
        // Создаем новый граф с тем же типом, количеством вершин и ребер, но без указанного ребра.
        Graph newGraph = new Graph(
                graph.getDirectType(),
                graph.getVertexCount(),
                graph.getEdgeCount() - 1, // Уменьшаем количество ребер на 1, так как одно будет удалено
                new HashMap<>(graph.getVertices()), // Копируем все вершины
                new ArrayList<>(graph.getEdges()).stream() // Копируем все ребра
                        .filter(e -> !e.equals(edgeToRemove)) // Исключаем удаляемое ребро
                        .collect(Collectors.toList()) // Собираем результат в список
        );
        return newGraph;
    }

    public static Graph removeVertexAndEdges(Graph graph, Vertex vertexToRemove) {
        // Создаем копию графа
        Graph newGraph = new Graph(graph.getDirectType(), graph.getVertexCount(), graph.getEdgeCount(),
                new HashMap<>(graph.getVertices()), new ArrayList<>(graph.getEdges()));
        // Удаляем связанные ребра
        List<Edge> edges = newGraph.getEdges();
        Iterator<Edge> iterator = edges.iterator();
        while (iterator.hasNext()) {
            Edge edge = iterator.next();
            if (edge.getFromV().equals(vertexToRemove.getId()) || edge.getToV().equals(vertexToRemove.getId())) {
                iterator.remove(); // Удаляем ребро из списка
            }
        }
        // Удаляем саму вершину
        newGraph.getVertices().remove(vertexToRemove.getId());
        return newGraph;
    }

    @Override
    public boolean execute(Graph graph) {
        if(getChromaticNumber(graph) != 4)
            return false;
        List<Vertex> vertices = new ArrayList<>(graph.getVertices().values());
        List<Edge> edges = graph.getEdges();
        // Проходим по всем вершинам
        for (Vertex vertex : vertices) {
            Graph newGraph = removeVertexAndEdges(graph, vertex);
            if(getChromaticNumber(newGraph)<4)
                return true;
        }
        // Проходим по всем ребрам
        for (Edge edge : edges) {
            Graph newGraph = removeEdge(graph, edge);
            if(getChromaticNumber(newGraph)<4)
                return true;
        }
        return false;
    }
}
