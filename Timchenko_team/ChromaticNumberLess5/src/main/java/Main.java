import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        var testGraph = GraphFactory.loadGraphFromFile(new File("/D:/Github/wise-task-algorithms-2024/example/NodesEqEdges/src/main/resources/graph.txt"));
        ChromaticNumberLess5 ChromaticNumberLess5 = new ChromaticNumberLess5();
        System.out.println(ChromaticNumberLess5.execute(testGraph));
    }
}
