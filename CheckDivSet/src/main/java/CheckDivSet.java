import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import com.mathsystem.api.graph.model.Edge;

import java.util.*;

import com.mathsystem.api.graph.model.Vertex;

public class CheckDivSet implements GraphProperty {

    public class ColorNode
    {
        public int color = 0;
        public Vertex node;
        public ColorNode(Vertex node) {
            this.node = node;
        }
    }

    public ColorNode getColorNode(UUID edge, Vector<ColorNode> set_color_node)
    {
        for (ColorNode temp_color : set_color_node) {
            if (temp_color.node.getId().equals(edge)) {
                return temp_color;
            }
        }
        return null;
    }

    public void workNode(Graph graph, ColorNode color_node, Vector<ColorNode> set_color_node, boolean with_red_node, int number_color){
        for (Edge edge : graph.getEdges()) {
            if (edge.getFromV().equals(color_node.node.getId())) {
                ColorNode temp_color = getColorNode(edge.getToV(), set_color_node);
                dfs(temp_color, graph, number_color, set_color_node, with_red_node);
            }
            else if (edge.getToV().equals(color_node.node.getId())) {
                ColorNode temp_color = getColorNode(edge.getFromV(), set_color_node);
                dfs(temp_color, graph, number_color, set_color_node, with_red_node);
            }
        }
    }

    HashSet<Integer> unic_set = new HashSet<>();
    HashSet<ColorNode> visited = new HashSet<>();
    public void dfs(ColorNode color_node, Graph graph, int number_color, Vector<ColorNode> set_color_node, boolean with_red_node)
    {
        if (with_red_node) {
            if (color_node.color == 0 && color_node.node.getColor() != Color.red) {
                color_node.color = number_color;
                workNode(graph, color_node, set_color_node, with_red_node, number_color);
            }
        }
        else {
            if (!visited.contains(color_node)) {
                visited.add(color_node);
                unic_set.add(color_node.color);
                workNode(graph, color_node, set_color_node, with_red_node, number_color);
            }
        }
    }
    Vector<ColorNode> set_color_nodes = new Vector<>();
    public boolean solve(Graph graph, boolean with_red_node)
    {
        int number_color = 0;
        if (with_red_node) {
            for (Vertex ver : graph.getVertices().values()) {
                ColorNode temp = new ColorNode(ver);
                set_color_nodes.add(temp);
            }
        }
        for (ColorNode node : set_color_nodes) {
            if (with_red_node) {
                if (node.color == 0 && node.node.getColor() != Color.red) {
                    number_color = number_color + 1;
                    dfs(node, graph, number_color, set_color_nodes, with_red_node);
                }
            }
            else {
                if (!visited.contains(node)) {
                    dfs(node, graph, number_color, set_color_nodes, with_red_node);
                    if (unic_set.size() > 2){
                        return true;
                    }
                    unic_set.clear();
                }
            }
        }
        return false;
    }

    @Override
    public boolean execute(Graph graph) {
        if (graph.getDirectType() == GraphType.DIRECTED)
        {
            return false;
        }
        solve(graph, true);
        return solve(graph, false);
    }


}