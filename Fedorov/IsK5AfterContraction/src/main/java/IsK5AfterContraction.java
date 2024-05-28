import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class IsK5AfterContraction implements GraphProperty {

    private HashMap<UUID, HashSet<UUID>> contractedGraph;

    private void AddEdge(UUID fromVertex, UUID toVertex){
        if(contractedGraph.containsKey(fromVertex)) {
            contractedGraph.get(fromVertex).add(toVertex);
        }
        else{
            contractedGraph.put(fromVertex, new HashSet<>());
            contractedGraph.get(fromVertex).add(toVertex);
        }
    }

    @Override
    public boolean execute(Graph graph) {
        if(graph.getVertexCount() < 5 || graph.getEdgeCount() < 10)
            return false;

        contractedGraph = new HashMap<>();
        HashSet<Edge> removedEdges = new HashSet<>();

        for(Edge edge: graph.getEdges()){
            var fromVertex = edge.getFromV();
            var toVertex = edge.getToV();

            if(edge.getColor().equals(Color.gray)){
                AddEdge(fromVertex, toVertex);
                AddEdge(toVertex, fromVertex);
            }
            else{
                removedEdges.add(edge);
            }
        }

        for(UUID vertex: graph.getVertices().keySet()){
            if(!contractedGraph.containsKey(vertex)) {
                contractedGraph.put(vertex, new HashSet<>());
            }
        }

        for(var edge: removedEdges){
            var fromVertex = edge.getFromV();
            var toVertex = edge.getToV();

            if(!fromVertex.equals(toVertex)) {
                if (!contractedGraph.get(fromVertex).contains(toVertex)) {
                    if(contractedGraph.containsKey(fromVertex) && contractedGraph.containsKey(toVertex)) {
                        contractedGraph.get(toVertex).addAll(contractedGraph.get(fromVertex));
                        contractedGraph.keySet().remove(fromVertex);
                    }
                    for (var vertex : contractedGraph.keySet()) {
                        if(contractedGraph.get(vertex).contains(fromVertex)) {
                            contractedGraph.get(vertex).remove(fromVertex);
                            contractedGraph.get(vertex).add(toVertex);
                        }
                    }
                    for (var tmp: removedEdges){
                        if(tmp.getFromV().equals(fromVertex)){
                            tmp.setFromV(toVertex);
                        }
                        if(tmp.getToV().equals(fromVertex)){
                            tmp.setToV(toVertex);
                        }
                    }
                }
            }
        }

        if(contractedGraph.size() == 5){
            for(var vertex: contractedGraph.keySet()){
                contractedGraph.get(vertex).remove(vertex);
                if(contractedGraph.get(vertex).size() != 4){
                    return false;
                }
            }
            return true;
        }
        return false;

    }
}