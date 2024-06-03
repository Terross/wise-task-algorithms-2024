import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        CoveringVertexSet coveringVertexSet = new CoveringVertexSet();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph.txt"));
        int result = coveringVertexSet.execute(testGraph);
        System.out.println(result);
    }
}
