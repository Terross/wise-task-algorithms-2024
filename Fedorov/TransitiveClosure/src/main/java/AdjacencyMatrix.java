import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.graph.repository.GraphType;

import java.util.*;

public class AdjacencyMatrix {

    private UUID[] vertexes;
    private int[][] matrix;

    private int getIndexVertex(UUID vertex) {
        for (int i = 0; i < vertexes.length; i++)
            if (vertexes[i].equals(vertex))
                return i;
        return -1;
    }

    public AdjacencyMatrix(final Graph graph) {
        vertexes = graph.getVertices().keySet().toArray(UUID[]::new);
        Arrays.sort(vertexes);
        matrix = new int[vertexes.length][vertexes.length];

        for (int i = 0; i < vertexes.length; ++i)
            for (int j = 0; j < vertexes.length; ++j)
                matrix[i][j] = 0;

        for (Edge edge : graph.getEdges())
            matrix[getIndexVertex(edge.getFromV())][getIndexVertex(edge.getToV())] = 1;


        if (graph.getDirectType() == GraphType.UNDIRECTED)
            for (int i = 0; i < vertexes.length; ++i)
                for (int j = 0; j < i; ++j) {
                    matrix[i][j] = matrix[j][i] + matrix[i][j];
                    matrix[j][i] = matrix[i][j];
                }

    }

    public AdjacencyMatrix(final Graph graph, final Color color) {
        vertexes = graph.getVertices().keySet().toArray(UUID[]::new);
        Arrays.sort(vertexes);
        matrix = new int[vertexes.length][vertexes.length];

        for (int i = 0; i < vertexes.length; ++i)
            for (int j = 0; j < vertexes.length; ++j)
                matrix[i][j] = 0;

        for (Edge edge : graph.getEdges())
            if (edge.getColor() == color)
                matrix[getIndexVertex(edge.getFromV())][getIndexVertex(edge.getToV())] = 1;

        if (graph.getDirectType() == GraphType.UNDIRECTED)
            for (int i = 0; i < vertexes.length; ++i)
                for (int j = 0; j < i; ++j) {
                    matrix[i][j] = matrix[j][i] + matrix[i][j];
                    matrix[j][i] = matrix[i][j];
                }

    }

    public AdjacencyMatrix(AdjacencyMatrix other) {
        vertexes = Arrays.copyOf(other.vertexes, other.vertexes.length);
        matrix = other.getMatrix();
    }

    public AdjacencyMatrix clone() { return new AdjacencyMatrix(this); }

    public boolean isValidMatrix() { return vertexes != null; }

    public int[][] getMatrix() {
        int[][] tmp = new int[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; ++i)
            tmp[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        return tmp;
    }

    public boolean setMatrix(int[][] other_matrix) {
        if (other_matrix.length != matrix.length)
            return false;

        for (int i = 0; i < matrix.length; ++i)
            if (other_matrix[i].length != matrix[i].length)
                return false;

        for (int i = 0; i < matrix.length; ++i)
            matrix[i] = Arrays.copyOf(other_matrix[i], other_matrix[i].length);

        return true;
    }

    public boolean equals(AdjacencyMatrix adjacencyMatrix) {
        if (vertexes.length != adjacencyMatrix.vertexes.length)
            return false;

        for (int i = 0; i < vertexes.length; ++i) {
            if (vertexes[i] != adjacencyMatrix.vertexes[i])
                return false;
            for (int j = 0; j < vertexes.length; ++j)
                if (matrix[i][j] != adjacencyMatrix.matrix[i][j])
                    return false;
        }

        return true;
    }

}