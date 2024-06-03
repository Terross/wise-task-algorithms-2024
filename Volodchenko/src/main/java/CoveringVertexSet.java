import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.util.*;

public class CoveringVertexSet implements GraphCharacteristic {
    public Map<Vertex, List<Edge>> getVerticesEdges(Graph graph) {///делает Map списка рёбер по вершинам
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
        int limit = 1;///2^vcnt
        int len = 0;///....вообще равен vcnt.....но имеет духовную ценность
        for( int i = 0; i < vcnt; ++ i ){
            limit *= 2;
            len ++;
        }


        Map<Vertex, List<Edge>> map = new HashMap<Vertex, List<Edge>>(getVerticesEdges(graph));///Удобный доступ к рёбрам конкретных вершин
        List<Edge> edges = new ArrayList<Edge>(graph.getEdges());///Все вершины, для "Удобный доступ"
        List<Vertex> vertices = new ArrayList<Vertex>(graph.getVertices().values().stream().toList());///Все рёбра

        Set<Edge> total = new HashSet<Edge>();///Все рёбра в 1 месте, для быстрого сравнения структур
        for( Edge e: edges ){
            total.add(e);
        }


        for( int i = 0; i < limit; ++ i ){//1_1:i=1->i=0(no edges case)
            int[] tmp = new int[len+1];
            int copy = i;
            int ind = 0;
            while( copy != 0 ){///Этот сегмент преобразует число в список используемых сейчас элементов
                tmp[ind] = copy % 2;
                copy /= 2;
                ind += 1;
            }


            Set<Edge> used = new HashSet<Edge>();///Собираем сюда рёбра, что бы сравнить с total и понять, достигли ли мы цели


            int newans = 0;
            for( int j = 0; j < len; ++ j ){///По всем вершинам
                if( tmp[j] == 1 ){///Но интересуют только "разрешённые" на этом ходу
                    newans ++;///Потенциально взяли вершину
                    for( Edge e: map.get(vertices.get(j)) ){///Нужно учесть рёбра, что бы потом быстро сравнить сеты и понять
                        used.add(e);///Достигнута ли цель
                    }
                }
            }


            if( !used.equals(total) ){///Стираем потенциальный ответ, если цель не была достигнута
                newans = ans;
            }

            if( newans < ans ) ans = newans;///Если вышло лучше чем было...то нужно запомнить
        }

        return ans;
    }
}
