package ru.leti;

import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        TriangleDegreeThreeChecker TriangleDegreeThreeChecker = new TriangleDegreeThreeChecker();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph (9).txt"));
        boolean result = TriangleDegreeThreeChecker.execute(testGraph);
        System.out.println(result);
    }
}