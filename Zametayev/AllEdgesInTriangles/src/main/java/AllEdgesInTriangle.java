import com.mathsystem.api.graph.model.*;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class AllEdgesInTriangle implements GraphProperty {
    public boolean execute(Graph graph) {
        if(graph.getEdgeCount() == 0){
            return false;
        }
        // Получение списка смежности
        Map<UUID, List<UUID>> adjacencyList = new HashMap<>();
        for (Vertex vertex : graph.getVertices().values()) {
            adjacencyList.put(vertex.getId(), new ArrayList<>());
        }

        //Заполняем список смежности
        for (Edge edge : graph.getEdges()) {
            //Нам не важен вид графа, так как нас интересует только наличие ребра между вершинами
            adjacencyList.get(edge.getFromV()).add(edge.getToV());
            adjacencyList.get(edge.getToV()).add(edge.getFromV());
        }

        for(UUID v : adjacencyList.keySet()){
            if(adjacencyList.get(v).size() < 2){
                return false; //В графе встретилась вершина степени 1, значит инцидентное ей ребро в треугольник входить не может
            }
        }

        List<Edge> visitedEdges = new ArrayList<>(); //список посещенных ребер
        for(Edge edge : graph.getEdges()){
            UUID u = edge.getFromV();
            UUID v = edge.getToV();
            Edge currEdge = new Edge(u, v, null, null, null);
            if(!visitedEdges.contains(currEdge)) { // Если мы еще не посещали это ребро, нужно добавить его в список посещенных
                visitedEdges.add(currEdge);
            }
            else { // Если это ребро встречалось нам до этого, то есть, оно уже в треугольнике, тогда можем переходить к проверке следующего ребра
                continue;
            }
            boolean isInTriangle = false;
            for (UUID neighbor : adjacencyList.get(u)) {
                if (adjacencyList.get(v).contains(neighbor)) {
                    isInTriangle = true;
                    Edge u_to_n = new Edge(u, neighbor, null, null, null);
                    Edge v_to_n = new Edge(v, neighbor, null, null, null);
                    //Полученные ребра уже находятся в треугольнике
                    visitedEdges.add(u_to_n);
                    visitedEdges.add(v_to_n);
                    break;
                }
            }
            if (!isInTriangle) {
                return false;
            }
        }

        return true;
    }

}

