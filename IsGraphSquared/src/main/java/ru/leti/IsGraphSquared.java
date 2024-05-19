package ru.leti;

import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import com.mathsystem.api.graph.model.*;
import java.util.HashMap;
import java.util.Set;


import java.util.*;

public class IsGraphSquared implements GraphProperty {

    private HashMap<UUID, List<Vertex>> createAdjList(Map<UUID, Vertex> vertices, List<Edge> edges)  {
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

    public boolean depthFirstSearch(Graph graph) {
        boolean is_second_degree = true;
        Set<UUID> visitedSet = new HashSet<>();

        HashMap<UUID, List<Vertex>> map = createAdjList(graph.getVertices(), graph.getEdges());
        HashMap<UUID, List<Vertex>> tranz = new HashMap<>();

        for (var vertex1 : map.keySet()) {
            for (var vertex2 : map.get(vertex1)) {
                for (var vertex3 : map.get(vertex2.getId())) {
                    //.out.println(graph.getVertices().get(vertex1).getColor());
                    //System.out.println(vertex2.getColor());
                    //System.out.println(vertex3.getColor());
                    if (map.get(vertex1).contains(vertex3)) {
                        //System.out.println("YES");
                        tranz.put(vertex1, new ArrayList<>());
                        tranz.get(vertex1).add(vertex3);
                    }
                    //System.out.println("\n");
                }
            }
        }
        //for (var vert1 : tranz.keySet()) {
        //    for (var vert2 : tranz.get(vert1)) {
        //        System.out.println(graph.getVertices().get(vert1).getColor());
        //        System.out.println(vert2.getColor());
        //        System.out.println("\n");
        //    }
        //}
        for (var vertex1 : map.keySet()) {
            for (var vertex2 : map.get(vertex1)) {
                for (var vertex3 : map.get(vertex2.getId())) {
                    if (!map.get(vertex1).contains(vertex3) && (!tranz.keySet().contains(vertex1) || tranz.keySet().contains(vertex1) && !tranz.get(vertex1).contains(vertex2)) &&  (!tranz.keySet().contains(vertex2.getId()) || tranz.keySet().contains(vertex2.getId()) && !tranz.get(vertex2.getId()).contains(vertex3)) && !vertex3.getId().equals(vertex1)) {
                        //boolean a = !map.get(vertex1).contains(vertex3);
                        //boolean b = !tranz.keySet().contains(vertex1) || tranz.keySet().contains(vertex1) && !tranz.get(vertex1).contains(vertex2);
                        //boolean c = !tranz.keySet().contains(vertex2.getId()) || tranz.keySet().contains(vertex2.getId()) && !tranz.get(vertex1).contains(vertex3);
                        //System.out.println(a);
                        //System.out.println(b);
                        //System.out.println(c);
                        //System.out.println(graph.getVertices().get(vertex1).getColor());
                        //System.out.println(vertex2.getColor());
                        //System.out.println(vertex3.getColor());
                        is_second_degree = false;
                        return is_second_degree;
                    }
                }
            }
        }
        return is_second_degree;
    }

    @Override
    public boolean execute(Graph graph) {
        return depthFirstSearch(graph);
    }
}