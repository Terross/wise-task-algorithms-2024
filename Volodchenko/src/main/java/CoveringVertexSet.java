import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.util.*;

public class CoveringVertexSet implements GraphCharacteristic {
    public Map<Vertex, List<Edge>> getVerticesEdges(Graph graph) {
        Map<Vertex, List<Edge>> res = new HashMap<>();
        for (Vertex vertex : graph.getVertices().values()) {
            res.put(vertex, new ArrayList<>());
            for (Edge edge : graph.getEdges()) {
                if (edge.getFromV().equals(vertex.getId()) || edge.getToV().equals(vertex.getId())) {
                    res.get(vertex).add(edge);
                }
            }
        }
        return res;
    }

    @Override
    public Integer execute(Graph graph) {
        int vcnt = graph.getVertexCount();
        int ecnt = graph.getEdgeCount();
        int ans = vcnt;
        int limit = 1;
        int len = 0;
        for( int i = 0; i < vcnt; ++ i ){
            limit *= 2;
            len ++;
        }
//        System.out.println(len);
//        System.out.println(limit);

        Map<Vertex, List<Edge>> map = new HashMap<Vertex, List<Edge>>(getVerticesEdges(graph));
        List<Edge> edges = new ArrayList<Edge>(graph.getEdges());
        List<Vertex> vertices = new ArrayList<Vertex>(graph.getVertices().values().stream().toList());

        Set<Edge> total = new HashSet<Edge>();
        //System.out.println(Arrays.toString(vertices.toArray()));
        for( Edge e: edges ){
            total.add(e);
        }


        for( int i = 0; i < limit; ++ i ){//1_1:i=1->i=0(no edges case)
            int[] tmp = new int[len+1];
            int copy = i;
            int ind = 0;
            while( copy != 0 ){
                tmp[ind] = copy % 2;
                copy /= 2;
                ind += 1;
            }
            Set<Edge> used = new HashSet<Edge>();
//            System.out.println(i);
//            for( int j = 0; j < len + 1; ++ j ){
//                System.out.println(tmp[j]);
//            }
//            System.out.println("\n-------\n");


            int newans = 0;
            for( int j = 0; j < len; ++ j ){
                if( tmp[j] == 1 ){
                    newans ++;
                    for( Edge e: map.get(vertices.get(j)) ){
                        used.add(e);
                    }
                }
            }

//            System.out.println(Arrays.toString(used.toArray()));
//            System.out.println(Arrays.toString(total.toArray()));
//            System.out.println(newans);
            if( !used.equals(total) ){
                newans = ans;
            }

            if( newans < ans ) ans = newans;
        }

        return ans;
    }
}
