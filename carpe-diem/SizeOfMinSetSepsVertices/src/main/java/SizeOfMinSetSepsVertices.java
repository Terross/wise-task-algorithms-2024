import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.util.*;

public class SizeOfMinSetSepsVertices implements GraphCharacteristic {

    // Создание списка смежности для заданного графа
    private HashMap<UUID, List<Vertex>> createAdjList(Graph graph)
    {
        Map<UUID, Vertex> vertices = graph.getVertices();
        List<Edge> edges = graph.getEdges();
        HashMap<UUID, List<Vertex>> adjList = new HashMap<>();
        for (var vertex : vertices.values())
        {
            adjList.put(vertex.getId(), new ArrayList<>());
        }

        for (var edge : edges)
        {
            adjList.get(edge.getFromV()).add(vertices.get(edge.getToV()));
        }
        return adjList;
    }

    @Override
    public Integer execute(Graph graph) {
        Vertex startVertex = null;
        Vertex finishVertex = null;
        // Находим начало и конец пути
        for (Vertex vertex : graph.getVertices().values()) {
            if (Color.blue.equals(vertex.getColor()))
                startVertex = vertex;
            if (Color.red.equals(vertex.getColor()))
                finishVertex = vertex;
            if (startVertex != null && finishVertex != null)
                break;
        }
        // Обрабатываем случай, когда вершины не найдены
        if (startVertex == null || finishVertex == null) {
            return -1;
        }

        Map<UUID, Vertex> vertices = graph.getVertices();
        // Создаем хешмапу посещенных вершным
        Map<UUID, Boolean> visited = new HashMap<>();
        for (Vertex vertex : vertices.values()) {
            visited.put(vertex.getId(), false);
        }
        Map<UUID, List<Vertex>> adjList = createAdjList(graph);

        // Вычитаем 1 для того, чтобы вывести количество вершин на пути, а не ребер
        int result = bfs(startVertex,finishVertex, vertices, visited, adjList);
        return result > 0 ? result - 1 : -1;
    }



    private Integer bfs(Vertex start, Vertex finish, Map<UUID, Vertex> vertices,   Map<UUID, Boolean> visited, Map<UUID, List<Vertex>> adjList)
    {
        visited.put(start.getId(), true);
        List<Vertex> queue = new ArrayList<>();
        HashMap<Vertex, Integer> length = new HashMap<>();
        for (var vertex : vertices.values()) {
            length.put(vertex, -1);
        }
        length.put(start, 0);
        queue.add(start);
        while (!queue.isEmpty())
        {
             Vertex now = queue.remove(0);
             for (Vertex neighbour : adjList.get(now.getId())){
                 if (!visited.get(neighbour.getId())){
                     visited.put(neighbour.getId(), true);
                     queue.add(neighbour);
                     length.put(neighbour, length.get(now) + 1);
                 }
             }
        }
        return length.get(finish);
    }

}
