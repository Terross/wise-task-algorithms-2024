import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class MinimalBarrier implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {

        if(!isBipartite(graph)){
            //System.out.println("Граф не двудольный");
            return false;
        }


        Set<UUID> in_barrier = new HashSet<>();
        Set<UUID> not_in_barrier = new HashSet<>();
        for(UUID vertex : graph.getVertices().keySet()){
            if(graph.getVertices().get(vertex).getColor() == Color.red){
                in_barrier.add(vertex);
            }else{
                not_in_barrier.add(vertex);
            }
        }



        Map<UUID, Vertex> vertices = graph.getVertices();
        List<Edge> edges = graph.getEdges();

        HashMap<UUID, List<UUID>> adjList = new HashMap<>();
        for (Vertex vertex : vertices.values()) {
            adjList.put(vertex.getId(), new ArrayList<>());
        }

        for (Edge edge : edges) {
            adjList.get(edge.getFromV()).add(edge.getToV());
            adjList.get(edge.getToV()).add(edge.getFromV());
        }
        int max_match = findMaxMatchingSize(graph);
        int deficit = graph.getVertexCount() - max_match;
        //System.out.printf("Подсчёт начального дефицита. Дефицит = %d, Мэтч = %d, Кол-во вершин =  %d\n", deficit, max_match, graph.getVertexCount());
        //проверяем, евляется ли множество барьером вообще odd(G−S)−|S|=def(G)
        int odd_of_original = odd(not_in_barrier, adjList);
        //System.out.printf("Количество нечётных компонетн без отмеченых красных = %d, Размер предполагаемого барьера = %d\n", odd_of_original, in_barrier.size());
        if(odd_of_original - in_barrier.size() != deficit){
            //System.out.println("Это не барьер");
            return false;
        }

        if(in_barrier.isEmpty()){
            //System.out.println("Минимальный барьер - пустое множество.");
            return true;
        }


        //когда убираем одну вершину из текущего барьера, новый odd тоже должен уменьшится на 1


        for(UUID vertex : in_barrier){
            Set<UUID> tmp_not_in = new HashSet<>();
            tmp_not_in.addAll(not_in_barrier);
            tmp_not_in.add(vertex);
            int new_odd = odd(tmp_not_in, adjList);
            if(new_odd == odd_of_original - 1){

                return false;
            }
        }


        //System.out.println(odd(graph.getVertices().keySet(), adjList));

        return true;
    }
    private static boolean isBipartite(Graph graph) {
        Map<UUID, Vertex> vertices = graph.getVertices();
        Queue<UUID> queue = new LinkedList<>();
        Set<UUID> visited = new HashSet<>();
        List<Edge> edges = graph.getEdges();
        Map<UUID, Integer> colors = new HashMap<>();

        HashMap<UUID, List<UUID>> adjList = new HashMap<>();
        for (Vertex vertex : vertices.values()) {
            adjList.put(vertex.getId(), new ArrayList<>());
        }

        for (Edge edge : edges) {
            adjList.get(edge.getFromV()).add(edge.getToV());
            adjList.get(edge.getToV()).add(edge.getFromV());
        }



        for(UUID VertexId : vertices.keySet()){
            if(queue.isEmpty()){ //очередь всегда пустая на этом шаге, но автор параноик
                if(visited.contains(VertexId)){
                    continue; //если вектор уже был проверен - идём дальше
                }else{
                    colors.put(VertexId, 1); //иначе начинаем новый пересчёт (это отдельная компонента связности)

                    queue.add(VertexId);
                }}

            while(!queue.isEmpty()){

                UUID current = queue.poll();
                visited.add(current);

                for(UUID neigh : adjList.get(current)){
                    if(colors.containsKey(neigh)){
                        if(visited.contains(neigh)){
                            continue;
                        }else if(colors.get(neigh) == colors.get(current)){
                            return false;
                        }
                    }else{
                        queue.add(neigh);
                        colors.put(neigh, 1 - colors.get(current));
                    }
                }

            }
        }
        return true;
    }

    private static int odd(Set<UUID> vertices,  HashMap<UUID, List<UUID>> adjList){
        Queue<UUID> queue = new LinkedList<>();
        Set<UUID> visited = new HashSet<>(); //удаляем уже просмотренные
        Set<UUID> seen = new HashSet<>(); //чтобы соседи не пересекались
        int answer = 0;
        for(UUID VertexId : vertices){
            if(visited.contains(VertexId)){
                continue; //если вектор уже был проверен - идём дальше
            }
            queue.add(VertexId);
            int tmp = 0;
            while(!queue.isEmpty()){
                tmp++;
                UUID current = queue.poll();
                visited.add(current);

                for(UUID neigh : adjList.get(current)){
                    if(!vertices.contains(neigh)) continue; //чтобы проверять соседей только в множестве vertices
                    if(seen.contains(neigh) || visited.contains(neigh)){
                        continue;
                    }
                    seen.add(neigh);
                    queue.add(neigh);

                }

            }
            if(tmp%2 == 1)answer++;
        }

        return answer;
    }


    private static int findMaxMatchingSize(Graph graph) {
        Map<UUID, UUID> matching = new HashMap<>();
        Map<UUID, Vertex> vertices = graph.getVertices();
        List<Edge> edges = graph.getEdges();

        HashMap<UUID, List<UUID>> adjList = new HashMap<>();
        for (Vertex vertex : vertices.values()) {
            adjList.put(vertex.getId(), new ArrayList<>());
        }

        for (Edge edge : edges) {
            adjList.get(edge.getFromV()).add(edge.getToV());
            adjList.get(edge.getToV()).add(edge.getFromV());
        }

        for(UUID vertex : vertices.keySet()){
            matching.put(vertex, null);
        }
        boolean flag = false;
        int answer = 0;
        for(UUID vertex : vertices.keySet()){
            Set<UUID> visited = new HashSet<>();
            flag = dfs(vertex, adjList, visited, matching);
            if(flag) answer++;
        }

        return answer;
    }

    private static boolean dfs(UUID v, HashMap<UUID, List<UUID>> adjList, Set<UUID> visited, Map<UUID, UUID> matching) {
        if(visited.contains(v)){
            return false;
        }
        visited.add(v);
        for(UUID neigh : adjList.get(v)){
            if(matching.get(neigh) == null){
                matching.put(neigh, v);
                return true;
            }else{
                if(dfs(matching.get(neigh), adjList, visited, matching)){
                    matching.put(neigh, v);
                    return true;
                }
            }
        }
        return false;
    }

}
