
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.util.*;

public class MatchingInGraph implements GraphCharacteristic {
    private int[][] createAdjMatrix(Graph graph) {
        Set<UUID> vertexes = graph.getVertices().keySet();
        Map<UUID, Integer> v_list = new HashMap<>();
        int numVertices = vertexes.size();
        for(int i = 0; i < numVertices; i++){
            for (UUID vertex : graph.getVertices().keySet()){
                v_list.put(vertex, i);
                i++;
            }
        }
        int[][] matrix = new int[numVertices][numVertices];
        for (Edge edge : graph.getEdges()) {
            UUID fromV = edge.getFromV();
            UUID toV = edge.getToV();
            //Find the indices of the vertices in the adjacency matrix
            int fromIndex = v_list.get(fromV);
            int toIndex = v_list.get(toV);
            // Ensure both vertices exist in the graph
            if (graph.getDirectType() == GraphType.UNDIRECTED) {
                matrix[fromIndex][toIndex] = 1;
                matrix[toIndex][fromIndex] = 1;
            }
            if (graph.getDirectType() == GraphType.DIRECTED) {
                matrix[fromIndex][toIndex] = 1;
            }
        }
        return matrix;
    }
    private int[][] minor(int[][] a, int x, int y){
        int length = a.length-1;
        int[][] result = new int[length][length];
        for(int i=0;i<length;i++) for(int j=0;j<length;j++){
            if(i<x && j<y){
                result[i][j] = a[i][j];
            }else if(i>=x && j<y){
                result[i][j] = a[i+1][j];
            }else if(i<x && j>=y){
                result[i][j] = a[i][j+1];
            }else{ //i>x && j>y
                result[i][j] = a[i+1][j+1];
            }
        }
        return result;
    }
    private int perm(int[][] a){
        if(a.length == 1){
            return a[0][0];
        }else{
            int sum = 0;
            for(int i=0;i<a.length;i++){
                sum += a[0][i] * perm(minor(a,0,i));
            }
            return sum;
        }
    }
    @Override
    public Integer execute(Graph graph) {
        int[][] matr = createAdjMatrix(graph);
        //System.out.println(Arrays.deepToString(matr));  For debugging purposes
        return perm(matr);
    }
}
