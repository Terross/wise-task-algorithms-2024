import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

public class MinNodeDegree implements GraphCharacteristic {
    @Override
    public Integer execute(Graph graph) {
        // check if graph has no vertices
        if (graph.getVertexCount() == 0)
            return 0;
        // initialize hashmap
        var vertex_to_degree = new HashMap<UUID, Integer>(); // degree of each vertex
        for (Vertex vertex : graph.getVertices().values())
            vertex_to_degree.put(vertex.getId(), 0);

        // compute degrees
        for (Edge edge : graph.getEdges()) {
            vertex_to_degree.compute(edge.getFromV(), (key, val) -> val + 1);
            vertex_to_degree.compute(edge.getToV(), (key, val) -> val + 1);
        }

        return Collections.min(vertex_to_degree.values());
    }
}
