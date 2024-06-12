public class MatrixFunctools {
    public static AdjacencyMatrix transitiveClosure(final AdjacencyMatrix adjacency_matrix) {
        int[][] matrix = adjacency_matrix.getMatrix();

        for (int k = 0; k < matrix.length; ++k)
            for (int i = 0; i < matrix.length; ++i)
                for (int j = 0; j < matrix.length && i != j; ++j)
                    matrix[i][j] = matrix[i][j] | (matrix[i][k] & matrix[k][j]);

        AdjacencyMatrix transitive_closure_matrix = new AdjacencyMatrix(adjacency_matrix);
        transitive_closure_matrix.setMatrix(matrix);

        return transitive_closure_matrix;
    }

    public static boolean isMinReduction(final AdjacencyMatrix general_transitive_matrix,
                                                   AdjacencyMatrix other_adjacency_matrix) {
        int[][] tmp_matrix = other_adjacency_matrix.getMatrix();

        for (int i = 0; i < tmp_matrix.length; ++i)
            for (int j = 0; j < tmp_matrix.length; ++j)
                if (tmp_matrix[i][j] == 1) {
                    tmp_matrix[i][j] = 0;
                    other_adjacency_matrix.setMatrix(tmp_matrix);
                    if (general_transitive_matrix.equals(transitiveClosure(other_adjacency_matrix)))
                        return false;
                    tmp_matrix[i][j] = 1;
                }

        other_adjacency_matrix.setMatrix(tmp_matrix);
        return true;
    }
}
