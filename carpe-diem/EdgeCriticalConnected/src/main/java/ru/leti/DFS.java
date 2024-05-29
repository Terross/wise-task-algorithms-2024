package ru.leti;

import com.mathsystem.api.graph.model.*;

import java.util.*;

public class DFS {
    /**
     * Performs a depth-first search on the given graph starting from the specified vertex.
     *
     * @param graph         the graph to perform the depth-first search on
     * @param startVertexId the ID of the vertex to start the search from
     * @return a set of UUIDs representing the visited vertices during the search
     */
    public Set<UUID> depthFirstSearch(Graph graph, UUID startVertexId) {
        Set<UUID> visitedSet = new HashSet<>();
        depthFirstSearchUtil(graph, startVertexId, visitedSet);
        return visitedSet;
    }

    /**
     * Performs a depth-first search traversal starting from the specified vertex.
     * This method is a utility method used by the depthFirstSearch method.
     *
     * @param graph       the graph to perform the depth-first search on
     * @param vertexId    the ID of the vertex to start the traversal from
     * @param visitedSet  a set to keep track of visited vertices
     */
    private void depthFirstSearchUtil(Graph graph, UUID vertexId, Set<UUID> visitedSet) {
        visitedSet.add(vertexId);

        for (Edge edge : graph.getEdges()) {
            if (edge.getFromV().equals(vertexId) && !visitedSet.contains(edge.getToV())) {
                depthFirstSearchUtil(graph, edge.getToV(), visitedSet);
            }
            if (edge.getToV().equals(vertexId) && !visitedSet.contains(edge.getFromV())) {
                depthFirstSearchUtil(graph, edge.getFromV(), visitedSet);
            }
        }
    }
}