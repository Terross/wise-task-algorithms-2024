import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FactorCritical implements GraphProperty {
    public FactorCritical() {
    }

    public boolean execute(Graph graph) {
        List<Edge> array1 = new ArrayList();
        Map<UUID, Vertex> map1 = new HashMap();
        Map<UUID, Vertex> map2 = graph.getVertices();
        if (graph.getVertexCount() % 2 == 0) {
            return false;
        } else {
            List<Edge> array2 = graph.getEdges();
            if (array2 == null) {
                return false;
            } else {
                Iterator var6 = map2.keySet().iterator();

                while(var6.hasNext()) {
                    UUID vertex = (UUID)var6.next();
                    map1.putAll(map2);
                    map1.remove(vertex);
                    Iterator var8 = array2.iterator();

                    while(var8.hasNext()) {
                        Edge edge = (Edge)var8.next();
                        if (!edge.getToV().equals(vertex) && !edge.getFromV().equals(vertex)) {
                            array1.add(new Edge(edge.getToV(), edge.getFromV(), edge.getColor(), edge.getWeight(), edge.getLabel()));
                        }
                    }

                    Graph graph1 = new Graph(graph.getDirectType(), map1.size(), array1.size(), map1, array1);
                    if (2 * this.MaxMatching(graph1) != map1.size()) {
                        return false;
                    }

                    map1.clear();
                    array1.clear();
                }

                return true;
            }
        }
    }

    public Integer MaxMatching(Graph graph) {
        List<Edge> M = new ArrayList();
        Map<UUID, Vertex> map = graph.getVertices();
        List<Edge> array = graph.getEdges();
        Iterator var5 = array.iterator();

        Edge temp;
        while(var5.hasNext()) {
            temp = (Edge)var5.next();
            ((Vertex)map.get(temp.getToV())).setWeight(0);
            ((Vertex)map.get(temp.getFromV())).setWeight(0);
        }

        var5 = array.iterator();

        while(var5.hasNext()) {
            temp = (Edge)var5.next();
            ((Vertex)map.get(temp.getToV())).setWeight(((Vertex)map.get(temp.getToV())).getWeight() + 1);
            ((Vertex)map.get(temp.getFromV())).setWeight(((Vertex)map.get(temp.getFromV())).getWeight() + 1);
        }

        boolean sorted = false;

        int i;
        while(!sorted) {
            sorted = true;

            for(i = 0; i < array.size() - 1; ++i) {
                int sumVWi = ((Vertex)map.get(((Edge)array.get(i)).getToV())).getWeight() + ((Vertex)map.get(((Edge)array.get(i)).getFromV())).getWeight();
                int sumVWi1 = ((Vertex)map.get(((Edge)array.get(i + 1)).getToV())).getWeight() + ((Vertex)map.get(((Edge)array.get(i + 1)).getFromV())).getWeight();
                if (sumVWi > sumVWi1) {
                    temp = (Edge)array.get(i);
                    array.set(i, (Edge)array.get(i + 1));
                    array.set(i + 1, temp);
                    sorted = false;
                }
            }
        }

        label59:
        for(i = 0; i < array.size(); ++i) {
            if (((Edge)array.get(i)).getColor() != Color.red) {
                M.add((Edge)array.get(i));
                ((Edge)array.get(i)).setColor(Color.blue);
                Iterator var11 = array.iterator();

                while(true) {
                    Edge edge;
                    do {
                        do {
                            if (!var11.hasNext()) {
                                continue label59;
                            }

                            edge = (Edge)var11.next();
                        } while(edge == array.get(i));
                    } while(map.get(edge.getToV()) != map.get(((Edge)array.get(i)).getToV()) && map.get(edge.getToV()) != map.get(((Edge)array.get(i)).getFromV()) && map.get(edge.getFromV()) != map.get(((Edge)array.get(i)).getToV()) && map.get(edge.getFromV()) != map.get(((Edge)array.get(i)).getFromV()));

                    edge.setColor(Color.red);
                }
            }
        }

        return M.toArray().length;
    }
}
