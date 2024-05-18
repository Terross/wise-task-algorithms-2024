import com.mathsystem.api.graph.GraphFactory;
import com.mathsystem.api.graph.oldmodel.directed.DirectedGraph;
import com.mathsystem.api.graph.oldmodel.undirected.UndirectedEdge;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        IsFourSimpleWays isFourSimpleWays = new IsFourSimpleWays();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/test/resources/trueGraph.txt"));
        boolean result = isFourSimpleWays.execute(testGraph);
        System.out.println(result);
        System.out.println(testGraph);
    }
}