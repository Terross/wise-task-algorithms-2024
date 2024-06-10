package ru.leti;

import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        OnePlanarGraph nodesEqEdges = new OnePlanarGraph();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph_3.txt"));
        boolean result = nodesEqEdges.execute(testGraph);
        System.out.println(result);
    }
}