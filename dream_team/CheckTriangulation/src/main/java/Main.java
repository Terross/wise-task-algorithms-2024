import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        CheckTriangulation checkTriangulation = new CheckTriangulation();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/checkTriangulation/undirected/middle1.txt"));
        boolean result = checkTriangulation.execute(testGraph);
        System.out.println(result);
    }
}