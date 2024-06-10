import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.util.*;

public class NumberOfCliques implements GraphCharacteristic {

    @Override
    public Integer execute(Graph graph) {
        if (graph.getVertices() == null || graph.getEdges() == null || graph.getVertices().size() == 0) {
            throw new IllegalArgumentException("Граф не содержит вершин или ребер");
        }
        if (graph.getDirectType() == GraphType.DIRECTED){
            throw new IllegalArgumentException("Граф является ориентированным");
        }
        return findCliques(graph);
    }

    private int findCliques(Graph graph) {
        if (graph.getVertices().size() == 1)
            return 1;

        Set<List<UUID>> cliques = new HashSet<>();
        Map<UUID, List<UUID>> adjacencyList = buildAdjacencyList(graph.getEdges());
        List<List<Boolean>> adjacencyMatrix = buildAdjacencyMatrix(graph, adjacencyList);

        for (UUID vert : adjacencyList.keySet())
        {
            if (adjacencyList.get(vert).size() <= 2)
                continue;
            int max_clique = adjacencyList.get(vert).size(); // без петель n-1
            if (max_clique == graph.getVertexCount() - 1)
            {
                max_clique -= 1;
            }
            int system = adjacencyList.get(vert).size(); // Система счисления для перевода
            for (int max_current_clique = 2; max_current_clique <= max_clique; max_current_clique++)
            {
                for (int variants = (int)Math.pow(system, max_current_clique - 1); variants <= (int)Math.pow(system, max_current_clique); variants++)
                {
                    List<Integer> chouse_vert = ToNNotation(variants,system);
                    Set<Integer> check_retry = new HashSet<>();
                    for (int i = 0; i < chouse_vert.size(); i++)
                    {
                        check_retry.add(chouse_vert.get(i));
                    }
                    if (chouse_vert.size() == check_retry.size())
                    {
                        List<UUID> clique = new ArrayList<>();
                        for (int i = 0; i < chouse_vert.size(); i++)
                        {
                            clique.add(adjacencyList.get(vert).get(chouse_vert.get(i)));
                        }
                        clique.add(vert);
                        Collections.sort(clique);
                        if (isFUllGraph(adjacencyMatrix, clique, graph))
                            cliques.add(clique);
                    }
                }
            }
        }
        int dop = 0;
        if (((graph.getVertexCount() * (graph.getVertexCount() - 1)) / 2)  == graph.getEdgeCount()) {
            dop = 1;
        }
        return cliques.size() + graph.getVertexCount() + graph.getEdgeCount() + dop;
    }

    private List<Integer> ToNNotation(int x, int n){
        ArrayList<Integer> y = new ArrayList<>();
        while (x != 0)
        {
            y.add(x%n);
            x /= n;
        }
        Collections.reverse(y);
        return y;
    }

    private boolean isFUllGraph(List<List<Boolean>> adjacencyMatrix, List<UUID> clique, Graph graph){
        boolean check = true;
        List<UUID> veriteces = new ArrayList<>();
        for (UUID vertex : graph.getVertices().keySet())
        {
            veriteces.add(vertex);
        }

        for (UUID vertex1 : clique){
            for (UUID vertex2 : clique)
            {
                if (vertex1 != vertex2)
                    check = check && (adjacencyMatrix.get(veriteces.indexOf(vertex1)).get(veriteces.indexOf(vertex2)));
            }
        }
        return check;
    }


    private static Map<UUID, List<UUID>> buildAdjacencyList(List<Edge> edges) {
        Map<UUID, List<UUID>> adjacencyList = new HashMap<>();
        for (Edge edge : edges) {
            adjacencyList.computeIfAbsent(edge.getFromV(), k -> new ArrayList<>()).add(edge.getToV());
            adjacencyList.computeIfAbsent(edge.getToV(), k -> new ArrayList<>()).add(edge.getFromV());
        }
        return adjacencyList;
    }

    private static List<List<Boolean>> buildAdjacencyMatrix(Graph graph, Map<UUID, List<UUID>> adjacencyList) {
        List<List<Boolean>> adjacencyMatrix = new ArrayList<>();
        for (UUID i : graph.getVertices().keySet()) // проходимся по всем вершинам графа
        {
            List<Boolean> temp = new ArrayList<>();
            for (UUID j : graph.getVertices().keySet())
            {
                temp.add(adjacencyList.get(i).contains(j));
            }
            adjacencyMatrix.add(temp);
        }
        return adjacencyMatrix;
    }
}
