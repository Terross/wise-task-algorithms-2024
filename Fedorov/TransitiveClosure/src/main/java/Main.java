import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        TransitiveClosure transitiveClosure = new TransitiveClosure();
        var testGraph = GraphFactory.loadGraphFromFile(new File("/home/mikhail/Kolok/NodeEqEdges/src/test/resources/graph.txt"));
        boolean result = transitiveClosure.execute(testGraph);
        System.out.println(result);
    }
}
