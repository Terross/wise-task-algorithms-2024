import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        NumberOfCliques nodesEqEdges = new NumberOfCliques();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/full_4vertex.txt.txt"));
        Integer result = nodesEqEdges.execute(testGraph);
        System.out.println(result);
    }
}