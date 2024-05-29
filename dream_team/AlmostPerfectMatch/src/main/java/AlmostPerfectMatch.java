import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class AlmostPerfectMatch implements GraphProperty {

    private static boolean depthFirstSearch(UUID v, HashMap<UUID, List<UUID>> adjList, Set<UUID> visited, Map<UUID, UUID> matching) {
        if(visited.contains(v)){
            return false;
        }
        visited.add(v);
        for(UUID neighbor : adjList.get(v)){
            if(matching.get(neighbor) == null){
                matching.put(neighbor, v);
                return true;
            } else {
                if(depthFirstSearch(matching.get(neighbor), adjList, visited, matching)){
                    matching.put(neighbor, v);
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isBipartite(Graph graph) {
        Map<UUID, Vertex> vertices = graph.getVertices();
        Queue<UUID> queue = new LinkedList<>();
        Set<UUID> visited = new HashSet<>();
        List<Edge> edges = graph.getEdges();
        Map<UUID, Integer> colors = new HashMap<>();

        HashMap<UUID, List<UUID>> adjList = new HashMap<>();
        for (Vertex vertex : vertices.values()) {
            adjList.put(vertex.getId(), new ArrayList<>());
        }

        for (Edge edge : edges) {
            adjList.get(edge.getFromV()).add(edge.getToV());
            adjList.get(edge.getToV()).add(edge.getFromV());
        }

        for(UUID vertexId : vertices.keySet()){
            if(queue.isEmpty()){
                if(visited.contains(vertexId)){
                    continue;
                } else {
                    colors.put(vertexId, 1);
                    queue.add(vertexId);
                }
            }

            while(!queue.isEmpty()){
                UUID current = queue.poll();
                visited.add(current);

                for(UUID neighbor : adjList.get(current)){
                    if(colors.containsKey(neighbor)){
                        if(visited.contains(neighbor)){
                            continue;
                        } else if(colors.get(neighbor) == colors.get(current)){
                            return false;
                        }
                    } else {
                        queue.add(neighbor);
                        colors.put(neighbor, 1 - colors.get(current));
                    }
                }
            }
        }
        return true;
    }

    private static int findMaxMatchingSize(Graph graph) {
        Map<UUID, UUID> matching = new HashMap<>();
        Map<UUID, Vertex> vertices = graph.getVertices();
        List<Edge> edges = graph.getEdges();

        HashMap<UUID, List<UUID>> adjList = new HashMap<>();
        for (Vertex vertex : vertices.values()) {
            adjList.put(vertex.getId(), new ArrayList<>());
        }

        for (Edge edge : edges) {
            adjList.get(edge.getFromV()).add(edge.getToV());
            adjList.get(edge.getToV()).add(edge.getFromV());
        }

        for(UUID vertexId : vertices.keySet()){
            matching.put(vertexId, null);
        }
        boolean flag = false;
        int answer = 0;
        for(UUID vertexId : vertices.keySet()){
            Set<UUID> visited = new HashSet<>();
            flag = depthFirstSearch(vertexId, adjList, visited, matching);
            if(flag) answer++;
        }

        return answer;
    }

    @Override
    public boolean execute(Graph graph) {
        int maxMatchingSize = findMaxMatchingSize(graph);
        int numVertices = graph.getVertices().size();

        if (!isBipartite(graph)){
            return false;
        }

        if (numVertices % 2 == 0) {
            return false;
        } else {
            return maxMatchingSize == numVertices - 1;
        }
    }
}
