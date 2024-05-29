package ru.leti;

import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;
import java.util.*;

public class GraphToughness implements GraphCharacteristic {

    private HashMap<UUID, List<Vertex>> adjList_;
    private HashMap<UUID, Boolean> visited_;
    private int componentCount;

    @Override
    public Integer execute(Graph graph) {
        // Создание списка смежности для заданного графа
        adjList_ = createAdjList(graph);
        visited_ = new HashMap<>();
        for (var uuid : adjList_.keySet())
        {
            visited_.put(uuid, false);
        }

        return computeGraphToughness(graph);
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

    private void dfs(UUID node, HashMap<UUID, Boolean> visited)
    {
        visited.put(node, true);
        for (var vertex : adjList_.get(node))
        {
            if(!visited.get(vertex.getId())) // Use the passed 'visited' map for checking
            {
                dfs(vertex.getId(), visited);
            }
        }
    }


    private int findConnectedComponents(HashMap<UUID, List<Vertex>> tempAdjList, HashMap<UUID, Boolean> visited)
    {
        int componentCount = 0; // Renamed from 'newComponent'
        for (var uuid : tempAdjList.keySet())
        {
            if(!visited.get(uuid))
            {
                componentCount++; // Increment 'componentCount' instead of 'newComponent'
                dfs(uuid, visited);
            }
        }
        return componentCount;
    }

    private Integer computeGraphToughness(Graph graph)
    {
        int n = adjList_.size();
        int min_ratio = Integer.MAX_VALUE;

        for (var uuid : adjList_.keySet())
        {
            HashMap<UUID, Boolean> tempVisited = new HashMap<>(visited_);
            tempVisited.put(uuid, true);

            int numComponents = findConnectedComponents(adjList_, tempVisited);
            int currentRatio = (n - 1) / numComponents;
            min_ratio = Math.min(min_ratio, currentRatio);
        }

        return min_ratio;
    }
}
