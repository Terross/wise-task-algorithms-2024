package ru.leti;

import java.io.File;
import java.io.FileNotFoundException;

import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.model.Graph;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        TransitiveReductions tr = new TransitiveReductions();
        Graph graph = GraphFactory.loadGraphFromFile(new File(args[1]));
        System.out.println(tr.execute(graph));
    }
}
