package ru.leti;

import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import ru.leti.PalmTree;


public class IsPlanar implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        boolean isPlanar = false;
        var T = new PalmTree();
        T.Builder(graph.getEdges());
        isPlanar = T.testPlanar();
        return isPlanar;
    }
}