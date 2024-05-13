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
        degrees_init(graph);
        dfs(graph, graph.getEdges().get(0).getFromV());

        List<Integer> degrees_list = new ArrayList<Integer>(degrees.values());

        for (int i = 0; i < degrees_list.size(); i++) {
            for (int j = i + 1; j < degrees_list.size(); j++) {
                if (degrees_list.get(i) + degrees_list.get(j) < graph.getVertexCount()) {
                    return false;
                }
            }
        }
        return true;
    }

    private HashMap<UUID, Character> visited = new HashMap<UUID, Character>();
    private HashMap <UUID, Integer> degrees = new HashMap<UUID, Integer>();

    private void degrees_init(Graph graph) {
        for (UUID vertex_id: graph.getVertices().keySet()) {
            degrees.put(vertex_id, 0);
        }
    }

    private void dfs(Graph graph , UUID start){
        for(Edge edge: graph.getEdges()){
            UUID from = edge.getFromV();
            UUID to = edge.getToV();
            visited.put(from, 'T');
            visited.put(to,'T');
            degrees.put(from, degrees.get(from) + 1);
            if(to != null) {
                degrees.put(to, degrees.get(to) + 1);
                if (!visited.containsKey(to)) {
                    dfs(graph, to);
                }
            }
        }
    }
}

