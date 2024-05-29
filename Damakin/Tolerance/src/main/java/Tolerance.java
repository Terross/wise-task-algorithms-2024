import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;



public class Tolerance implements GraphProperty {
    @Override
    public boolean execute(Graph graph){
        // Check symmetry:
        if (graph.getDirectType() == GraphType.DIRECTED){
            if (graph.getEdgeCount() % 2 != 0){
                return false;
            }
            int flag = 0;
            for (int i = 0; i < graph.getEdgeCount(); i++){
                Edge edge_tmp_first = graph.getEdges().get(i);
                for (int j = i + 1; j < graph.getEdgeCount(); j++){
                    Edge edge_tmp_second = graph.getEdges().get(j);
                    if (edge_tmp_first.getToV().compareTo(edge_tmp_second.getFromV()) == 0
                            &&  edge_tmp_second.getToV().compareTo(edge_tmp_first.getFromV()) == 0){
                        flag += 1;
                    }
                }
            }
            return flag == graph.getEdgeCount() / 2;
        } else {
            return true;
        }
    }
}
