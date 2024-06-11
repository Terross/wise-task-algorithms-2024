import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;
import java.util.stream.Collectors;

//проверки графа на 3-редуцируемость, т.е. проверка, что минимальная степень подграфа <= 2.
public class IsGraph3Reducible implements  GraphProperty {
    //обходит все вершины графа и для каждой из них вызывает метод subgraphCheck, который проверяет связность вершин и их степени
    @Override
    public boolean execute(Graph graph) {
        for (UUID vertex: graph.getVertices().keySet()){
            if(!subgraphCheck(vertex, graph, new HashSet<>())) {
                return false;
            }
        }
        return true;
    }

    //проверяет связность вершин в графе. Для каждой вершины он находит смежные вершины, вычисляет их степени и проверяет условие 3-редуцируемости
    private boolean subgraphCheck(UUID vertex, Graph graph, Set<UUID> usedVertex){
        usedVertex.add(vertex);
        int min_degree = 3;
        for(UUID ver: usedVertex){
            Set<UUID> subGraphVertex = getVertexMate(ver, graph, Set.of());
            int tmp_min = subGraphVertex.size();
            for(UUID v: subGraphVertex){
                if(!usedVertex.contains(v)) tmp_min -= 1;
            }
            if(tmp_min < min_degree) min_degree = tmp_min;
        }
        //Если условие не выполняется возвращается false
        if(min_degree > 2){
            return false;
        }

        //рекурсия для остальных вершин
        for (UUID newVertex: getVertexMate(vertex, graph, usedVertex)){
            if (!subgraphCheck(newVertex, graph, new HashSet<>(usedVertex))) return false;
        }
        return true;
    }

    //возвращает множество вершин, смежных с данной вершиной vertex
    private Set<UUID> getVertexMate (UUID vertex, Graph graph, Set<UUID> usedVertex){
        return graph.getEdges().stream().filter(edge -> edge.getToV().equals(vertex) || edge.getFromV().equals(vertex)).map(edge -> {
            if (edge.getToV().equals(vertex)){
                return edge.getFromV();
            }
            return edge.getToV();
        }).filter(v -> !usedVertex.contains(v)).collect(Collectors.toSet());
    }
}
