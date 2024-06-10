import com.mathsystem.api.graph.GraphFactory;


import java.io.File;
import java.io.FileNotFoundException;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/Bridge.txt"));

        AllEdgesInTriangle edgeCriticalConnected = new AllEdgesInTriangle();
        var edges = testGraph.getEdges();
        var k = edgeCriticalConnected.execute(testGraph);
        System.out.println(k);

    }
}