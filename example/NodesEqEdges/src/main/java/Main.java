import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        NodesEqEdges nodesEqEdges = new NodesEqEdges();
        var testGraph = GraphFactory.loadGraphFromFile(new File("/home/egor/C++/wise-task-algorithms-2024/example/NodesEqEdges/src/main/resources/"));
        boolean result = nodesEqEdges.execute(testGraph);
        System.out.println(result);
    }
}
