import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;

import java.util.*;

import java.util.ArrayList;
import java.util.List;

public class PerfectMatching implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        List<Edge> edgesToCheck = getEdgesToCheck(graph);
        if (isPerfectMatching(graph, edgesToCheck)) {
            return true;
        } else {
            return false;
        }
    }

    //Проверка выделенных ребер на совершенное паросочетание
    public static boolean isPerfectMatching(Graph graph, List<Edge> edgesToCheck) {
        //Цикл по всем вершинам
        for (UUID vertexId : graph.getVertices().keySet()) {
            //Переменная, показывающая инцидентна ли вершина ровно одному выделенному ребру
            boolean incidentToSingleEdge = false;
            //Цикл по всем выделенным ребрам
            for (Edge edge : edgesToCheck) {
                //Если вершина принадлежит выделенному ребру
                if (edge.getFromV().equals(vertexId) || edge.getToV().equals(vertexId)) {
                    //Если вершина принадлежит выделенному ребру и инциндентна другому выделенному ребру
                    if (incidentToSingleEdge) {
                        //Возвращаем False
                        return false;
                    }
                    //Если вершина принадлежит выделенному ребру и неинцендентна другому выделенному ребру, меняем значение переменной на True
                    incidentToSingleEdge = true;
                }
            }
            //Если вершина не инцидентна ни 1 выделенному ребру
            if (!incidentToSingleEdge) {
                //Возвращаем False
                return false;
            }
        }

        //Если все вершины принадлежат ровно 1 выделенному ребру - возвращаем True
        return true;
    }

    //Функиця получения выделенных ребер
    public static List<Edge> getEdgesToCheck(Graph graph) {
        //Создание списка для хранения выделенных ребер
        List<Edge> edgesToCheck = new ArrayList<>();
        //Цикл по всем ребрам графа
        for (Edge edge : graph.getEdges()) {
            //Если цвет ребра не серый
            if (edge.getColor() != Color.gray) {
                //Добавляем ребро в список выделенных ребер
                edgesToCheck.add(edge);
            }
        }
        //Возвращаем список выделенных ребер
        return edgesToCheck;
    }
}