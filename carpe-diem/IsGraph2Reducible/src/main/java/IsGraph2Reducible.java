import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;
import java.util.stream.Collectors;

public class IsGraph2Reducible implements  GraphProperty {

    @Override
    public boolean execute(Graph graph) {
        for (UUID vertex: graph.getVertices().keySet()){
            if(!subGraph(vertex, graph, new HashSet<>())) return false;
        }
        return true;
    }

    private Set<UUID> getVertexMate (UUID vertex, Graph graph, Set<UUID> used){
        return graph.getEdges().stream()
                .filter(edge -> edge.getToV().equals(vertex) ||
                        edge.getFromV().equals(vertex))
                .map(edge -> {
                    if (edge.getToV().equals(vertex)) return edge.getFromV();
                    return edge.getToV();
                })
                .filter(v -> !used.contains(v))
                .collect(Collectors.toSet());
    }

    private boolean subGraph(UUID vertex, Graph graph, Set<UUID> used){
        used.add(vertex);
        int min_degree = 2;
        for(UUID ver: used){
            Set<UUID> subGraphVertex = getVertexMate(ver, graph, Set.of());
            int tmp_min = subGraphVertex.size();
            for(UUID v: subGraphVertex){
                if(!used.contains(v)) tmp_min -= 1;
            }
            if(tmp_min < min_degree) min_degree = tmp_min;
        }
        if(min_degree > 1) return false;

        for (UUID v: getVertexMate(vertex, graph, used)){
            if (!subGraph(v, graph, new HashSet<>(used))) return false;
        }
        return true;
    }
}