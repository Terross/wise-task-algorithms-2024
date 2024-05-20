import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class IsOddCycle implements GraphProperty {
    private final ArrayList<Edge> chosenEdges = new ArrayList<>();
    private final HashMap<UUID, Integer> verticesColor = new HashMap<>();
    private final HashMap<UUID, ArrayList<UUID>> adjacencyList = new HashMap<>();

    private void getChosenEdges(Graph graph) {
        for (Edge edge : graph.getEdges()) {
            if (edge.getColor() == Color.red) {
                chosenEdges.add(edge);
            }
        }
    }

    private UUID getUndirectedConnectedVertex(UUID VertexUUID, Edge edge) {
        if (edge == null) {
            return null;
        }

        if (edge.getToV().equals(VertexUUID)) {
            return edge.getFromV();
        } else if (edge.getFromV().equals(VertexUUID)) {
            return edge.getToV();
        }

        return null;
    }

    private Edge getUndirectedIncidentEdge(UUID VertexUUID) {
        for (Edge edge : chosenEdges) {
            if (edge.getToV().equals(VertexUUID)) {
                return edge;
            } else if (edge.getFromV().equals(VertexUUID)) {
                return edge;
            }
        }

        return null;
    }

    private Edge getDirectedIncidentEdge(UUID VertexUUID) {
        for (Edge edge : chosenEdges) {
            if (edge.getFromV().equals(VertexUUID)) {
                return edge;
            }
        }

        return null;
    }

    private void getAdjacencyList(GraphType graphType, List<Edge> edges) {

        for(Edge edge : edges) {
            if(graphType == GraphType.UNDIRECTED) {
                ArrayList<UUID> l1 = adjacencyList.get(edge.getFromV());

                if(l1 == null) {
                    l1 = new ArrayList<>();
                    adjacencyList.put(edge.getFromV(), l1);
                }
                l1.add(edge.getToV());

                ArrayList<UUID> l2 = adjacencyList.get(edge.getToV());
                if(l2 == null) {
                    l2 = new ArrayList<>();
                    adjacencyList.put(edge.getToV(), l2);
                }
                l2.add(edge.getFromV());

            } else if(graphType == GraphType.DIRECTED) {
                ArrayList<UUID> l = adjacencyList.get(edge.getFromV());

                if(l == null) {
                    l = new ArrayList<>();
                    adjacencyList.put(edge.getFromV(), l);
                }
                l.add(edge.getToV());
            }
        }

    }

    private void dfs(UUID v, int color) {
        verticesColor.put(v, color);

        if(adjacencyList.containsKey(v)) {
            for(UUID neighbor : adjacencyList.get(v)) {
                if(verticesColor.get(neighbor) == 0) {
                    dfs(neighbor, color);
                }
            }
        }
    }

    private static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if(Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }

        return null;
    }

    private boolean checkIfContainsOnlyOneComponent(GraphType graphType, List<Edge> edges) {
        getAdjacencyList(graphType, edges);

        for(Edge edge : edges) {
            verticesColor.put(edge.getFromV(), 0);
            verticesColor.put(edge.getToV(), 0);
        }

        int color = 1;
        while(verticesColor.containsValue(0)) {
            dfs(getKeyByValue(verticesColor, 0), color);
            color++;
        }

        Set<Integer> colors = new HashSet<>(verticesColor.values());

        return colors.size() == 1;
    }

    @Override
    public boolean execute(Graph graph) {
        chosenEdges.clear();
        adjacencyList.clear();
        verticesColor.clear();

        getChosenEdges(graph);
        int parity = 0;

        if(chosenEdges.isEmpty()) {
            return false;
        }

        // check if graph contains only 1 component
        if(!checkIfContainsOnlyOneComponent(graph.getDirectType(), chosenEdges)) {
            return false;
        }

        if(graph.getDirectType() == GraphType.UNDIRECTED) {

            while(!chosenEdges.isEmpty()) {
                UUID start = chosenEdges.get(0).getFromV();
                Edge currentEdge = getUndirectedIncidentEdge(start);

                if (currentEdge == null) {
                    return false;
                }

                UUID current = getUndirectedConnectedVertex(start, currentEdge);
                chosenEdges.remove(currentEdge);
                parity++;

                do {
                    currentEdge = getUndirectedIncidentEdge(current);

                    if (currentEdge == null) {
                        return false;
                    }

                    current = getUndirectedConnectedVertex(current, currentEdge);

                    parity++;

                    chosenEdges.remove(currentEdge);
                } while (!start.equals(current));
            }

        } else if(graph.getDirectType() == GraphType.DIRECTED) {

            while(!chosenEdges.isEmpty()) {
                Edge currentEdge = chosenEdges.get(0);
                UUID start = currentEdge.getFromV();
                UUID current = currentEdge.getToV();

                chosenEdges.remove(currentEdge);
                parity++;

                do {
                    currentEdge = getDirectedIncidentEdge(current);

                    if(currentEdge == null) {
                        return false;
                    }

                    current = currentEdge.getToV();

                    parity++;

                    chosenEdges.remove(currentEdge);
                } while (!start.equals(current));
            }

        }

        return parity % 2 != 0;
    }
}