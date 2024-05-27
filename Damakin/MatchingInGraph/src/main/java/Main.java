

import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        MatchingInGraph matchingInGraph = new MatchingInGraph();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph.txt"));
        Integer result = matchingInGraph.execute(testGraph);
        System.out.println(result);
    }
}