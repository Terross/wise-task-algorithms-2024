import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.util.HashSet;
import java.util.UUID;
import java.util.Set;

public class LargestCycle implements GraphCharacteristic {
    Integer max_length = 0;
    Set<UUID> used_vert = new HashSet<>();
    Set<Edge> used_edges = new HashSet<>();
    @Override
    public Integer execute(Graph graph) {

        if (graph.getDirectType() == GraphType.DIRECTED) {
            // Перебираем все вершины графа
            for (UUID vertexId : graph.getVertices().keySet()) {
                used_vert.clear();
                bnb_dir(graph, vertexId, vertexId);
            }
        }else{
            // Перебираем все вершины графа
            for (UUID vertexId : graph.getVertices().keySet()) {
                used_vert.clear();
                used_edges.clear();
                bnb_undir(graph, vertexId, vertexId);
            }
        }

        return max_length;
    }


    // Рекурсивная функция перебора
    public void bnb_undir(Graph graph, UUID startVertexId, UUID currentVertexId) {
        // Если мы вернулись в стартовую вершину и это не начало пути
        if (used_vert.size() > 1 && startVertexId.equals(currentVertexId)) {
            max_length = Math.max(used_vert.size(), max_length);
            return;
        }

        if (!used_vert.contains(currentVertexId)) {
            used_vert.add(currentVertexId);
            for (Edge edge : graph.getEdges()) {
                if (!used_edges.contains(edge)) {
                    if (edge.getFromV().equals(currentVertexId)) {
                        used_edges.add(edge);
                        UUID nextVertexId = edge.getToV();
                        used_vert.add(currentVertexId);
                        bnb_undir(graph, startVertexId, nextVertexId);
                        used_edges.remove(edge);
                    }
                    if (edge.getToV().equals(currentVertexId)) {
                        used_edges.add(edge);
                        UUID nextVertexId = edge.getFromV();
                        bnb_undir(graph, startVertexId, nextVertexId);
                        used_edges.remove(edge);
                    }
                }
            }
            used_vert.remove(currentVertexId);
        }
    }

    // Рекурсивная функция перебора
    public void bnb_dir(Graph graph, UUID startVertexId, UUID currentVertexId) {
        // Если мы вернулись в стартовую вершину и это не начало пути
        if (used_vert.size() > 1 && startVertexId.equals(currentVertexId)) {
            max_length = Math.max(used_vert.size(), max_length);
            return;
        }

        if (!used_vert.contains(currentVertexId)) {
            used_vert.add(currentVertexId);
            for (Edge edge : graph.getEdges()) {
                if (edge.getFromV().equals(currentVertexId)) {
                    UUID nextVertexId = edge.getToV();
                    bnb_dir(graph, startVertexId, nextVertexId);
                }
            }
            used_vert.remove(currentVertexId);
        }
    }


}

