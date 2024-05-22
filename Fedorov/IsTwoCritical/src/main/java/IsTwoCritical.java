import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

public class IsTwoCritical implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        return graph.getVertexCount() == 2 && graph.getEdgeCount() == 1;
    }
}

