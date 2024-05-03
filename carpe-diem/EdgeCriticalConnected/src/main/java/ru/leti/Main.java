package ru.leti;

import com.mathsystem.api.graph.GraphFactory;
import ru.leti.EdgeCriticalConnected;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph.txt"));

        EdgeCriticalConnected edgeCriticalConnected = new EdgeCriticalConnected();
        var edges = testGraph.getEdges();
        var k = edgeCriticalConnected.execute(testGraph);
        System.out.println(k);

    }
}