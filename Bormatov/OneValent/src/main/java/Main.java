//package ru.leti;

import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        OneValent max_size = new OneValent();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/graphs/graph.txt"));
        boolean result = max_size.execute(testGraph);
        System.out.println(result);
    }
}