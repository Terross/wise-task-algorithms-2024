package ru.leti;

import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        MaxSizeOfPathsBetween max_size = new MaxSizeOfPathsBetween();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph2.txt"));
        Integer result = max_size.execute(testGraph);
        System.out.println(result);
    }

}