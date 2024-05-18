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


public class Valent implements GraphProperty {
    private final ArrayList<Edge> chosenEdges = new ArrayList<>();
    private final ArrayList<Vertex> chosenVertexes = new ArrayList<>();
    private final ArrayList<Vertex> vertices = new ArrayList<>();

    private void getChosenEdges(Graph graph) {
        for (Edge edge : graph.getEdges()) {
            if (edge.getColor() == Color.red) {
                chosenEdges.add(edge);
            }
        }
    }

    private void getChosenVertexes(Graph graph) {
        vertices.addAll(graph.getVertices().values());
        for (Vertex vertex : vertices) {
            if (vertex.getColor() == Color.red) {
                chosenVertexes.add(vertex);
            }
        }
    }

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
        return from == 1 && to == 1;
    }

    @Override
    public boolean execute(Graph graph) {
        getChosenEdges(graph);
        getChosenVertexes(graph);

        if (chosenVertexes.size() != graph.getVertexCount()) {
            return false;
        }
        for (Vertex vertex : chosenVertexes) {
            if (!isOneValent(vertex.getId())) {
                return false;
            }
        }
        return true;
    }

}
