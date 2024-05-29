import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Tolerance tolerance = new Tolerance();
        var testGraph = GraphFactory.loadGraphFromFile(new File("src/main/resources/graph.txt"));
        boolean result = tolerance.execute(testGraph);
        System.out.println(result);
    }
}