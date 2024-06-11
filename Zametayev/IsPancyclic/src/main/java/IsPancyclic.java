import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

import static java.lang.Math.abs;

public class IsPancyclic implements GraphProperty {
    public boolean execute(Graph graph) {
        if (graph.getDirectType() == GraphType.DIRECTED) {
            return false;
        }
        if (graph.getVertexCount() < 3) {
            return false;
        }
        Vector<Edge> mst = find_MST(graph);
        Vector<Vector<Edge>> basis = get_Basic_of_Cycles(graph, mst);
        Vector<Integer> answer = generateBinaryCombinations(graph, basis, basis.size());
        for (int i = 2; i < graph.getVertexCount(); i++) {
            if (answer.get(i) == 0) {
                return false;
            }
        }
        return true;
    }

    private List<UUID> getListVertices(Graph graph) {
        List<UUID> vertices = new ArrayList<UUID>();
        Map<UUID, Vertex> map_v = graph.getVertices();
        for (var elem: map_v.entrySet()) {
            vertices.add(elem.getKey());
        }
        return vertices;
    }

    // Метод для нахождения базисных циклов
    private void single_dfs(List<UUID> verts, UUID current, List<Edge> edges, Vector<Edge> cycle, boolean[] visited_vert, boolean[] visited_edge, UUID start) {
        int currentIndex = verts.indexOf(current);
        visited_vert[currentIndex] = true;
        for (Edge edge : edges) {
            int edgeIndex = edges.indexOf(edge);
            if (!visited_edge[edgeIndex]) {
                if (edge.getFromV().equals(current)) {
                    int toIndex = verts.indexOf(edge.getToV());
                    if (edge.getToV().equals(start)) {
                        cycle.add(edge);
                        return;
                    } else if (!visited_vert[toIndex]) {
                        visited_edge[edgeIndex] = true;
                        cycle.add(edge);
                        single_dfs(verts, edge.getToV(), edges, cycle, visited_vert, visited_edge, start);
                        if (cycle.contains(edge)) {
                            return;
                        }
                    }
                } else if (edge.getToV().equals(current)) {
                    int fromIndex = verts.indexOf(edge.getFromV());
                    if (edge.getFromV().equals(start)) {
                        cycle.add(edge);
                        return;
                    } else if (!visited_vert[fromIndex]) {
                        visited_edge[edgeIndex] = true;
                        cycle.add(edge);
                        single_dfs(verts, edge.getFromV(), edges, cycle, visited_vert, visited_edge, start);
                        if (cycle.contains(edge)) {
                            return;
                        }
                    }
                }
            }
        }
        visited_vert[currentIndex] = false;
        if (!cycle.isEmpty()) {
            cycle.remove(cycle.size() - 1);
        }
    }

    private Vector<Edge> find_MST(Graph graph) {
        Vector<Edge> MST = new Vector<Edge>();
        List<UUID> verts = getListVertices(graph);
        int[] colors = new int[graph.getVertexCount()];
        for (int i = 0; i < graph.getVertexCount(); i++) {
            colors[i] = i;
        }
        for (Edge edge: graph.getEdges()) {
            if (colors[verts.indexOf(edge.getFromV())] != colors[verts.indexOf(edge.getToV())]) {
                MST.add(edge);
                for (int i = 0; i < graph.getVertexCount(); i++) {
                    if (colors[i] == colors[verts.indexOf(edge.getToV())]) {
                        colors[i] = colors[verts.indexOf(edge.getFromV())];
                    }
                }
            }
        }
        return MST;
    }

    private Vector<Vector<Edge>> get_Basic_of_Cycles(Graph graph, Vector<Edge> mst) {
        List<Edge> all_edges = graph.getEdges();
        Vector<Vector<Edge>> basis = new Vector<>();
        List<UUID> verts = getListVertices(graph);
        for (Edge edge: all_edges) {
            if (!mst.contains(edge)) {
                Vector<Edge> check = new Vector<>(mst);
                check.add(edge);
                boolean[] visited = new boolean[check.size()];
                Vector<Edge> new_cycle = new Vector<>();
                boolean[] visited_verts = new boolean[verts.size()];
                single_dfs(verts, edge.getFromV(), check, new_cycle, visited_verts, visited, edge.getFromV());
                basis.add(new_cycle);
            }
        }
        return basis;
    }

    private void xor(Vector<Integer> a, Vector<Integer> b) {
        for (int i = 0; i < a.size(); i++) {
            a.set(i, a.get(i) ^ b.get(i));
        }
    }

    private Vector<Integer> generateBinaryCombinations(Graph graph, Vector<Vector<Edge>> basis, int n) {
        int totalCombinations = 1 << n;  // 2^n combinations
        Vector<Vector<Integer>> basis_bin = new Vector<>();
        List<Edge> all_edges = graph.getEdges();
        Vector<Integer> length_of_cycles = new Vector<>(graph.getVertexCount());
        for (int i = 0; i < graph.getVertexCount(); i++) {
            length_of_cycles.add(0);
        }
        // Представление циклов в виде бинарной последовательности - вхождение ребер.
        for (int i = 0; i < basis.size(); i++) {
            Vector<Integer> bin = new Vector<>(n);
            for (int k = 0; k < all_edges.size(); k++) {
                bin.add(0);
            }
            for (int j = 0; j < all_edges.size(); j++) {
                if (basis.get(i).contains(all_edges.get(j))) {
                    bin.set(j, 1);
                } else {
                    bin.set(j, 0);
                }
            }
            basis_bin.add(bin);
        }
        Vector<Integer> base = new Vector<>(n);
        for (int k = 0; k < all_edges.size(); k++) {
            base.add(0);
        }
        int count = 0;
        // Перебор всех возможных комбинаций из базисов.
        for (int i = 0; i < totalCombinations; i++) {
            for (int k = 0; k < all_edges.size(); k++) {
                base.set(k, 0);
            }
            String binaryString = Integer.toBinaryString(i);
            while (binaryString.length() < n) {
                binaryString = "0" + binaryString;
            }
            for (int j = 0; j < binaryString.length(); j++) {
                if (binaryString.charAt(j) == '1') {
                    xor(base, basis_bin.get(j));
                }
            }
            for (int k = 0; k < all_edges.size(); k++) {
                if (base.get(k) == 1) count += 1;
            }
            if (count == 0 || count > graph.getVertexCount()) {
                count = 0;
                continue;
            }
            length_of_cycles.set(count - 1, length_of_cycles.get(count - 1) + 1);
            count = 0;
        }
        return length_of_cycles;
    }
}
