import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class Chromatic3 implements GraphProperty {
    public Map<UUID, List<UUID>> vertexListfunc(Graph graph){
        Map<UUID, List<UUID>> vertexMap = new TreeMap<>();
        for(var vertex : graph.getVertices().keySet()){
            vertexMap.put(vertex, new ArrayList<>());
        }
        for(Edge edge: graph.getEdges()){
            UUID from = edge.getFromV();
            UUID to = edge.getToV();
            vertexMap.get(from).add(to);
            vertexMap.get(to).add(from);
        }

        return vertexMap;
    }

    public void weightZero(Graph graph){
        for(var vertex : graph.getVertices().keySet()){
            graph.getVertices().get(vertex).setWeight(0);
        }
    }

    @Override
    public boolean execute(Graph graph) {
        weightZero(graph);
        Stack<UUID> stack = new Stack<>();
        ArrayList<UUID> array = new ArrayList<>();
        Set<Integer> weightArray = new HashSet<>();
        Integer max_weight = 1;

        Integer i = 0;
        stack.push(vertexListfunc(graph).keySet().iterator().next());

        while(!(stack.isEmpty())){
            UUID vertUUID = stack.pop();
            array.add(vertUUID);
            graph.getVertices().get(vertUUID).setWeight(1);
            graph.getVertices().get(vertUUID).setLabel(i.toString());
            weightArray.clear();
            weightArray.add(0);
            for(UUID vertex: vertexListfunc(graph).get(vertUUID)){
                Integer weight = graph.getVertices().get(vertex).getWeight();
                weightArray.add(weight);
            }

            for(int x = 0; x <= weightArray.size(); x++){
                if((!weightArray.contains(x))){
                    graph.getVertices().get(vertUUID).setWeight(x);
                    if(x > max_weight){max_weight = x;}
                    break;
                }
            }

            for(UUID vertex: vertexListfunc(graph).get(array.get(array.size()-1))){
                if(!(array.contains(vertex)) & !(stack.contains(vertex))){
                    stack.push(vertex);
                }
            }
        }
        return (max_weight == 3);
    }
}
