import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import com.mathsystem.api.graph.model.Edge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SumDegIsMoreOrEqVertexCount implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        get_degrees(graph);

        int min_degree1 = Integer.MAX_VALUE;
        int min_degree2 = Integer.MAX_VALUE;

        List<Integer> degrees_list = new ArrayList<Integer>(degrees.values());

        for (int i = 0; i < degrees_list.size(); i++) {
            if (min_degree1 > degrees_list.get(i)) {
                min_degree1 = degrees_list.get(i);
            }
            else if(min_degree2 > degrees_list.get(i)) {
                min_degree2 = degrees_list.get(i);
            }
        }

        return min_degree1 + min_degree2 >= graph.getVertexCount();
    }

    private HashMap <UUID, Integer> degrees = new HashMap<UUID, Integer>();

    private void get_degrees(Graph graph) {
        for (UUID vertex_id: graph.getVertices().keySet()) {
            degrees.put(vertex_id, 0);
        }

        for(Edge edge: graph.getEdges()){
            UUID from = edge.getFromV();
            UUID to = edge.getToV();
            degrees.put(from, degrees.get(from) + 1);
            degrees.put(to, degrees.get(to) + 1);
        }
    }
}

