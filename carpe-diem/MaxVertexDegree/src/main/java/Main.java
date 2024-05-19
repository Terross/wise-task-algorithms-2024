import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        MaxVertexDegree MaxVertexDegree = new MaxVertexDegree();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph1.txt"));
        Integer result = MaxVertexDegree.execute(testGraph);
        System.out.println(result);
    }
}
