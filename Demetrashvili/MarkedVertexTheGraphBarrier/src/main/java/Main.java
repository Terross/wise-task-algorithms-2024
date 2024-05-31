

import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        /**MarkedVertexTheGraphBarrier nodesEqEdges = new MarkedVertexTheGraphBarrier();*/
        NumberOfCliques nodesEqEdges = new NumberOfCliques();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph_cl.txt"));
        Integer result = nodesEqEdges.execute(testGraph);
        /**Boolean result = test.execute(testGraph);*/
        System.out.println(result);
    }
}