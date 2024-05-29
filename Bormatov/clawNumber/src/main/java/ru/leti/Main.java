package ru.leti;

import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;


public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        var graph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph4.txt"));
        var solution = new clawNumber();
        System.out.println(solution.execute(graph));
        }
}