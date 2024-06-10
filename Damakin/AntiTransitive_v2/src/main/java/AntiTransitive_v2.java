import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AntiTransitive_v2 implements GraphProperty {

    public boolean execute(Graph graph) {

        List<Edge> edges = graph.getEdges();
        Map<UUID, Vertex> vertexMap = graph.getVertices();
        List<UUID> vertices = vertexMap.keySet().stream().toList();
        int vertexCount = vertices.size();
        boolean[][] matrix = new boolean[vertexCount][vertexCount];

        // Заполнение матрицы смежности
        for (Edge edge : edges) {
            UUID from = edge.getFromV();
            UUID to = edge.getToV();

            int index_from = vertices.indexOf(from);
            int index_to = vertices.indexOf(to);

            matrix[index_from][index_to] = true;

            if (graph.getDirectType() != GraphType.DIRECTED) {
                matrix[index_to][index_from] = true;
            }
        }

        // Проверка антитранзитивности
        for (int i = 0; i < vertexCount; ++i) {
            for (int j = 0; j < vertexCount; ++j) {
                if (matrix[i][j]) {
                    for (int k = 0; k < vertexCount; ++k) {
                        if (matrix[j][k] && i != k) {
                            if (matrix[i][k]) {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
