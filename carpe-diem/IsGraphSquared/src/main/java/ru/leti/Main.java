package ru.leti;


import com.mathsystem.api.graph.GraphFactory;
//import ru.leti.EdgeCriticalConnected;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/small_true_graph_4_transitive_closure.txt"));
        IsGraphSquared dfs = new IsGraphSquared();
        System.out.println(dfs.check_is_squared(testGraph));
        }
    }
