import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import com.mathsystem.api.graph.model.Edge;

import java.util.*;

public class FindCube implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        HashMap <UUID, Integer> degrees = getDegrees(graph);
        HashMap<UUID, List<UUID>> adjacencyList = getAdjacencyList(graph);
        Set<UUID> visited = new HashSet<>();
        List<Set<UUID>> comps =  findConectComponents(adjacencyList,visited);
        Set<Set<UUID>> compSet = new HashSet<>(comps);
        List<Set<UUID>> cubes = new ArrayList<>();
        Set<UUID> combined = new HashSet<>();
        Set<Set<UUID>> combinations = new HashSet<>();
        for (Set<UUID> comp : compSet) {
            generateCombinations(comp, index, combined, combinations);
        };
        int count = 0;
        for(Set<UUID> comb : compSet){
            count = 0;
            for (UUID uuid : comb){
                if(degrees.get(uuid).equals(3)){
                    count++;
                }
            }
            if(count == comb.size() && count >=2){
                cubes.add(comb);
            }
        }
        if(cubes.isEmpty()){
            return false;
        }else{
        return true;}
    }
    Set<Set<UUID>> pathSet = new HashSet<>();

    public HashMap<UUID, List<UUID>> getAdjacencyList(Graph graph) {
        HashMap<UUID, List<UUID>> adjacencyList = new HashMap<>();
        for(UUID vertex: graph.getVertices().keySet()){
            adjacencyList.put(vertex, new ArrayList<>());
        }

        for (Edge edge : graph.getEdges()) {
            UUID key = edge.getFromV();
            UUID value = edge.getToV();
            adjacencyList.get(key).add(value);
        }

        return adjacencyList;
    }
    public HashMap <UUID, Integer> getDegrees(Graph graph){
        HashMap <UUID, Integer> degrees = new HashMap<UUID, Integer>();
        for (UUID vertex_id : graph.getVertices().keySet()) {
            degrees.put(vertex_id, 0);
        }
        for (Edge edge : graph.getEdges()){
            UUID from = edge.getFromV();
            UUID to = edge.getToV();
            Integer tmp_from = degrees.get(from) +1;
            degrees.put(from, tmp_from);
            if(to != null) {
                Integer tmp_to = degrees.get(to) +1;
                degrees.put(to, tmp_to);

            }
        }
        return degrees;
    }

    private  Set<UUID> dfs(HashMap<UUID,List<UUID>> adjacencyList, UUID vertex, Set<UUID> visited){
        visited.add(vertex);
        for(UUID tmp:  adjacencyList.get(vertex)){
            if(!visited.contains(tmp)){
                dfs(adjacencyList, tmp, visited);
            }
        }
        return visited;
    }

    private List<Set<UUID>> findConectComponents(HashMap<UUID,List<UUID>> adjacencyList,Set<UUID> visited ){
        List<Set<UUID>> components = new ArrayList<>();
        Set <UUID> path = new HashSet<>();
        for(UUID vertex: adjacencyList.keySet()){
            if(!visited.contains(vertex)){
                Set<UUID> tmp_visited = new HashSet<>();
                tmp_visited.add(vertex);
                if(!components.contains(tmp_visited)){
                components.add(tmp_visited);}
                dfs(adjacencyList, vertex, tmp_visited);
            }
        }
        return components;
    }
    int index = 0;
    public void generateCombinations(Set<UUID> component, int index ,Set<UUID> combination, Set<Set<UUID>> combinations){
        combinations.add(new HashSet<>(combination)); // Добавляем текущую комбинацию в выходной набор

        for (UUID vertex : component) {
            if (!combination.contains(vertex)) {
                combination.add(vertex); // Добавляем элемент в текущую комбинацию
                generateCombinations(component, index + 1, combination, combinations); // Рекурсивный вызов
                combination.remove(vertex); // Удаляем последний элемент перед следующим вызовом
            }
        }
    }
}

