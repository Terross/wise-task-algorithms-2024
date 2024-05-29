package ru.leti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

public class TransitiveReductions implements GraphCharacteristic {
    private Optional<Edge> findEdge(final List<Edge> edges, final UUID src, final UUID dst) {
        for (int i = 0; i < edges.size(); ++i) {
            final Edge edge = edges.get(i);

            final UUID from = edge.getFromV();
            final UUID to = edge.getToV();

            if (dst.equals(to) && src.equals(from) || dst.equals(from) && src.equals(to)) {
                return Optional.of(edge);
            }
        }

        return Optional.empty();
    }

    private Integer countTransitiveReductions(final Graph graph, final int startIndex) {
        final int edgeCount = graph.getEdgeCount();
        final int vertexCount = graph.getVertexCount();

        if (vertexCount == 0 || edgeCount + 1 <= vertexCount) {
            return 1;
        }

        final HashMap<UUID, Vertex> vertices = new HashMap<>(graph.getVertices());
        final List<Edge> edges = graph.getEdges();

        int reductions = 0;

        for (int i = startIndex; i < edgeCount; ++i) {
            Edge edge = edges.get(i);

            final UUID a = edge.getFromV();
            final UUID b = edge.getToV();

            for (final UUID c : vertices.keySet()) {
                if (findEdge(edges, a, c).isEmpty() || findEdge(edges, c, b).isEmpty()) {
                    continue;
                }

                List<Edge> subgraphEdges = new ArrayList<>(edges);
                subgraphEdges.remove(i);

                reductions += countTransitiveReductions(
                        new Graph(graph.getDirectType(),
                                vertexCount,
                                edgeCount - 1,
                                vertices,
                                subgraphEdges),
                        i);

                break;
            }
        }

        return reductions == 0 ? 1 : reductions;
    }

    @Override
    public Integer execute(final Graph graph) {
        List<Edge> subgraphEdges = new ArrayList<>();
        for (final Edge edge : graph.getEdges()) {
            if (edge.getFromV().equals(edge.getToV())) {
                continue;
            }

            subgraphEdges.add(edge);
        }

        return countTransitiveReductions(
                new Graph(graph.getDirectType(),
                        graph.getVertexCount(),
                        subgraphEdges.size(),
                        graph.getVertices(),
                        subgraphEdges),
                0);
    }
}
