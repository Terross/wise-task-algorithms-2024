import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.graph.repository.Color;

import java.util.*;

public class SizeOfMinSetSeparatingVertices implements GraphCharacteristic {
    // Ключевые вершины должны быть синего цвета
    final Color color = Color.blue;

    // Подсчет вершин синего цвета, по условию их должно быть ровно 2
    private Integer countVertex(Graph graph) {
        Integer counter = 0;

        for (var vertex : graph.getVertices().entrySet())
            if (vertex.getValue().getColor().equals(color))
                counter++;

        return counter;
    }

    // Получение списка смежности
    private Map<UUID, List<UUID>> getAdjacencyList(Graph graph) {
        Map<UUID, List<UUID>> adjacencyList = new HashMap<>();
        graph.getVertices().keySet().forEach(key -> adjacencyList.put(key, new ArrayList<>()));

        for (Edge edge : graph.getEdges()) {
            UUID key = edge.getFromV();
            UUID value = edge.getToV();
            adjacencyList.get(key).add(value);
            if (graph.getDirectType().equals(GraphType.UNDIRECTED))
                adjacencyList.get(value).add(key);
        }

        return adjacencyList;
    }

    // Функция для нахождения ключевых вершин
    private AbstractMap.SimpleEntry<UUID, UUID> getStartEndVertex(Graph graph) {
        boolean flag_one_vertex = false;
        UUID first_vertex = null;

        for (var vertex : graph.getVertices().entrySet())
            if (vertex.getValue().getColor().equals(color)) {
                if (flag_one_vertex)
                    return new AbstractMap.SimpleEntry<>(first_vertex, vertex.getKey());
                first_vertex = vertex.getKey();
                flag_one_vertex = true;
            }

        return null;
    }

    @Override
    public Integer execute(Graph graph) {
        // Если в графе не две ключевые вершины
        if (!countVertex(graph).equals(2))
            return -1;

        // Создаем список смежности
        Map<UUID, List<UUID>> adjacencyList = getAdjacencyList(graph);

        var tmp_simple_empty = getStartEndVertex(graph);
        UUID start = tmp_simple_empty.getKey();
        UUID end = tmp_simple_empty.getValue();
        boolean no_way = false;
        Integer counter = 0;

        // Запускаем поиск в ширину, находим путь и удаляем его
        // После каждого удаления увеличиваем счетчик на 1
        // Таким образом мы получаем размер минимального разделяющего множества
        while (!no_way) {
            no_way = true;
            // Создаем очередь
            Deque<UUID> queue = new ArrayDeque<>();
            // Кладем стартовую вершину в очередь
            queue.offer(start);
            // Создаем путь
            Map<UUID, UUID> way = new HashMap<>();
            graph.getVertices().keySet().forEach(key -> way.put(key, null));
            // Запоминаем посещенные вершины
            Set<UUID> visitedVertex = new HashSet<>();
            visitedVertex.add(start);

            // Пока очередь не пуста
            while (!queue.isEmpty()) {
                UUID vertex = queue.poll();

                // Если дошли до конца увеличиваем счетчик
                if (vertex.equals(end)) {
                    no_way = false;
                    counter++;
                    break;
                }

                // Продолжаем построение пути
                for (UUID neighbor : adjacencyList.get(vertex)) {
                    if (!visitedVertex.contains(neighbor)) {
                        way.put(neighbor, vertex);
                        visitedVertex.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }

            UUID tmpVertex = end;
            // Удаляем путь
            while (!tmpVertex.equals(start) && way.get(tmpVertex) != null) {
                adjacencyList.get(way.get(tmpVertex)).remove(tmpVertex);
                if (graph.getDirectType().equals(GraphType.UNDIRECTED))
                    adjacencyList.get(tmpVertex).remove(way.get(tmpVertex));
                tmpVertex = way.get(tmpVertex);
            }
        }

        return counter;
    }
}
