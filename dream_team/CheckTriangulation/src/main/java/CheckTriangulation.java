import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

public class CheckTriangulation implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        int numberOfVertices = graph.getVertexCount();
        int numberOfEdges = graph.getEdgeCount();

        if(numberOfVertices < 3) {
            return false;
        } else {
            return numberOfEdges == 3 * numberOfVertices - 6;
        }
    }
}

