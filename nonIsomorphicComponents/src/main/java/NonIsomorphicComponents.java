import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public List<List<Node>> findComponents() {
        List<List<Node>> components = new ArrayList<>();
        boolean[] visited = new boolean[nodes.size()];
        Arrays.fill(visited, false);


        for (Node node : nodes) {
            if (!visited[node.value - 1]) {
                List<Node> component = new ArrayList<>();
                Queue<Node> queue = new LinkedList<>();
                queue.add(node);
                while (!queue.isEmpty()) {
                    Node current = queue.remove();
                    if (!visited[current.value - 1]) {
                        visited[current.value - 1] = true;
                        component.add(current);
                        for (Node neighbor : current.neighbors) {
                            queue.add(neighbor);
                        }
                    }
                }
                components.add(component);
            }
        }
        return components;
    }
}

public class NonIsomorphicComponents implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        var edges = graph.getEdges();
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
        }

        List<List<Node>> components = gr.findComponents();
        if (components.size() <= 1) {
            return false;
        }
        for (int i = 0; i < components.size() - 1; i++) {
            for (int k = i + 1; k < components.size(); k++) {
                List<Node> comp1 = components.get(i);
                List<Node> comp2 = components.get(k);
                if (comp1.size() == comp2.size()) {
                    ArrayList<Integer> compI1 = new ArrayList<>();
                    ArrayList<Integer> compI2 = new ArrayList<>();
                    for (Node node : comp1) {
                        compI1.add(node.neighbors.size());
                    }
                    for (Node node : comp2) {
                        compI2.add(node.neighbors.size());
                    }
                    Set<Integer> hash1 = new HashSet<>(compI1);
                    Set<Integer> hash2 = new HashSet<>(compI2);
                    if (hash1.equals(hash2)) {
                        return false;
                    }
                } else {
                    return false;
                }
            }

        }
        return true;
    }
}