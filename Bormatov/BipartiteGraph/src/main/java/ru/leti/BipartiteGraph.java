package ru.leti;

import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class BipartiteGraph implements GraphProperty {

    private HashMap<UUID, List<Vertex>> adjList_;

    @Override
    public boolean execute(Graph graph) {
        adjList_ = createAdjList(graph);
        HashMap<UUID, Integer> color = new HashMap<>(graph.getVertexCount());
        for (var uuid : adjList_.keySet())
        {
            color.put(uuid, -1);
        }

        for (var uuid : adjList_.keySet())
        {
            if (color.get(uuid) == -1 && (!dfs(uuid, color, 0)))
                return false;
        }

        return true;
    }

    // Создание списка смежности для заданного графа
    private HashMap<UUID, List<Vertex>> createAdjList(Graph graph) {
        Map<UUID, Vertex> vertices = graph.getVertices();
        List<Edge> edges = graph.getEdges();
        HashMap<UUID, List<Vertex>> adjList = new HashMap<>();
        for (Vertex vertex : vertices.values()) {
            adjList.put(vertex.getId(), new ArrayList<>());
        }

        for (Edge edge : edges) {
            adjList.get(edge.getFromV()).add(vertices.get(edge.getToV()));
            adjList.get(edge.getToV()).add(vertices.get(edge.getFromV()));
        }
        return adjList;
    }

    boolean dfs(UUID src, HashMap<UUID, Integer> color, Integer c)
    {
        color.put(src, c);
        for (var vertex : adjList_.get(src))
        {
            var id = vertex.getId();
            if (color.get(id) == -1)
            {
                if (!dfs(id, color, 1 - c))
                    return false;
            } else if (Objects.equals(color.get(id), color.get(src)))
                return false;
        }
        return true;
    }
}
