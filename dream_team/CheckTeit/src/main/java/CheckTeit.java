import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckTeit implements GraphProperty {
    public Map<Vertex, List<Edge>> getVerticesEdges(Graph graph) {
        Map<Vertex, List<Edge>> res = new HashMap<>();
        for (Vertex vertex : graph.getVertices().values()) {
            res.put(vertex, new ArrayList<>());
            for (Edge edge : graph.getEdges()) {
                if (edge.getFromV().equals(vertex.getId()) || edge.getToV().equals(vertex.getId())) {
                    res.get(vertex).add(edge);
                }
            }
        }
        return res;
    }

    @Override
    public boolean execute(Graph graph) {
        Map<Vertex, List<Edge>> map = getVerticesEdges(graph);
        for (List<Edge> edges : map.values()) {
            for (int i = 0; i < edges.size() - 1; i++) {
                for (int j = i + 1; j < edges.size(); j++){
                    if (edges.get(i).getColor().equals(edges.get(j).getColor()))
                        return false;
                }
            }
        }
        return true;
    }
}
