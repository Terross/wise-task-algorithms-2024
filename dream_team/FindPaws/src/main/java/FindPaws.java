import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class FindPaws implements GraphProperty  {
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

    public List<UUID> getListEdges(Map<Vertex, List<Edge>> map,Vertex vertex) {
        List<UUID> local_list = new ArrayList<>();
        for (Edge edge : map.get(vertex)) {
            if (!local_list.contains(edge.getToV()) && !edge.getToV().equals(vertex.getId())) {
                local_list.add(edge.getToV());
            }
            if (!local_list.contains(edge.getFromV()) && !edge.getFromV().equals(vertex.getId())) {
                local_list.add(edge.getFromV());
            }
        }
        return local_list;
    }

    public boolean cmp(List<UUID> localList,List<UUID> InductionList,Vertex vertex){
        for(UUID idfro : localList) {
            for (UUID idto : InductionList) {
                if (idfro.equals(idto) && !idto.equals(vertex.getId()) ){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean check(List<UUID> corrects,List<UUID> reds,Graph graph){
        int lenthOfCorr = corrects.size();

        for (Vertex vertex : graph.getVertices().values()) {
            if (vertex.getColor().toString().equals("red")){
                reds.add(vertex.getId());
            }
        }
        int lenthOfReds=reds.size();
        if (lenthOfCorr == lenthOfReds){
            for(UUID idcorr : corrects){
                if(!reds.contains(idcorr)){
                    return false;
                }
            }
            for(UUID idred : reds){
                if(!corrects.contains(idred)){
                    return false;
                }
            }
        }
        else return false;
        return true;
    }

    @Override
    public boolean execute(Graph graph) {
        Map<Vertex, List<Edge>> map = getVerticesEdges(graph);
        List<UUID> corrects = new ArrayList<>();
        List<UUID> reds = new ArrayList<>();
        for (Vertex vertex : graph.getVertices().values()) {
            boolean flag=true;
            if (map.get(vertex).size() == 3) {
                List<UUID> localList = getListEdges(map, vertex);
                for (Edge edges : map.get(vertex)) {
                    for (Vertex vertexto : graph.getVertices().values()) {
                        if (vertexto.getId().equals(edges.getToV()) || vertexto.getId().equals(edges.getFromV())) {
                            if (!vertex.equals(vertexto)) {
                                if (map.get(vertexto).size() > 1) {
                                    List<UUID> InductionList = getListEdges(map, vertexto);
                                    if (!cmp(localList, InductionList, vertex)) {
                                        flag = false;
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                }
                if (!corrects.contains(vertex.getId()) && flag) {
                    corrects.add(vertex.getId());
                }
            }
        }
        return check(corrects, reds, graph);
    }
}

