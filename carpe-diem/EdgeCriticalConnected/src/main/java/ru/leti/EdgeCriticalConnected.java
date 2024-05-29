package ru.leti;

import com.mathsystem.api.graph.model.*;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class EdgeCriticalConnected implements GraphProperty {
    /**
     * Executes the algorithm to determine if the edge-connectivity of the graph changes after removing a marked edge. 
     * Color.blue is used to mark the edge.
     * 
     * @param graph the graph to be analyzed
     * @return true if the edge-connectivity of the graph changes after removing the marked edge, false otherwise
     */
    @Override
    public boolean execute(Graph graph) {
        // Find the marked edge as the first blue edge
        Edge marked_edge = null;
        for (Edge edge : graph.getEdges()) {

            if (edge.getColor().equals(Color.blue)) {
                marked_edge = edge;
                break;
            }
        }
        if (marked_edge == null) {
            return false;
        }

        // Find the edge-connectivity of the original graph
        var graph_connectivity = findKConnectivityOfGraph(graph);

        // Remove the marked edge from the graph
        var edges = graph.getEdges();
        edges.remove(marked_edge);
        List<Edge> new_edges = new ArrayList<>(edges);
        new_edges.remove(marked_edge);
        graph.setEdges(new_edges);

        // Find the edge-connectivity of the new graph without the marked edge
        var new_graph_connectivity = findKConnectivityOfGraph(graph);

        // Restore the original graph
        graph.setEdges(edges);

        // Return true if the edge-connectivity of the graph changes after removing the marked edge
        return !new_graph_connectivity.equals(graph_connectivity);


    }

    /**
     * Generates the super set of all possible subsets of edges.
     *
     * @param edges the list of edges
     * @return the super set of all possible subsets of edges
     */
    public static List<List<Edge>> getSuperSetOfEdges(List<Edge> edges) {
        int n = edges.size();
        List<List<Edge>> subsets = new ArrayList<>();

        // Generate all possible subsets
        for (int i = 0; i < (1 << n); i++) {
            List<Edge> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    subset.add(edges.get(j));
                }
            }
            subsets.add(subset);
        }

        // Sort the subsets by size in descending order
        subsets.sort((a, b) -> b.size() - a.size());
        
        return subsets;
    }

    /**
     * Finds the edge-connectivity of a graph.
     *
     * @param graph the graph to find the edge-connectivity of
     * @return the edge-connectivity of the graph as an Integer
     */
    public static Integer findKConnectivityOfGraph(Graph graph) {
        var edges = graph.getEdges();
        var subsets = getSuperSetOfEdges(graph.getEdges());
        
        // Check if the graph is connected after removing each subset of edges
        for (int i = 0; i < subsets.size(); i++) {
            // Remove the subset of edges from the graph
            graph.setEdges(subsets.get(i));
            // Check if the graph is connected
            if (!isGraphConnected(graph)) {
                // Restore the original graph
                graph.setEdges(edges);

                // Return the edge-connectivity of the graph
                return edges.size() - subsets.get(i).size();
            }
        }
        // Restore the original graph
        graph.setEdges(edges);

        return 0;
    }

    /**
     * Checks if a graph is connected.
     *
     * @param graph the graph to check
     * @return true if the graph is connected, false otherwise
     */
    public static boolean isGraphConnected(Graph graph) {
        // Perform a depth-first search on the graph
        // starting from the first vertex
        UUID key = graph.getVertices().keySet().iterator().next();

        var dfs = new DFS();

        // Get the set of visited vertices
        Set<UUID> visited = dfs.depthFirstSearch(graph, key);

        // Check if all vertices have been visited
        for (UUID v : graph.getVertices().keySet()) {
            if (!visited.contains(v)) {
                return false;
            }
        }

        return true;
    }


}


