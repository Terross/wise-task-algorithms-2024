import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class CheckThreePaths implements GraphProperty {
    private final ArrayList<UUID> bestPath = new ArrayList<>();
    private final ArrayList<UUID> vertices = new ArrayList<>();
    private final HashMap<UUID, ArrayList<UUID>> adjacencyList = new HashMap<>();

    private void getAdjacencyList(GraphType graphType, List<Edge> edges) {
        for(UUID vertex : vertices) {
            adjacencyList.put(vertex, new ArrayList<>());
        }

        for(Edge edge : edges) {
            if(graphType == GraphType.UNDIRECTED) {

                ArrayList<UUID> l1 = adjacencyList.get(edge.getFromV());
                l1.add(edge.getToV());

                ArrayList<UUID> l2 = adjacencyList.get(edge.getToV());
                l2.add(edge.getFromV());

            } else if(graphType == GraphType.DIRECTED) {

                ArrayList<UUID> l = adjacencyList.get(edge.getFromV());
                l.add(edge.getToV());

            }
        }

    }

    private HashMap<UUID, ArrayList<UUID>> deepCopyHashMap(HashMap<UUID, ArrayList<UUID>> hashMap) {
        if(hashMap == null) {
            return null;
        }

        HashMap<UUID, ArrayList<UUID>> newHashMap = new HashMap<>();
        for(Map.Entry<UUID, ArrayList<UUID>> entry : hashMap.entrySet()) {
            newHashMap.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }

        return newHashMap;
    }

    private boolean bfs(UUID start, UUID goal, HashMap<UUID, ArrayList<UUID>> adjList) {
        bestPath.clear();

        Queue<UUID> q = new LinkedList<>();
        HashMap<UUID, UUID> path = new HashMap<>();
        HashMap<UUID, Boolean> visited = new HashMap<>();

        visited.put(start, true);
        q.add(start);

        while(!q.isEmpty() && !visited.getOrDefault(goal, false)) {
            UUID currentVert = q.poll();

            for(UUID neighbor : adjList.getOrDefault(currentVert, null)) {
                if(!visited.getOrDefault(neighbor, false)) {
                    visited.put(neighbor, true);
                    path.put(neighbor, currentVert);
                    q.add(neighbor);
                }
            }
        }

        if(visited.getOrDefault(goal, false)) {
            UUID tmp = goal;
            while(!tmp.equals(start)) {
                bestPath.add(0, path.get(tmp));
                tmp = path.get(tmp);
            }
            bestPath.add(goal);

            return true;
        }

        return false;
    }

    private void deletePath(HashMap<UUID, ArrayList<UUID>> adjList, GraphType graphType) {
        for(int i = 1; i < bestPath.size(); i++) {
            adjList.get(bestPath.get(i - 1)).remove(bestPath.get(i));

            if(graphType == GraphType.UNDIRECTED) {
                adjList.get(bestPath.get(i)).remove(bestPath.get(i - 1));
            }

        }
    }

    private boolean findPaths(UUID start, UUID goal, int count, GraphType graphType) {
        HashMap<UUID, ArrayList<UUID>> newAdjList = deepCopyHashMap(adjacencyList);


        for(int i = 0; i < count; i++) {
            bestPath.clear();

            if(!bfs(start, goal, newAdjList)) {
                return false;
            }
            deletePath(newAdjList, graphType);
        }

        return true;
    }

    @Override
    public boolean execute(Graph graph) {
        vertices.clear();
        bestPath.clear();
        adjacencyList.clear();
        vertices.addAll(graph.getVertices().keySet());

        getAdjacencyList(graph.getDirectType(), graph.getEdges());

        for(int i = 0; i < vertices.size() - 1; i++) {
            for(int j = 0; j < vertices.size(); j++) {
                if(i == j) continue;

                int PATH_COUNT = 3;
                if(!findPaths(vertices.get(i), vertices.get(j), PATH_COUNT, graph.getDirectType())) {
                    return false;
                }
            }
        }

        return true;
    }
}