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

    public List<Node> getN() {
        return neighbors;
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


    public List<Node> getNodes() {
        return nodes;
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

    public int getNum(int i) {
        return nodes.get(i).getN().size();
    }
}

public class k4subdivision implements GraphProperty {
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
        if (components.size() != 1) {
            return false;
        }
        int num_3 = 0;
        int num_2 = 0;

        for (int i = 0; i < gr.getNodes().size(); i++) {
            if (gr.getNodes().get(i).getN().size() == 3) {
                num_3 += 1;
            } else if (gr.getNum(i) == 2) {
                num_2 += 1;
            } else {
                return false;
            }
        }
        return num_3 == 4;
    }
}