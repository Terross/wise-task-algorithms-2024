import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        var testGraph = GraphFactory.loadGraphFromFile(new File("/D:/Github/wise-task-algorithms-2024/example/NodesEqEdges/src/main/resources/graph.txt"));
        D_Choosable_Graph D_Choosable_Graph = new D_Choosable_Graph();
        System.out.println(D_Choosable_Graph.execute(testGraph));
    }
}
