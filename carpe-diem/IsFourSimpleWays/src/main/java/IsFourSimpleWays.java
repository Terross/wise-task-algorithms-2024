import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.oldmodel.directed.DirectedGraph;
import com.mathsystem.api.graph.oldmodel.undirected.UndirectedGraph;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;


public class IsFourSimpleWays implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        // check if all vertices have degree of at least 4
        if (!allVerticesHaveDegreeOfAtLeastLimit(graph, 4)) {
            return false;
        }

        // TODO: consider whether it is correct to consider all possible pairs with only the first vertex at all, or whether it is required to consider all possible pairs?
        Vertex initialVertex = graph.getVertices().entrySet().iterator().next().getValue();

        // iterate through all vertices in the graph
        for (Map.Entry<UUID, Vertex> entry : graph.getVertices().entrySet()) {
            Vertex currentVertex = entry.getValue();

            // check if there are at least four simple paths between the initial vertex and the current vertex
            if (!hasFourSimplePathsBetween(initialVertex, currentVertex, graph)) {
                return false;
            }
        }

        return true;
    }


    public static boolean allVerticesHaveDegreeOfAtLeastLimit(Graph graph, Integer limit) {
        Map<UUID, Integer> degrees = calculateDegrees(graph);

        for (Map.Entry<UUID, Integer> entry : degrees.entrySet()) {
            if (entry.getValue() < limit) {
                return false;
            }
        }

        return true;
    }


    public static Map<UUID, Integer> calculateDegrees(Graph graph) {
        Map<UUID, Integer> degrees = new HashMap<>();

        // all vertices degrees are 0 initially
        for (UUID vertexId : graph.getVertices().keySet()) {
            degrees.put(vertexId, 0);
        }

        // iterate through each edge in the graph
        for (Edge edge : graph.getEdges()) {
            UUID sourceVertexId = edge.getFromV();
            UUID targetVertexId = edge.getToV();

            // increment degrees for source
            degrees.put(sourceVertexId, degrees.get(sourceVertexId) + 1);

            // increment degrees for target as well in case of undirected graph
            if (graph.getDirectType() == GraphType.UNDIRECTED) {
                degrees.put(targetVertexId, degrees.get(targetVertexId) + 1);
            }
        }

        return degrees;
    }


    public List<UUID> getSimplePathBetween(Vertex start, Vertex end, Map<UUID, List<UUID>> adjacentList) {
        List<UUID> path = new ArrayList<UUID>();
        Map<UUID, UUID> parentMap = new HashMap<UUID, UUID>();
        Queue<UUID> queue = new LinkedList<UUID>();
        Set<UUID> visited = new HashSet<UUID>();

        queue.add(start.getId());
        visited.add(start.getId());

        while (!queue.isEmpty()) {
            UUID current = queue.poll();

            if (current.equals(end.getId())) {
                // path found, reconstruct it
                while (current != null) {
                    path.add(current);
                    current = parentMap.get(current);
                }

                return path;
            }

            for (UUID neighbor : adjacentList.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }

        return path;
    }


    public void removePathFromAdjacentList(List<UUID> path, Map<UUID, List<UUID>> adjacentList, Graph graph) {
        if (path.isEmpty()) { return; }

        for (int i = 0; i < path.size() - 1; i++) {
            UUID current = path.get(i);
            UUID next = path.get(i + 1);

            // remove the edge from current to next in the adjacent list
            adjacentList.get(current).remove(next);

            // if the graph is undirected, also remove the edge from next to current
            if (graph.getDirectType() == GraphType.UNDIRECTED) {
                adjacentList.get(next).remove(current);
            }
        }
    }


    public boolean hasFourSimplePathsBetween(Vertex first, Vertex second, Graph graph) {
        // for simplicity call hasFourSimplePathsBetween for same vertex is true
        if (first.equals(second)) {
            return true;
        }

        // create adjacent list
        Map<UUID, List<UUID>> adjacentList = getAdjacentList(graph);
        int total_paths = 0;

        do {
            // try to find simple path
            List<UUID> path = getSimplePathBetween(first, second, adjacentList);

            if (path.isEmpty()) {
                return false;
            }

            removePathFromAdjacentList(path, adjacentList, graph);
            ++total_paths;
        } while(total_paths < 4);

        return true;
    }


    // TODO: should be in Graph class, but are we allowed to modify it?
    public Map<UUID, List<UUID>> getAdjacentList(Graph graph) {
        Map<UUID, List<UUID>> adjacencyList = new HashMap<>();

        // iterate through all edges in the graph
        for (Edge edge : graph.getEdges()) {
            UUID sourceVertexId = edge.getFromV();
            UUID targetVertexId = edge.getToV();

            // initialize the list for the source vertex if it doesn't exist and add the target vertex to the source vertex's list
            adjacencyList.putIfAbsent(sourceVertexId, new ArrayList<>());
            adjacencyList.get(sourceVertexId).add(targetVertexId);

            // if the graph is undirected, also add the source vertex to the target vertex's list
            if (graph.getDirectType() == GraphType.UNDIRECTED) {
                adjacencyList.putIfAbsent(targetVertexId, new ArrayList<>());
                adjacencyList.get(targetVertexId).add(sourceVertexId);
            }
        }

        return adjacencyList;
    }
}