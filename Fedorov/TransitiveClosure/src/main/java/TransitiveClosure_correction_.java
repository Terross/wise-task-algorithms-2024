import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

public class TransitiveClosure_correction_ implements GraphProperty {

    public AdjacencyMatrix transitiveClosure(final AdjacencyMatrix adjacency_matrix) {
        int[][] matrix = adjacency_matrix.getMatrix();

        for (int k = 0; k < matrix.length; ++k)
            for (int i = 0; i < matrix.length; ++i)
                for (int j = 0; j < matrix.length && i != j; ++j)
                    matrix[i][j] = matrix[i][j] | (matrix[i][k] & matrix[k][j]);

        AdjacencyMatrix transitive_closure_matrix = new AdjacencyMatrix(adjacency_matrix);
        transitive_closure_matrix.setMatrix(matrix);

        return transitive_closure_matrix;
    }

    @Override
    public boolean execute(Graph graph) {
        AdjacencyMatrix matrix = new AdjacencyMatrix(graph);
        return matrix.equals(transitiveClosure(matrix));
    }
}