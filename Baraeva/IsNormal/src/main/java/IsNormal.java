import com.mathsystem.api.graph.model.*;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import com.mathsystem.domain.graph.repository.Color;

import java.util.*;


public class IsNormal implements GraphProperty {
    int edges_count;
    int vertexes_count;
    boolean flag;
    boolean[] is_visited;
    ArrayList<ArrayList<Integer>> adjacency_list;
    List<Edge> edges;
    List<UUID> vertexes;

    @Override
    public boolean execute(Graph graph) {
        setValues(graph); // Установка значений переменных переданного графа

        // Запускаем DFS для каждой вершины, если хотя бы одно множество не является независимым, то возвращается false
        for (int i = 0; i < vertexes_count; i ++) {
            if (!flag)
                break;
            dfs(i, graph);
        }
        return flag;
    }

    // Установка значений переменных переданного графа
    private void setValues(Graph graph) {
        flag = true;
        vertexes_count = graph.getVertexCount();
        edges_count = graph.getEdgeCount();
        edges = graph.getEdges();
        vertexes = new ArrayList<>();
        adjacency_list = new ArrayList<>(vertexes_count);
        is_visited = new boolean[vertexes_count];

        for (int i = 0; i < vertexes_count; i++)
            adjacency_list.add(new ArrayList<>());

        // Заполнение списка смежности
        for (int i = 0; i < edges_count; i++) {
            Edge edge = edges.get(i);
            if (!vertexes.contains(edge.getFromV()))
                vertexes.add(edge.getFromV());
            if (!vertexes.contains(edge.getToV()))
                vertexes.add(edge.getToV());
            addEdge(vertexes.indexOf(graph.getEdges().get(i).getFromV()), vertexes.indexOf(graph.getEdges().get(i).getToV()));
        }
    }

    // Добавление ребра в список смежности
    private void addEdge(int v, int w) {
        adjacency_list.get(v).add(w);
        adjacency_list.get(w).add(v);
    }

    // Обход в глубину для проверки на нормальность вершин
    private void dfs(int current, Graph graph) {
        is_visited[current] = true;
        for (int target : adjacency_list.get(current)) {
            Color current_color = graph.getVertices().get(vertexes.get(current)).getColor();
            Color target_color = graph.getVertices().get(vertexes.get(target)).getColor();
            if (current_color == target_color && current_color != Color.gray) { // Если цвета текущей и следующей вершин совпадают, то множество не является независимым
                flag = false;
                break;
            }
            if (!is_visited[target] && flag)
                dfs(target, graph);
        }
    }
}
