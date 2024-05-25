import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.util.*;

public class BlockCount implements GraphCharacteristic {

    private int count = 0;
    private int time = 0;

    @Override
    public Integer execute(Graph graph) {
        if (graph.getEdgeCount() == 0) {
            return 0;
        }
        return countBiconnectedComponents(graph);
    }

    private int countBiconnectedComponents(Graph graph) {
        Map<UUID, Integer> disc = new HashMap<>();
        Map<UUID, Integer> low = new HashMap<>();
        Map<UUID, UUID> parent = new HashMap<>();
        LinkedList<Edge> st = new LinkedList<>();

        for (Vertex vertex : graph.getVertices().values()) {
            disc.put(vertex.getId(), -1);
            low.put(vertex.getId(), -1);
            parent.put(vertex.getId(), null);
        }

        for (Vertex vertex : graph.getVertices().values()) {
            if (disc.get(vertex.getId()) == -1)
                BCCUtil(graph, vertex, disc, low, st, parent);

            int j = 0;

            while (!st.isEmpty()) {
                j = 1;
                Edge edge = st.removeLast();
            }
            if (j == 1) {
                count++;
            }
        }

        return count;
    }

    private void BCCUtil(Graph graph, Vertex u, Map<UUID, Integer> disc, Map<UUID, Integer> low,
                         LinkedList<Edge> st, Map<UUID, UUID> parent) {

        disc.put(u.getId(), ++time);
        low.put(u.getId(), time);
        int children = 0;

        for (Edge edge : graph.getEdges()) {
            if (edge.getFromV().equals(u.getId()) || edge.getToV().equals(u.getId())) {
                UUID vId = edge.getFromV().equals(u.getId()) ? edge.getToV() : edge.getFromV();
                Vertex v = graph.getVertices().get(vId);

                if (disc.get(vId) == -1) {
                    children++;
                    parent.put(vId, u.getId());

                    st.addLast(edge);
                    BCCUtil(graph, v, disc, low, st, parent);

                    if (low.get(u.getId()) > low.get(vId))
                        low.put(u.getId(), low.get(vId));

                    if ((disc.get(u.getId()) == 1 && children > 1) || (disc.get(u.getId()) > 1 && low.get(vId) >= disc.get(u.getId()))) {
                        while (!st.isEmpty() && (st.getLast().getFromV().equals(u.getId()) || st.getLast().getToV().equals(vId))) {
                            Edge e = st.removeLast();
                        }
                        if (!st.isEmpty()) {
                            st.removeLast();
                        }
                        count++;
                    }
                } else if (!vId.equals(parent.get(u.getId())) && disc.get(vId) < disc.get(u.getId())) {
                    if (low.get(u.getId()) > disc.get(vId))
                        low.put(u.getId(), disc.get(vId));

                    st.addLast(edge);
                }
            }
        }
    }
}