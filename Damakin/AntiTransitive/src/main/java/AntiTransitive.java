import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;

import com.mathsystem.domain.plugin.plugintype.GraphProperty;

public class AntiTransitive implements GraphProperty {

    @Override
    public boolean execute(Graph graph) {
            for (int i = 0; i < graph.getEdgeCount(); i++) {
                Edge edge_tmp_first = graph.getEdges().get(i);
                for (int j = 0; j < graph.getEdgeCount(); j++){
                    Edge edge_tmp_second = graph.getEdges().get(j);
                    for (int k = 0; k < graph.getEdgeCount(); k++){
                        Edge edge_tmp_third = graph.getEdges().get(k);
                        if (k == i || k == j || i == j){
                            continue;
                        }
                        if (( edge_tmp_first.getToV().compareTo(edge_tmp_second.getFromV()) == 0 || edge_tmp_first.getToV().compareTo(edge_tmp_second.getToV()) == 0 ||
                                edge_tmp_first.getFromV().compareTo(edge_tmp_second.getFromV()) == 0 || edge_tmp_first.getFromV().compareTo(edge_tmp_second.getToV()) == 0)
                                &&
                                ( edge_tmp_second.getToV().compareTo(edge_tmp_third.getFromV()) == 0 || edge_tmp_second.getToV().compareTo(edge_tmp_third.getToV()) == 0 ||
                                        edge_tmp_second.getFromV().compareTo(edge_tmp_third.getFromV()) == 0 || edge_tmp_second.getFromV().compareTo(edge_tmp_third.getToV()) == 0)
                                &&
                                ( edge_tmp_third.getToV().compareTo(edge_tmp_first.getFromV()) == 0 || edge_tmp_third.getToV().compareTo(edge_tmp_first.getToV()) == 0 ||
                                        edge_tmp_third.getFromV().compareTo(edge_tmp_first.getFromV()) == 0 || edge_tmp_third.getFromV().compareTo(edge_tmp_first.getToV()) == 0)
                        ) return false;
                    }
                }
            }
        return true;
    }
}