import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

public class CorrectnessOfVertexColoring implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        var vertices = graph.getVertices();
        for (Edge edge : graph.getEdges()) {
            if (vertices.get(edge.getFromV()).getColor() == vertices.get(edge.getToV()).getColor()) {
                return false;
            }
        }
        return true;
    }
}