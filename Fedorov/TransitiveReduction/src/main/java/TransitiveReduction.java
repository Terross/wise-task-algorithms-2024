import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class TransitiveReduction implements GraphProperty {

    private final List<Color> colors = Arrays.asList(Color.red, Color.pink,
            Color.blue, Color.green, Color.yellow, Color.brown, Color.gray);

    private List<AdjacencyMatrix> SplitToColor(final Graph graph) {
        List<AdjacencyMatrix> list = new ArrayList<>();

        for (Color color : colors) {
            AdjacencyMatrix tmp = new AdjacencyMatrix(graph, color);
            if (tmp.isValidMatrix())
                list.add(tmp);
        }

        return list;
    }

    @Override
    public boolean execute(Graph graph) {
        List<AdjacencyMatrix> list_adjacency_matrix = SplitToColor(graph);
        AdjacencyMatrix general_transitive_matrix = MatrixFunctools.transitiveClosure(new AdjacencyMatrix(graph));

        for (AdjacencyMatrix other_adjacency_matrix : list_adjacency_matrix)
            if (general_transitive_matrix.equals(MatrixFunctools.transitiveClosure(other_adjacency_matrix)))
                if (MatrixFunctools.isMinReduction(general_transitive_matrix, other_adjacency_matrix))
                    return true;

        return false;
    }
}
