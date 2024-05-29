import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class IsIsomorphicK5withoutSelectedEdges implements GraphProperty {
    final static int VERTEX_COUNT = 5;
    final static int MIN_EDGES_COUNT = (VERTEX_COUNT * (VERTEX_COUNT - 1));

    @Override
    public boolean execute(Graph graph) {
        if (graph.getVertexCount() < VERTEX_COUNT) {
            return false;
        }
        if (graph.getEdgeCount() < MIN_EDGES_COUNT) {
            return false;
        }
        Map<UUID, Set<UUID>> adjacency_list = new HashMap<>();
        Map<UUID, Set<UUID>> reversed_adjacency_list = new HashMap<>();

        Set<UUID> vertices_ids = graph.getVertices().keySet();
        for (var vertex_id: vertices_ids){
            adjacency_list.put(vertex_id, new HashSet<UUID>());
            reversed_adjacency_list.put(vertex_id, new HashSet<UUID>());
        }
        for (var edge: graph.getEdges()){
            if (edge.getColor() != Color.gray) {
                continue;
            }
            var start_id = edge.getFromV();
            var end_id = edge.getToV();
            adjacency_list.get(start_id).add(end_id);
            reversed_adjacency_list.get(end_id).add(start_id);
        }
        HashSet<UUID> updated_vertices_ids = new HashSet<>();
        for (UUID vertex_id: vertices_ids){
            var outgoing_vertices = adjacency_list.get(vertex_id);
            var incoming_vertices = reversed_adjacency_list.get(vertex_id);
            if (outgoing_vertices.size() > 1
                    || incoming_vertices.size() > 1) {
                if (updated_vertices_ids.size() >= VERTEX_COUNT){
                    return false;
                }
                updated_vertices_ids.add(vertex_id);
                continue;
            }
            if (outgoing_vertices.isEmpty()
                    || incoming_vertices.isEmpty()) {
                return false;
            }
            for (UUID from_id: incoming_vertices) {
                for (UUID to_id : outgoing_vertices) {
                    if (adjacency_list.get(from_id).contains(to_id)
                        || reversed_adjacency_list.get(to_id).contains(from_id)) {
                        return false;
                    }
                    adjacency_list.get(from_id).remove(vertex_id);
                    adjacency_list.get(from_id).add(to_id);
                    reversed_adjacency_list.get(to_id).remove(vertex_id);
                    reversed_adjacency_list.get(to_id).add(from_id);
                }
            }
        }

        if (updated_vertices_ids.size() < VERTEX_COUNT){
            return false;
        }

        for (UUID vertex_id: updated_vertices_ids) {
            var outgoing_vertices = adjacency_list.get(vertex_id);
            var incoming_vertices = reversed_adjacency_list.get(vertex_id);
            if (incoming_vertices.size() != VERTEX_COUNT - 1
                    || outgoing_vertices.size() != VERTEX_COUNT - 1
            ) {
                return false;
            }
        }
        return true;
    }
}
