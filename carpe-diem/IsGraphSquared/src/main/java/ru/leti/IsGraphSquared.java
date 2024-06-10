package ru.leti;

import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import com.mathsystem.api.graph.model.*;
import java.util.HashMap;


import java.util.*;

public class IsGraphSquared implements GraphProperty {

    private HashMap<UUID, List<Vertex>> createHashMapGraph(Map<UUID, Vertex> vertices, List<Edge> edges)  {
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

private HashMap<UUID, List<Vertex>> createHashMapСlosure(HashMap<UUID, List<Vertex>> map)  {
    HashMap<UUID, List<Vertex>> tranz = new HashMap<>();
    for (var vertex1 : map.keySet()) {
        for (var vertex2 : map.get(vertex1)) {
            for (var vertex3 : map.get(vertex2.getId())) {
                if (map.get(vertex1).contains(vertex3)) {
                    tranz.put(vertex1, new ArrayList<>());
                    tranz.get(vertex1).add(vertex3);
                }
            }
        }
    }
    return tranz;
}

    public boolean check_is_squared(Graph graph) {
        boolean is_squared = true;
        HashMap<UUID, List<Vertex>> map = createHashMapGraph(graph.getVertices(), graph.getEdges());
        HashMap<UUID, List<Vertex>> tranz= createHashMapСlosure(map);
        for (var vertex1 : map.keySet()) {
            for (var vertex2 : map.get(vertex1)) {
                for (var vertex3 : map.get(vertex2.getId())) {
                    if (!map.get(vertex1).contains(vertex3) && (!tranz.keySet().contains(vertex1) || tranz.keySet().contains(vertex1) && !tranz.get(vertex1).contains(vertex2)) &&  (!tranz.keySet().contains(vertex2.getId()) || tranz.keySet().contains(vertex2.getId()) && !tranz.get(vertex2.getId()).contains(vertex3)) && !vertex3.getId().equals(vertex1)){
                        is_squared = false;
                        return is_squared;
                    }
                }
            }
        }
        return is_squared;
    }

    @Override
    public boolean execute(Graph graph) {
        return check_is_squared(graph);
    }
}