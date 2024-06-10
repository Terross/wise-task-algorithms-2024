//package ru.leti;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.awt.*;
import java.util.*;
import java.util.List;


public class OneValent implements GraphProperty {
    private final ArrayList<Edge> chosenEdges = new ArrayList<>();
    private final ArrayList<UUID> chosenVertexes = new ArrayList<>();

    // собираем выделенные ребра
    private void getChosenEdges(Graph graph) {
        for (Edge edge : graph.getEdges()) {
            if (edge.getColor() == Color.red) {
                chosenEdges.add(edge);
            }
        }
    }

    // собираем выделенные вершины
    private void getChosenVertexes(Graph graph) {
        for (Edge edge : chosenEdges) {
            if (!(chosenVertexes.contains(edge.getToV()))){
                chosenVertexes.add(edge.getToV());
            }
            if (!(chosenVertexes.contains(edge.getFromV()))){
                chosenVertexes.add(edge.getFromV());
            }
        }
    }

    // проверяем на вхождение и выход ребер, сверяем количесвто вершин
    private boolean isOneValent(UUID vertex) {
        int from = 0;
        int to = 0;
        for (Edge edge : chosenEdges) {
            if (edge.getFromV().equals(vertex)) {
                from = from + 1;
            } else if (edge.getToV().equals(vertex)) {
                to = to + 1;

            }
        }

        // если одно вхождение и один выход - возврат 1
        return from == 1 && to == 1;
    }

    @Override
    public boolean execute(Graph graph) {
        //найдем выделенные ребра
        getChosenEdges(graph);
        //выделенные вершины
        getChosenVertexes(graph);

        // проверка, что кол-во выделенных вершин = кол-ву вершин исход графа
        if (chosenVertexes.size() != graph.getVertexCount()) {
            return false;
        }

        // проверка каждой вершины на одновалентность
        for (UUID vertex : chosenVertexes) {
            if (!isOneValent(vertex)) {
                return false;
            }
        }
        return true;
    }

}
