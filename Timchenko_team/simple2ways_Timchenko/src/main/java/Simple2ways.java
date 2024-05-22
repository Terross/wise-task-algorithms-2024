import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Simple2ways implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        //System.out.println(graph.getVertices());
        var edges = graph.getEdges();
        //System.out.println(edges);
        Map<String, Integer> ind_vertex = new HashMap<String, Integer>();
        int j = 1;
        for (int i = 0; i < edges.size(); i++) {
            if (!ind_vertex.containsKey(edges.get(i).getFromV().toString())) {
                ind_vertex.put(edges.get(i).getFromV().toString(), j);
                j += 1;

            }
            if (!ind_vertex.containsKey(edges.get(i).getToV().toString())) {
                ind_vertex.put(edges.get(i).getToV().toString(), j);
                j += 1;
            }
        }

        Graphs gr = new Graphs(graph.getVertexCount());
        for (int i = 0; i < edges.size(); i++) {
            gr.addEdge(ind_vertex.get(edges.get(i).getFromV().toString()), ind_vertex.get(edges.get(i).getToV().toString()));
            gr.addEdge(ind_vertex.get(edges.get(i).getToV().toString()), ind_vertex.get(edges.get(i).getFromV().toString()));
        }
        for (int i = 1; i <= graph.getVertexCount(); i++) {
            for (int v = graph.getVertexCount(); v >= i; v--) {
                if (i != v) {
                    List<List<Integer>> paths = gr.findPaths(i, v);


                    if (paths.isEmpty()) {
                        return false;
                    } else {
                        List<List<Integer>> match = findMatchingArrays(paths);
                        if(match.isEmpty()){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    class Node {
        int value;
        List<Node> neighbors;

        public Node(int value) {
            this.value = value;
            this.neighbors = new ArrayList<>();
        }

        public void addNeighbor(Node neighbor) {
            this.neighbors.add(neighbor);
        }
    }

    class Graphs {
        List<Node> nodes;

        public Graphs(int numberOfNodes) {
            this.nodes = new ArrayList<>(numberOfNodes);
            for (int i = 0; i < numberOfNodes; i++) {
                this.nodes.add(new Node(i + 1));
            }
        }

        public void addEdge(int startNode, int endNode) {
            this.nodes.get(startNode - 1).addNeighbor(this.nodes.get(endNode - 1));
        }

        public List<List<Integer>> findPaths(int startNode, int endNode) {
            List<List<Integer>> paths = new ArrayList<>();
            Set<Set<Integer>> visitedEdges = new HashSet<>();
            List<Integer> currentPath = new ArrayList<>();

            dfs(this.nodes.get(startNode - 1), endNode, currentPath, paths, visitedEdges);

            return paths;
        }

        private void dfs(Node currentNode, int endNode, List<Integer> currentPath, List<List<Integer>> paths, Set<Set<Integer>> visitedEdges) {
            currentPath.add(currentNode.value);

            if (currentNode.value == endNode) {
                paths.add(new ArrayList<>(currentPath));
            }

            for (Node neighbor : currentNode.neighbors) {
                Set<Integer> edge = new HashSet<>(Arrays.asList(currentNode.value, neighbor.value));

                if (!visitedEdges.contains(edge)) {
                    visitedEdges.add(edge);
                    dfs(neighbor, endNode, currentPath, paths, visitedEdges);
                    visitedEdges.remove(edge);
                }
            }

            currentPath.remove(currentPath.size() - 1);
        }
    }

    private static List<List<Integer>> findMatchingArrays(List<List<Integer>> arrays) {
        List<List<Integer>> matchingArrays = new ArrayList<>();

        for (int i = 0; i < arrays.size(); i++) {
            for (int j = i + 1; j < arrays.size(); j++) {
                if (isMatchingArray(arrays.get(i), arrays.get(j))) {
                    matchingArrays.add(arrays.get(i));
                    matchingArrays.add(arrays.get(j));
                }
            }
        }

        return matchingArrays;
    }

    private static boolean isMatchingArray(List<Integer> array1, List<Integer> array2) {
        for (int i = 1; i < array1.size()-1; i++) {
            if (array2.contains(array1.get(i))) {
                return false;

            }

        }
        return true;
    }
}

